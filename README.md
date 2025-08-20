# SpacesWayFindingSDK-SampleApp-Android

A sample Android application demonstrating the integration and usage of the Spaces WayFinding SDK for indoor navigation and mapping. This project showcases how to use the SDK in a real-world scenario, including map display, building selection, and pathfinding features.

## Features
- Indoor map display using Maplibre
- Building and floor selection
- Pathfinding and navigation
- Custom event handling for map and navigation events
- Example integration of SpacesWayFindingSDK and SpacesWayFindingApp libraries

## Requirements
- Koala Feature Drop | 2024.2.1 or later
- JDK 17 or later
- Android 8.0(SDK version 26) or later
- Kotlin 2.0.0 or later
- SpacesWayFindingSDK and SpacesWayFindingApp (included as aar files)

## Getting Started

### 1. Clone the Repository
```sh
git clone https://github.com/CiscoDevNet/SpacesWayFindingSDK-SampleApp-Android.git
cd SpacesWayFindingSDK-SampleApp-Android
```

### 2. Open the Project
Open `SpacesWayFindingSDK-SampleApp-Android` in Android Studio.

### 3. Build and Run
- Select a simulator or a physical device.
- Build and run the app.

### 4. SDK Integration
- The required libraries (`SpacesWayFindingSDK`, `SpacesWayFindingApp`) are included in the `app/libs/` directory.
- Go to `File` and click `Sync Project with Gradle Files` to sync with Gradle.

## Project Structure
- `app/src/main/java/com/cisco/spaces/wayfinding/sample/`
  - `MainActivity.kt`: The main entry point of the application which handles building and floor selection
  - `BuildingParamsFragment.kt`: UI for building parameters configuration
  - `BuildingParamsRecyclerViewAdapter.kt`: Adapter for building params list
  - `ProgressFragment.kt`: Progress indicator UI component
  - `handlers/`: Contains custom event handlers
      - `CustomMapEventsHandler.kt`: Handles map-related events
      - `CustomMapExitHandler.kt`: Manages map exit events
      - `CustomPathFindingEventsHandler.kt`: Handles navigation events
- `app/src/main/res/`
  - `layout/`: XML files defining the UI for activities.
  - `drawable/`: Image/Icon assets used within the application.
  - `values/`: Resource files for strings, colors, and styles.
- `app/libs/`: Contains the required `SpacesWayFindingSDK.aar` and `SpacesWayFindingApp.aar` libraries.
- `build.gradle.kts`: Gradle build scripts for the application and project-level dependencies.

## Documentation
- See `WF-SDK-V1.2-Document.pdf` for detailed SDK documentation and API reference.

## Contributing
See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## License
See [LICENSE](LICENSE) for license information.

## Security
See [SECURITY.md](SECURITY.md) for security policies.

## Code of Conduct
See [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) for community standards.

## Support
For questions or support, please refer to the documentation or open an issue in this repository.
