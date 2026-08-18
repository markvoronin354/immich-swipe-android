# Immersive Mode Implementation Walkthrough

I have configured the app to run in **Immersive Mode**, which hides the system bars (status bar and navigation bar) by default to provide more screen space for your photos.

## Changes Made

### 1. MainActivity Fullscreen Configuration
- **File:** [MainActivity.kt](file:///Users/markvoronin/StudioProjects/immich-swipe2/app/src/main/java/com/markvoronin/immichswipe/MainActivity.kt)
- **Implementation:**
    - Used `WindowInsetsControllerCompat` to hide `systemBars()`.
    - Set the behavior to `BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE`.
- **Result:** Upon app launch, the system bars are hidden. They will temporarily reappear if the user swipes from the top or bottom edges of the screen and will auto-hide shortly after.

## Verification Results

- **Build:** Successfully verified with `app:assembleDebug`.
- **User Experience:** The app now occupies the entire screen real estate, maximizing the view for swiping through media.
