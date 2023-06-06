package com.example.timej.ui.screen.search.auditorium

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.timej.R
import com.example.timej.data.dto.AuditoriumDto
import com.example.timej.data.net.Status
import com.example.timej.ui.screen.MainViewModel
import com.example.timej.ui.screen.search.view.SearchView
import com.example.timej.ui.theme.*
import com.example.timej.ui.view.LoadingScreen

@Composable
fun AuditoriumSearch(
    onBackClick: () -> Unit,
    navController: NavHostController,
    building: String,
    mainViewModel: MainViewModel
) {
    val auditorium = remember { mainViewModel.auditoriumSearchState }
    //val auditoriums = remember { mainViewModel.auditoriumsState }
    val auditoriums = listOf(
        AuditoriumDto("", 123, "123", ""),
        AuditoriumDto("", 234, "234", ""),
        AuditoriumDto("", 45, "45", ""),
        AuditoriumDto("", 3, "3", ""),
        AuditoriumDto("", 47, "47", ""),
        AuditoriumDto("", 100, "100", ""),
        AuditoriumDto("", 223, "223", ""),
        AuditoriumDto("", 310, "310", ""),

    )
    val screenState = remember { mainViewModel.auditoriumScreenState }

    Column {
        Box(
            modifier = Modifier
                .background(HawkesBlue)
                .fillMaxWidth()
                .height(135.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 22.dp)
                        .padding(horizontal = 19.dp)
                ) {
                    IconButton(onClick = onBackClick, modifier = Modifier.size(20.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_left),
                            contentDescription = null,
                            tint = Raven
                        )
                    }

                    Text(
                        text = stringResource(id = R.string.auditorium),
                        style = SearchTitle,
                        color = Shark,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 45.dp)
                ) {
                    Text(
                        text = building,
                        style = SearchFaculty,
                        color = Shark,
                    )
                }

                SearchView(
                    state = auditorium,
                    onValueChange = mainViewModel::onAuditoriumSearchChange,
                    placeholderText = R.string.auditorium_number
                )

            }
        }

        if (screenState.value.status == Status.LOADING) {
            LoadingScreen()
        } else if (screenState.value.status == Status.SUCCESS) {
            val data = screenState.value.data
            if (data == null) {
                AuditoriumList(
                    navController = navController,
                    mainViewModel = mainViewModel,
                    state = auditorium,
                    building = building,
                    auditoriums = auditoriums
                )
            } else {
                AuditoriumList(
                    navController = navController,
                    mainViewModel = mainViewModel,
                    state = auditorium,
                    building = building,
                    auditoriums = data
                )
            }
        }

    }
}