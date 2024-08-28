package com.grpcx.androidtask.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.DayOfWeek

@Composable
fun WeeklyCalendar() {

    val daysOfTheWeek = DayOfWeek.entries.map { it.name.substring(0, 3) }
    var selected by remember {
        mutableIntStateOf(0)
    }

    TabRow(
        selectedTabIndex = selected,
    ) {
        daysOfTheWeek.forEachIndexed { index, day ->
            Day(
                name = day,
                isSelected = selected == index
            ) {
                selected = index
            }
        }
    }
}

@Composable
private fun Day(
    name: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        modifier = Modifier
            .padding(8.dp)
            .clickable(
                onClick = onSelected
            )
    ) {

        Box(
            modifier = Modifier
                .size(16.dp)
                .background(
                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Unspecified,
                    shape = CircleShape
                )
                .border(
                    width = if (isSelected) 0.dp else 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .3f),
                    shape = CircleShape
                )
        )

        Text(
            text = name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 18.sp,
            textAlign = TextAlign.Center,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = .3f)
        )

    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCalendar() {
    WeeklyCalendar()
}
