package com.example.timej.ui.screen.search.teacher

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
import com.example.timej.data.dto.TeacherDto
import com.example.timej.data.net.Status
import com.example.timej.ui.screen.MainViewModel
import com.example.timej.ui.screen.search.view.SearchView
import com.example.timej.ui.theme.*
import com.example.timej.ui.view.LoadingScreen

@Composable
fun TeacherSearch(
    onBackClick: () -> Unit,
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    val teacher = remember { mainViewModel.teacherSearchState }
    //val teachers = remember { mainViewModel.teachersState }
    val teachers = listOf(
        TeacherDto("", "", "", "", "Ivanovich", "Ivan", listOf(), "Ivanov"),
        TeacherDto("", "", "", "", "Ivanovich", "Sergei", listOf(), "Ivanov"),
        TeacherDto("", "", "", "", "Ivanovich", "Stepan", listOf(), "Ivanov"),
        TeacherDto("", "", "", "", "Ivanovna", "Diana", listOf(), "Ivanova"),
        TeacherDto("", "", "", "", "Ivanovich", "Denis", listOf(), "Ivanov"),
        TeacherDto("", "", "", "", "Ivanovich", "Nikolai", listOf(), "Ivanov"),
        TeacherDto("", "", "", "", "Ivanovich", "Sergei", listOf(), "Ivanov")
    )
    val teachersScreenState = remember { mainViewModel.teachersScreenState }

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
                        text = stringResource(id = R.string.teachers),
                        style = SearchTitle,
                        color = Shark,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                SearchView(
                    state = teacher,
                    onValueChange = mainViewModel::onTeacherSearchChange,
                    placeholderText = R.string.teacher_name
                )

            }
        }

        if (teachersScreenState.value.status == Status.LOADING) {
            LoadingScreen()
        } else if (teachersScreenState.value.status == Status.SUCCESS) {
            TeacherList(
                navController = navController,
                mainViewModel = mainViewModel,
                state = teacher,
                teachers = teachers
            )
        }
    }

}