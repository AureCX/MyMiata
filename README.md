# MyMiata 🚗

MyMiata is an Android application designed for Mazda Miata enthusiasts and owners. It provides a central hub for your car's information, a map of its location, and an infrared remote for an IR-controlled accessory (e.g. LED lighting).

## 🌟 Features

- **Location Map**: Integrated Google Maps view. The marker currently uses a fixed placeholder position; no live tracking source is wired in yet.
- **Interactive UI**:
    - Personalized welcome screen.
    - Bubble-style menus for license plate information and car status popups.
- **IR Remote**: Sends NEC-encoded infrared codes through the phone's IR blaster (if it has one). Only the power code is configured; add the others in `IRRemoteActivity.kt`.
- **Emergency Services**: One-tap access to the phone dialer with your saved emergency number.
- **Local Profile**: Username, license plate and emergency number are stored on the device (no account, no password).

## 🛠️ Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: Android View system (XML layouts).
- **Maps**: [Google Maps SDK for Android](https://developers.google.com/maps/documentation/android-sdk/intro).
- **Navigation**: Activity-based navigation.
- **Storage**: `SharedPreferences` for user session and profile data.

## 📂 Project Structure

- `app/src/main/java/com/example/mymiata/`: Contains the main application logic.
    - `MainActivity.kt`: The primary dashboard.
    - `LoginActivity.kt`: User entry and session start.
    - `IRRemoteActivity.kt`: Remote control implementation.
    - `NecIrEncoder.kt`: Converts 32-bit NEC codes into IR transmit patterns (unit-tested).
    - `UserPrefs.kt`: Locally stored user profile.
- `app/src/main/res/layout/`: XML layout definitions for the traditional UI components.

## 🚀 Getting Started

### Prerequisites

- Android Studio Narwhal (2025.1.3) or newer, JDK 17+.
- Android SDK 35.
- A Google Maps API key (optional; the map stays blank without one).

### Installation

1. Clone the repository.
2. Open the project in Android Studio.
3. Add your Google Maps API key to `local.properties` (never commit this file):
   ```properties
   MAPS_API_KEY=your_key_here
   ```
   The `MAPS_API_KEY` environment variable also works, e.g. on CI.
4. Sync the project with Gradle files.
5. Run the app on a device or emulator. The IR remote needs a phone with an IR blaster.

## 📝 License

This project is for personal use and portfolio demonstration.