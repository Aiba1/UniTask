# UniTask

UniTask is a practical academic planner designed to help university students track their subjects, assignments, and deadlines. It provides a straightforward, offline-first interface to manage daily schedules and academic workloads efficiently.

## Core Functionality

* User can create, edit, and delete academic subjects.
* User can add, modify, delete, and mark tasks as completed.
* User can assign specific deadlines and link tasks to a subject.
* App displays a daily/weekly schedule and upcoming deadlines.
* App persists all data locally using a Room database.
* User can filter tasks by subject or completion status.
* App triggers local notifications for approaching deadlines.

## Folder Structure

StudyFlow/
├── app/
│   ├── src/main/java/com/example/unitask/   # Kotlin source code
│   ├── src/main/res/                        # UI resources, layouts, icons
│   └── src/main/AndroidManifest.xml         # App configuration
├── gradle/                                  # Gradle wrapper files
├── build.gradle.kts                         # Project-level build configurations
└── README.md                                # Project documentation

## Build & Run Instructions

1. Clone the repository using Git.
2. Open the project folder in Android Studio.
3. Wait for the Gradle sync to complete.
4. Connect an Android device or start an Android Virtual Device (AVD).
5. Click the "Run" button in the top toolbar.