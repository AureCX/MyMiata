# MyMiata 🚗

MyMiata is an Android application designed for Mazda Miata enthusiasts and owners. It provides a central hub for managing your car's information, visualizing a 3D model of the vehicle, tracking its location, and accessing remote control features.

## 🌟 Features

- **Location Tracking**: Integrated Google Maps to view your car's current or last known location.
- **Interactive UI**:
  - Personalized welcome screen.
  - Bubble-style menus for license plate information and car status popups.
- **IR Remote Integration**: Dedicated interface for infrared remote functionality.
- **Emergency Services**: One-tap access to a phone dialer for emergency situations.
  - **User Authentication**: Secure login and session management using local preferences.

⚠️⚠️ **STILL UNDER DEVELOPMENT:** ⚠️⚠️

- **3D Model Viewer**: High-quality 3D rendering of the Mazda Miata using the [SceneView](https://github.com/SceneView/sceneview-android) library.
  - Interactive camera (zoom, pan, rotate).
  - Physically Based Rendering (PBR) for realistic lighting and materials.
  - Geometric centering for intuitive model interaction.

## 🛠️ Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: Hybrid approach using **Android View System (XML)** and **Jetpack Compose**.
- **Maps**: [Google Maps SDK for Android](https://developers.google.com/maps/documentation/android-sdk/intro).
- **Navigation**: Activity-based navigation.
- **Storage**: `SharedPreferences` for user session and profile data.

⚠️⚠️ **STILL UNDER DEVELOPMENT:** ⚠️⚠️
- **3D Rendering**: [SceneView 4.0.0](https://io.github.sceneview) (Filament-based).

## 📂 Project Structure

- `app/src/main/java/com/example/mymiata/`: Contains the main application logic.
  - `MainActivity.kt`: The primary dashboard.
  - `LoginActivity.kt`: User entry and session start.
  - `IRRemoteActivity.kt`: Remote control implementation.
  - `CarModelView.kt`: Composable responsible for the 3D scene. (⚠️⚠️ **STILL UNDER DEVELOPMENT:** ⚠️⚠️)
- `app/src/main/res/layout/`: XML layout definitions for the traditional UI components.
- `app/src/main/assets/models/`: Holds the `miata.glb` 3D asset.

## 🚀 Getting Started

### Prerequisites

- Android Studio Koala (or newer).
- Android SDK 34 (UpsideDownCake) or higher.
- A valid Google Maps API Key (optional, but highly required if you intend on tracking your car ;)).

### Installation

1. Clone the repository.
2. Open the project in Android Studio.
3. Add your Google Maps API Key to `local.properties` or directly in `AndroidManifest.xml` (not recommended for production).
4. Sync the project with Gradle files.
5. Run the app on a physical device or emulator with OpenGL ES 3.0 support.

## 📝 License

This project is for personal use and portfolio demonstration.
