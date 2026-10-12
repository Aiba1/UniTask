package com.example.unitask.ui.screens

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unitask.ui.components.AddClassDialog
import com.example.unitask.ui.components.AddTaskDialog
import com.example.unitask.ui.components.BottomNavigationBar
import com.example.unitask.ui.components.GridBackgroundLines
import com.example.unitask.ui.components.TopHeaderBar
import com.example.unitask.ui.theme.Spacing
import com.example.unitask.ui.theme.UniTaskTheme
import com.example.unitask.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    BackHandler(enabled = viewModel.navigationStack.size > 1) {
        viewModel.popBackStack()
    }

    val currentTab = viewModel.currentTab

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomNavigationBar(
                selectedTab = currentTab,
                onTabSelected = { viewModel.navigateTo(it) }
            )
        },
        floatingActionButton = {
            if (currentTab == "Tasks") {
                FloatingActionButton(
                    onClick = { viewModel.showAddTaskDialog = true },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    shape = CircleShape,
                    modifier = Modifier.defaultMinSize(minWidth = Spacing.minTouchTarget, minHeight = Spacing.minTouchTarget)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Task",
                        modifier = Modifier.size(28.dp)
                    )
                }
            } else if (currentTab == "Schedule" || currentTab == "Classes") {
                FloatingActionButton(
                    onClick = { viewModel.showAddClassDialog = true },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    shape = CircleShape,
                    modifier = Modifier.defaultMinSize(minWidth = Spacing.minTouchTarget, minHeight = Spacing.minTouchTarget)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Class",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            GridBackgroundLines()

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TopHeaderBar()

                when (currentTab) {
                    "Home" -> {
                        DashboardScreen(
                            studentName = "User",
                            completedCount = viewModel.completedCount,
                            totalCount = viewModel.totalCount,
                            progressFraction = viewModel.progressFraction,
                            tasks = viewModel.tasks,
                            classes = viewModel.classes,
                            onTaskToggle = { viewModel.toggleTask(it) },
                            onNavigateToTasks = { viewModel.navigateTo("Tasks") },
                            onNavigateToClasses = { viewModel.navigateTo("Schedule") }
                        )
                    }

                    "Schedule" -> {
                        ScheduleScreen(
                            classes = viewModel.classes,
                            onClassDelete = { viewModel.deleteClass(it) }
                        )
                    }

                    "Tasks" -> {
                        TasksScreen(
                            tasks = viewModel.tasks,
                            onTaskToggle = { viewModel.toggleTask(it) },
                            onTaskDelete = { viewModel.deleteTask(it) }
                        )
                    }

                    "Classes" -> {
                        ClassesScreen(
                            classes = viewModel.classes,
                            onClassDelete = { viewModel.deleteClass(it) }
                        )
                    }
                }
            }
        }

        if (viewModel.showAddTaskDialog) {
            AddTaskDialog(
                onDismiss = { viewModel.showAddTaskDialog = false },
                onTaskAdd = { title, time -> viewModel.addTask(title, time) }
            )
        }

        if (viewModel.showAddClassDialog) {
            AddClassDialog(
                onDismiss = { viewModel.showAddClassDialog = false },
                onClassAdd = { title, time, room, day -> viewModel.addClass(title, time, room, day) }
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true, widthDp = 390, heightDp = 840)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, widthDp = 390, heightDp = 840)
@Composable
fun HomeScreenPreview() {
    UniTaskTheme {
        Surface {
            HomeScreen()
        }
    }
}
