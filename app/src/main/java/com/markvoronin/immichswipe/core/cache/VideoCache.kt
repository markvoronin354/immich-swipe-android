package com.markvoronin.immichswipe.core.cache

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.cache.CacheDataSink
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import java.io.File

/**
 * Singleton managing the video cache for ExoPlayer to prevent re-buffering on loop.
 */
@OptIn(UnstableApi::class)
object VideoCache {
    private var cache: SimpleCache? = null
    private const val CACHE_SIZE = 1024 * 1024 * 1024L // 1 GB for videos

    fun getCache(context: Context): SimpleCache {
        return cache ?: synchronized(this) {
            if (cache == null) {
                val cacheDir = File(context.cacheDir, "video_cache")
                if (!cacheDir.exists()) cacheDir.mkdirs()
                cache = SimpleCache(
                    cacheDir,
                    LeastRecentlyUsedCacheEvictor(CACHE_SIZE),
                    StandaloneDatabaseProvider(context)
                )
            }
            cache!!
        }
    }

    fun getCacheDataSourceFactory(context: Context, apiKey: String): CacheDataSource.Factory {
        val httpDataSourceFactory = DefaultHttpDataSource.Factory()
            .setAllowCrossProtocolRedirects(true)
            .setConnectTimeoutMs(15_000)
            .setReadTimeoutMs(30_000)
            .setDefaultRequestProperties(mapOf("x-api-key" to apiKey))
        
        val cacheInstance = getCache(context)
        
        return CacheDataSource.Factory()
            .setCache(cacheInstance)
            .setUpstreamDataSourceFactory(httpDataSourceFactory)
            .setCacheWriteDataSinkFactory(CacheDataSink.Factory().setCache(cacheInstance))
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
    }
}
