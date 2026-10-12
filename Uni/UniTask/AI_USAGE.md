# AI Usage Transparency Document

## Project: UniTask

This document outlines the role and impact of AI assistance during the development of the **UniTask** Android application. It provides an honest account of where AI was utilized, where it succeeded, where it was wrong or hallucinated, and how human engineering oversight was applied.

---

## 1. Scope of AI Usage

AI assistance was utilized across the following development workflows:
1. **UI Layout Implementation**: Converting visual design mocks/screenshots into idiomatic Jetpack Compose layouts (`UniTaskHomeScreen`, `DashboardScreen`, `ScheduleScreen`, `TasksScreen`, `ClassesScreen`).
2. **Design System & Styling**: Establishing a unified Material 3 theme (`Color.kt`, `Theme.kt`, `Spacing.kt`), enforcing 48dp minimum touch targets for accessibility, and supporting Dark Mode.
3. **Interactive Features**: Implementing time pickers (`AppTimePickerDialog`), dynamic progress tracking, class schedule filtering by days of the week, and Android system Back button handling (`BackHandler`).
4. **Architecture & Refactoring**: Restructuring a monolithic `MainActivity.kt` into a clean MVVM architecture (`data/model/`, `data/repository/`, `viewmodel/`, `ui/components/`, `ui/screens/`).

---

## 2. Instances Where AI Was Wrong or Made Mistakes

During the iterative development process, several AI suggestions were incorrect or incomplete and required human intervention and corrections:

### A. Deprecated and Incorrect Icon Imports
- **The Error**: The AI initially imported `Icons.Outlined.MenuBook` directly from `androidx.compose.material.icons.outlined.MenuBook`, which caused deprecation warnings/errors in newer Compose BOM releases.
- **The Correction**: Replaced with `Icons.AutoMirrored.Outlined.MenuBook` from `androidx.compose.material.icons.automirrored.outlined.MenuBook`.

### B. Incomplete Gradle Catalog Dependencies
- **The Error**: When adding `material-icons-extended` and `lifecycle-viewmodel-compose`, the AI wrote Kotlin DSL accessor strings like `libs.androidx.compose.material.icons.extended` without first syncing or properly configuring version catalog keys in `libs.versions.toml`.
- **The Correction**: Synchronized `libs.versions.toml` library catalog definitions with `build.gradle.kts` dependencies and ran `gradle_sync`.

### C. Concurrency Edit Conflicts with IDE Memory Buffers
- **The Error**: AI attempted to overwrite `MainActivity.kt` without checking if the file buffer had been modified in memory by the IDE, causing concurrency lock errors.
- **The Correction**: Used `read_file` to capture the latest buffer state before performing surgical edits via IDE modification APIs.

### D. Hardcoded Colors and Touch Targets
- **The Error**: In early iterations, AI hardcoded color literals (e.g. `Color(0xFF52B788)` and `Color(0xFFE4ECEC)`) directly inside screen Composables and created small icon buttons with touch targets smaller than 48dp.
- **The Correction**: Refactored all color definitions into `MaterialTheme.colorScheme` (`Color.kt`/`Theme.kt`), created a centralized `Spacing` object with `minTouchTarget = 48.dp`, and wrapped interactive surfaces with `defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)`.

---

## 3. Human Oversight & Verification

Every component and feature generated or modified with AI assistance was validated using:
- Automated Gradle build checks (`:app:assembleDebug`).
- Compose preview renders (`HomeScreenPreview`, `DashboardScreenPreview`, `TasksScreenPreview`, `ClassesScreenPreview`, `ScheduleScreenPreview`) in both Light and Dark mode.
- Code inspections to enforce Android best practices, Kotlin coding conventions, and accessibility standards.

---

## 4. Summary

AI served as a powerful productivity multiplier for generating Jetpack Compose boilerplate, canvas drawings, and UI structure. However, strict human engineering oversight was critical for maintaining proper architecture, resolving compiler warnings/deprecations, adhering to Material 3 design guidelines, and ensuring code quality.
