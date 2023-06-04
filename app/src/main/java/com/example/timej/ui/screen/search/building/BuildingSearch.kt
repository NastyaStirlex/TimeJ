package com.example.timej.ui.screen.search.building

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.timej.R
import com.example.timej.data_classes.Status
import com.example.timej.ui.screen.MainViewModel
import com.example.timej.ui.screen.search.view.SearchView
import com.example.timej.ui.theme.HawkesBlue
import com.example.timej.ui.theme.Raven
import com.example.timej.ui.theme.SearchTitle
import com.example.timej.ui.theme.Shark
import com.example.timej.view.LoadingScreen

@Composable
fun BuildingSearch(
    onBackClick: () -> Unit,
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    val building = remember { mainViewModel.buildingSearchState }
    val buildings = remember { mainViewModel.buildingsState }
    val screenState = remember { mainViewModel.buildingScreenState }

    Column {
        Box(
            modifier = Modifier
                .background(HawkesBlue)
                .fillMaxWidth()
                .height(135.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
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
                        text = stringResource(id = R.string.building),
                        style = SearchTitle,
                        color = Shark,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                SearchView(
                    state = building,
                    onValueChange = mainViewModel::onBuildingSearchChange,
                    placeholderText = R.string.building_number
                )

            }
        }

        if (screenState.value.status == Status.LOADING) {
            LoadingScreen()
        } else if (screenState.value.status == Status.SUCCESS) {
            BuildingList(
                navController = navController,
                mainViewModel = mainViewModel,
                state = building,
                buildings = buildings
            )
        }

    }

}