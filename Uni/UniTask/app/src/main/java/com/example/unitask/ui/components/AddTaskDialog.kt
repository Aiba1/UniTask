package com.example.unitask.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.unitask.ui.theme.Spacing

@Composable
fun AddTaskDialog(
    onDismiss: () -> Unit,
    onTaskAdd: (String, String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("12:00") }
    var showTimePicker by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Add New Task",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing.medium)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Task Title") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = time,
                        onValueChange = { time = it },
                        label = { Text("Time / Deadline") },
                        singleLine = true,
                        readOnly = true,
                        trailingIcon = {
                            IconButton(
                                onClick = { showTimePicker = true },
                                modifier = Modifier.defaultMinSize(minWidth = Spacing.minTouchTarget, minHeight = Spacing.minTouchTarget)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Schedule,
                                    contentDescription = "Select Time",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showTimePicker = true }
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        onTaskAdd(
                            title.trim(),
                            if (time.isNotBlank()) time.trim() else "12:00"
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.defaultMinSize(minWidth = Spacing.minTouchTarget, minHeight = Spacing.minTouchTarget)
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.defaultMinSize(minWidth = Spacing.minTouchTarget, minHeight = Spacing.minTouchTarget)
            ) {
                Text("Cancel", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        },
        shape = RoundedCornerShape(16.dp),
        containerColor = MaterialTheme.colorScheme.surface
    )

    if (showTimePicker) {
        val initialParts = time.split(":").mapNotNull { it.toIntOrNull() }
        val initHour = initialParts.getOrNull(0) ?: 12
        val initMinute = initialParts.getOrNull(1) ?: 0

        AppTimePickerDialog(
            title = "Select Task Deadline",
            initialHour = initHour,
            initialMinute = initMinute,
            onDismiss = { showTimePicker = false },
            onTimeSelected = { _, _, formattedTime ->
                time = formattedTime
                showTimePicker = false
            }
        )
    }
}
