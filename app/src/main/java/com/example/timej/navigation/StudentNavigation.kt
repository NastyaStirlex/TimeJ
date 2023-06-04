package com.example.timej.navigation

import android.content.Context
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

import com.example.timej.data.repository.JwtRepository
import com.example.timej.ui.screen.student.StudentScreen
import com.example.timej.ui.screen.student.StudentViewModel

fun NavGraphBuilder.signNavigation(
    navController: NavController,
    context: Context,
    jwtRepository: JwtRepository
) {
    navigation(route = "Student", startDestination = "") {
        composable("schedule") {

            val studentViewModel = hiltViewModel<StudentViewModel>()

            //StudentScreen(studentViewModel = studentViewModel, group = )
        }
    }
}