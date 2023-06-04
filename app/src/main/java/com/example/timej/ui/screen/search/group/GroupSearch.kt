package com.example.timej.ui.screen.search.group

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
import com.example.timej.data_classes.Status
import com.example.timej.ui.screen.MainViewModel
import com.example.timej.ui.screen.search.view.SearchView
import com.example.timej.ui.theme.*
import com.example.timej.view.LoadingScreen

@Composable
fun GroupSearch(
    onBackClick: () -> Unit,
    navController: NavHostController,
    faculty: String,
    mainViewModel: MainViewModel
) {
    val group = remember { mainViewModel.groupSearchState }
    val groups = remember { mainViewModel.groupsState }
    val groupScreenState = remember { mainViewModel.groupScreenState }

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
                        text = stringResource(id = R.string.groups),
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
                        text = faculty,
                        style = SearchFaculty,
                        color = Shark,
                    )
                }

                SearchView(
                    state = group,
                    onValueChange = mainViewModel::onGroupSearchChange,
                    placeholderText = R.string.group_number
                )

            }
        }

        if (groupScreenState.value.status == Status.LOADING) {
            LoadingScreen()
        } else if (groupScreenState.value.status == Status.SUCCESS) {
            GroupList(
                navController = navController,
                mainViewModel = mainViewModel,
                state = group,
                groups = groups
            )
        }
    }
}