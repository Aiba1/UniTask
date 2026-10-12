package com.example.unitask.data.repository

import com.example.unitask.data.model.ClassItem
import com.example.unitask.data.model.TaskItem

class TaskRepository {
    private var nextTaskId = 6
    private var nextClassId = 6

    fun getInitialTasks(): List<TaskItem> {
        return listOf(
            TaskItem(1, "Practice coding for 30 min", "23:30", true),
            TaskItem(2, "Practice database", "12:00", true),
            TaskItem(3, "Work on assignment Android", "13:00", true),
            TaskItem(4, "Practice coding for 30 min", "15:30", false),
            TaskItem(5, "Practice coding for 30 min", "20:00", false)
        )
    }

    fun getInitialClasses(): List<ClassItem> {
        return listOf(
            ClassItem(1, "Monday", "09:00 – 10:20", "Mobile Application Development", "Room 305"),
            ClassItem(2, "Tuesday", "10:30 – 11:50", "Database Systems", "Room 412"),
            ClassItem(3, "Wednesday", "12:30 – 13:50", "Probability & Statistics", "Room 207"),
            ClassItem(4, "Thursday", "14:00 – 15:20", "Web Development", "Room 318"),
            ClassItem(5, "Friday", "11:00 – 12:20", "Software Engineering", "Room 102")
        )
    }

    fun createNewTask(title: String, time: String): TaskItem {
        return TaskItem(nextTaskId++, title, time, false)
    }

    fun createNewClass(title: String, time: String, room: String, day: String): ClassItem {
        return ClassItem(nextClassId++, day, time, title, room)
    }
}
