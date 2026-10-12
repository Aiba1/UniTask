package com.example.unitask.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitask.ui.theme.Spacing

val DAYS_OF_WEEK = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

@Composable
fun AddClassDialog(
    onDismiss: () -> Unit,
    onClassAdd: (String, String, String, String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("09:00") }
    var endTime by remember { mutableStateOf("10:20") }
    var room by remember { mutableStateOf("") }
    var selectedDay by remember { mutableStateOf("Monday") }

    var pickingPickerType by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Add New Class",
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
                    label = { Text("Subject / Class Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.small),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedTextField(
                            value = startTime,
                            onValueChange = { startTime = it },
                            label = { Text("Start Time") },
                            singleLine = true,
                            readOnly = true,
                            trailingIcon = {
                                IconButton(
                                    onClick = { pickingPickerType = "start" },
                                    modifier = Modifier.defaultMinSize(minWidth = Spacing.minTouchTarget, minHeight = Spacing.minTouchTarget)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Schedule,
                                        contentDescription = "Pick Start Time",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { pickingPickerType = "start" }
                        )
                    }

                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedTextField(
                            value = endTime,
                            onValueChange = { endTime = it },
                            label = { Text("End Time") },
                            singleLine = true,
                            readOnly = true,
                            trailingIcon = {
                                IconButton(
                                    onClick = { pickingPickerType = "end" },
                                    modifier = Modifier.defaultMinSize(minWidth = Spacing.minTouchTarget, minHeight = Spacing.minTouchTarget)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Schedule,
                                        contentDescription = "Pick End Time",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { pickingPickerType = "end" }
                        )
                    }
                }

                OutlinedTextField(
                    value = room,
                    onValueChange = { room = it },
                    label = { Text("Room (e.g. Room 204)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Select Day:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.extraSmall + 2.dp)
                ) {
                    items(DAYS_OF_WEEK) { day ->
                        val isSelected = selectedDay == day
                        val bg = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
                        val textColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant

                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = bg,
                            modifier = Modifier
                                .defaultMinSize(minWidth = Spacing.minTouchTarget, minHeight = Spacing.minTouchTarget)
                                .clickable { selectedDay = day }
                        ) {
                            Text(
                                text = day.take(3),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = textColor,
                                modifier = Modifier.padding(horizontal = Spacing.medium, vertical = Spacing.small)
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        val fullTimeRange = "$startTime – $endTime"
                        onClassAdd(
                            title.trim(),
                            fullTimeRange,
                            if (room.isNotBlank()) room.trim() else "Room 101",
                            selectedDay
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

    if (pickingPickerType != null) {
        val targetTime = if (pickingPickerType == "start") startTime else endTime
        val initialParts = targetTime.split(":").mapNotNull { it.toIntOrNull() }
        val initHour = initialParts.getOrNull(0) ?: (if (pickingPickerType == "start") 9 else 10)
        val initMinute = initialParts.getOrNull(1) ?: (if (pickingPickerType == "start") 0 else 20)

        AppTimePickerDialog(
            title = if (pickingPickerType == "start") "Select Start Time" else "Select End Time",
            initialHour = initHour,
            initialMinute = initMinute,
            onDismiss = { pickingPickerType = null },
            onTimeSelected = { _, _, formattedTime ->
                if (pickingPickerType == "start") {
                    startTime = formattedTime
                } else {
                    endTime = formattedTime
                }
                pickingPickerType = null
            }
        )
    }
}
