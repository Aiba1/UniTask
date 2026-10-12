package com.example.unitask.data.model

data class TaskItem(
    val id: Int,
    val title: String,
    val time: String,
    var isCompleted: Boolean
)
