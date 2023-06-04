package com.example.timej.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.timej.data.repository.JwtRepository

//@Composable
//fun SetupNavHost(
//    navController: NavHostController,
//    context: Context,
//    jwtRepository: JwtRepository,
//    isAuth: Boolean
//) {
//
//    NavHost(
//        navController = navController,
//        startDestination = if (!isAuth) "Sign" else "Main"
//    ) {
//
//        signNavigation(navController, context, jwtRepository)
//
//        mainNavigation(navController)
//
//        movieNavigation(navController)
//
//        reviewNavigation(navController)
//    }
//}
