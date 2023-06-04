package com.example.timej.ui.screen.search.teacher

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.timej.calendar
import com.example.timej.data.dto.TeacherDto
import com.example.timej.utils.getCurrentWeekEnd
import com.example.timej.utils.getCurrentWeekStart
import com.example.timej.ui.screen.MainViewModel
import java.util.*

@Composable
fun TeacherList(
    navController: NavController,
    mainViewModel: MainViewModel,
    state: State<String>,
    teachers: List<TeacherDto>
) {
    val teachersNames = teachers.map { it.surname + " " + it.name + " " + it.middleName }
    var filteredTeachers: List<String>

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp, start = 20.dp, end = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val searchedText = state.value

        filteredTeachers = if (searchedText.isEmpty()) {
            teachersNames
        } else {
            val resultList = mutableListOf<String>()
            for (teacher in teachersNames) {
                if (teacher.lowercase(Locale.getDefault())
                        .startsWith(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(teacher)
                }
            }
            resultList
        }
        items(filteredTeachers) { filteredTeacher ->
            TeacherItem(
                teacher = filteredTeacher,
                onItemClick = { selectedTeacher ->
                    (mainViewModel::getShedule)(
                        getCurrentWeekStart(mCalendar = calendar),
                        getCurrentWeekEnd(mCalendar = calendar),
                        null,
                        teachers.find { it.surname + " " + it.name + " " + it.middleName == selectedTeacher }?.id,
                        null,
                        null
                    )

                    navController.navigate("shedule?teacher=$selectedTeacher")
                }
            )
        }
    }
}