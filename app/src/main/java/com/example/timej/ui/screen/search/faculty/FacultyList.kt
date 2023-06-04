package com.example.timej.ui.screen.search.faculty

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
import com.example.timej.data.dto.FacultyDto
import com.example.timej.ui.screen.MainViewModel
import java.util.*

@Composable
fun FacultyList(
    navController: NavController,
    mainViewModel: MainViewModel,
    state: State<String>,
    faculties: List<FacultyDto>
) {
    val facultiesNames = faculties.map { it.name }
    var filteredFaculties: List<String>

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(top = 20.dp, bottom = 20.dp, start = 20.dp, end = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val searchedText = state.value

        filteredFaculties = if (searchedText.isEmpty()) {
            facultiesNames
        } else {
            val resultList = mutableListOf<String>()
            for (faculty in facultiesNames) {
                if (faculty.lowercase(Locale.getDefault())
                        .startsWith(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(faculty)
                }
            }
            resultList
        }
        items(filteredFaculties) { filteredFaculty ->
            FacultyItem(
                faculty = filteredFaculty
            ) { selectedFaculty ->
                val faculty = faculties.find { it.name == filteredFaculty }
                navController.navigate("group_search/$selectedFaculty")
                if (faculty != null) {
                    mainViewModel.getGroups(facultyId = faculty.id)
                }
            }
        }
    }
}