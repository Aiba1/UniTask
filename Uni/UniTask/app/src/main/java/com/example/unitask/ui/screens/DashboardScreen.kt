package com.example.unitask.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.unitask.data.model.ClassItem
import com.example.unitask.data.model.TaskItem
import com.example.unitask.ui.components.HeroSection
import com.example.unitask.ui.components.TodayClassesSection
import com.example.unitask.ui.components.TodayProgressCard
import com.example.unitask.ui.components.TodayTasksSection
import com.example.unitask.ui.theme.Spacing
import com.example.unitask.ui.theme.UniTaskTheme

@Composable
fun DashboardScreen(
    studentName: String,
    completedCount: Int,
    totalCount: Int,
    progressFraction: Float,
    tasks: List<TaskItem>,
    classes: List<ClassItem>,
    onTaskToggle: (Int) -> Unit,
    onNavigateToTasks: () -> Unit,
    onNavigateToClasses: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Spacing.large, vertical = Spacing.medium),
        verticalArrangement = Arrangement.spacedBy(Spacing.large)
    ) {
        HeroSection(studentName = studentName)

        TodayProgressCard(
            completedCount = completedCount,
            totalCount = totalCount,
            progress = progressFraction
        )

        TodayTasksSection(
            tasks = tasks.take(5),
            onTaskToggle = onTaskToggle,
            onViewAllClick = onNavigateToTasks
        )

        TodayClassesSection(
            classes = classes,
            onViewAllClick = onNavigateToClasses
        )

        Spacer(modifier = Modifier.height(Spacing.default))
    }
}

@Preview(name = "Light Mode", showBackground = true, widthDp = 390, heightDp = 840)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, widthDp = 390, heightDp = 840)
@Composable
fun DashboardScreenPreview() {
    UniTaskTheme {
        Surface {
            DashboardScreen(
                studentName = "User",
                completedCount = 3,
                totalCount = 5,
                progressFraction = 0.6f,
                tasks = listOf(
                    TaskItem(1, "Practice coding for 30 min", "23:30", true),
                    TaskItem(2, "Practice database", "12:00", true)
                ),
                classes = listOf(
                    ClassItem(1, "Monday", "09:00 – 10:20", "Mobile Dev", "Room 305")
                ),
                onTaskToggle = {},
                onNavigateToTasks = {},
                onNavigateToClasses = {}
            )
        }
    }
}
