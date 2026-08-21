# Persistent Fullscreen Controls and Minimal Card View

This plan refines the video player UI based on your feedback: removing progress indicators from the card view and making fullscreen controls always visible.

## Proposed Changes

### [Component Name]

#### [MODIFY] [SwipeScreen.kt](file:///Users/markvoronin/StudioProjects/immich-swipe2/app/src/main/java/com/markvoronin/immichswipe/feature/swipe/SwipeScreen.kt)

1.  **SharedVideoPlayer Updates**:
    *   Modify the progress UI block to only render if `isFullscreen` is `true`. This removes the `LinearProgressIndicator` and timestamp from the normal swipe cards.

2.  **FullscreenViewer Updates**:
    *   Change the initial state of `controlsVisible` to `true`.
    *   Remove the toggle logic (`controlsVisible = !controlsVisible`) from the `onTap` and `onPress` callbacks. This makes the controls persistent and removes the requirement to tap to see them.
    *   Clean up the `onTap` and `onPress` branches that were used for toggling, focusing them solely on "Tap to Swipe" (KEEP/DELETE) and video control (toggling the ExoPlayer controller trigger if needed, though native controls are disabled).

## Verification Plan

### Manual Verification
*   **Normal View**: Verify that videos in the swipe cards no longer show a progress bar or timestamp at the bottom.
*   **Fullscreen Mode**:
    *   Verify that all controls (Favorite, Close, Mute, Progress Slider, etc.) are visible immediately upon entering fullscreen.
    *   Verify that tapping the center of the screen no longer hides the controls.
    *   Verify that "Tap to Swipe" (left/right sides) still works as expected.
