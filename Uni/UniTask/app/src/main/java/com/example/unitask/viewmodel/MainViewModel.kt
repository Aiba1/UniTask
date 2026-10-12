package com.example.unitask.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unitask.data.model.ClassItem
import com.example.unitask.data.model.TaskItem
import com.example.unitask.data.repository.TaskRepository

class MainViewModel(
    private val repository: TaskRepository = TaskRepository()
) : ViewModel() {

    val navigationStack = mutableStateListOf("Home")
    val currentTab: String
        get() = navigationStack.lastOrNull() ?: "Home"

    var tasks by mutableStateOf(repository.getInitialTasks())
        private set

    var classes by mutableStateOf(repository.getInitialClasses())
        private set

    var showAddTaskDialog by mutableStateOf(false)
    var showAddClassDialog by mutableStateOf(false)

    val completedCount: Int
        get() = tasks.count { it.isCompleted }

    val totalCount: Int
        get() = tasks.size

    val progressFraction: Float
        get() = if (totalCount > 0) completedCount.toFloat() / totalCount else 0f

    fun navigateTo(tab: String) {
        if (currentTab != tab) {
            navigationStack.add(tab)
        }
    }

    fun popBackStack(): Boolean {
        return if (navigationStack.size > 1) {
            navigationStack.removeAt(navigationStack.lastIndex)
            true
        } else {
            false
        }
    }

    fun toggleTask(taskId: Int) {
        tasks = tasks.map {
            if (it.id == taskId) it.copy(isCompleted = !it.isCompleted)
            else it
        }
    }

    fun deleteTask(taskId: Int) {
        tasks = tasks.filter { it.id != taskId }
    }

    fun addTask(title: String, time: String) {
        val newTask = repository.createNewTask(title, time)
        tasks = tasks + newTask
        showAddTaskDialog = false
    }

    fun deleteClass(classId: Int) {
        classes = classes.filter { it.id != classId }
    }

    fun addClass(title: String, time: String, room: String, day: String) {
        val newClass = repository.createNewClass(title, time, room, day)
        classes = classes + newClass
        showAddClassDialog = false
    }
}
