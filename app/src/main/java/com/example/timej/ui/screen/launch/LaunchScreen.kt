package com.example.timej.ui.screen.launch

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.timej.ui.view.LoadingScreen
import kotlinx.coroutines.delay

@Composable
fun LaunchScreen(
    navController: NavHostController,
    launchViewModel: LaunchViewModel
) {
    val isStudent by remember { launchViewModel.isStudent }
    val isTeacher by remember { launchViewModel.isTeacher }

    val scale = remember {
        androidx.compose.animation.core.Animatable(0.0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )

        delay(1700)
        if (true) {
            navController.navigate("student_schedule/${launchViewModel.groupNumber}")
        } else if (isTeacher) {
            navController.navigate("teacher_schedule/${launchViewModel.teacherName}")
        } else {
            navController.navigate("choice_screen")
        }
    }

    LoadingScreen()
}