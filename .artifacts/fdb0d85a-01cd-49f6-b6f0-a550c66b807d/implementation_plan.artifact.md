# Immersive Mode (Full Screen) Implementation

Configure the app to automatically hide the system bars (status and navigation) upon launch to provide a distraction-free experience.

## Proposed Changes

### MainActivity
#### [MODIFY] [MainActivity.kt](file:///Users/markvoronin/StudioProjects/immich-swipe2/app/src/main/java/com/markvoronin/immichswipe/MainActivity.kt)
- Import `WindowCompat`, `WindowInsetsCompat`, and `WindowInsetsControllerCompat`.
- Configure `WindowInsetsControllerCompat` in `onCreate` to hide system bars and set the behavior to `BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE`.

## Verification Plan

### Automated Tests
- Build the project to ensure no regressions in window handling.

### Manual Verification
- Launch the app and verify that the status bar and navigation bar are hidden.
- Verify that they can be revealed by swiping from the edges and that they hide themselves again automatically.
