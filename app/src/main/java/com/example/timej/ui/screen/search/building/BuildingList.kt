package com.example.timej.ui.screen.search.building

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
import com.example.timej.data.dto.BuildingDto
import com.example.timej.data.dto.FacultyDto
import com.example.timej.ui.screen.MainViewModel
import java.util.*

@Composable
fun BuildingList(
    navController: NavController,
    mainViewModel: MainViewModel,
    state: State<String>,
    buildings: List<BuildingDto>
) {
    val buildingsTitles = buildings.map { it.title }
    var filteredBuilding: List<String>

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(top = 20.dp, bottom = 20.dp, end = 5.dp, start = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val searchedText = state.value

        filteredBuilding = if (searchedText.isEmpty()) {
            buildingsTitles
        } else {
            val resultList = mutableListOf<String>()
            for (building in buildingsTitles) {
                if (building.lowercase(Locale.getDefault())
                        .startsWith(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(building)
                }
            }
            resultList
        }

        items(filteredBuilding) { filteredBuilding ->
            BuildingItem(
                building = filteredBuilding
            ) { selectedBuilding ->
                val building = buildings.find { it.title == filteredBuilding }
                navController.navigate("auditorium_search/$selectedBuilding")
                if (building != null) {
                    mainViewModel.getAuditoriums(buildingId = building.id)
                }
            }
        }
    }
}