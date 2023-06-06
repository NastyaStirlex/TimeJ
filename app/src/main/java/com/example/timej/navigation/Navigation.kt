package com.example.timej.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.timej.data.repository.JwtRepository
import com.example.timej.ui.screen.MainViewModel
import com.example.timej.ui.screen.choice.ChoiceScreen
import com.example.timej.ui.screen.launch.LaunchScreen
import com.example.timej.ui.screen.launch.LaunchViewModel
import com.example.timej.ui.screen.profile.ProfileScreen
import com.example.timej.ui.screen.profile.ProfileViewModel
import com.example.timej.ui.screen.schedule.ScheduleScreen
import com.example.timej.ui.screen.search.auditorium.AuditoriumSearch
import com.example.timej.ui.screen.search.building.BuildingSearch
import com.example.timej.ui.screen.search.faculty.FacultySearch
import com.example.timej.ui.screen.search.group.GroupSearch
import com.example.timej.ui.screen.search.teacher.TeacherSearch
import com.example.timej.ui.screen.signIn.SignInScreen
import com.example.timej.ui.screen.signIn.SignInViewModel
import com.example.timej.ui.screen.student.StudentScreen
import com.example.timej.ui.screen.student.StudentViewModel
import com.example.timej.ui.screen.teacher.TeacherScreen
import com.example.timej.ui.screen.teacher.TeacherViewModel

@Composable
fun Navigation(
    context: Context,
    jwtRepository: JwtRepository,
    signInViewModel: SignInViewModel,
    mainViewModel: MainViewModel,
    group: String?,
    name: String?,
    isAuth: Boolean
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (!isAuth) "sign_in" else "launch"
    ) {
        composable("launch") {
            val launchViewModel = hiltViewModel<LaunchViewModel>()
            LaunchScreen(
                navController = navController,
                launchViewModel = launchViewModel
            )
        }


        composable("student_profile") {
            if (group != null) {
                StudentNavBar(navController = navController, group = group) {
                    val profileViewModel = hiltViewModel<ProfileViewModel>()
                    ProfileScreen(
                        onBackToClick = { navController.navigate("student_schedule/$group") },
                        profileViewModel = profileViewModel,
                        context = context,
                        jwtRepository = jwtRepository,
                        onClickLogout = { navController.navigate("sign_in") })
                }
            }
        }

        composable("teacher_profile") {
            if (name != null) {
                TeacherNavBar(navController = navController, name = name) {
                    val profileViewModel = hiltViewModel<ProfileViewModel>()
                    ProfileScreen(
                        onBackToClick = { navController.navigate("teacher_schedule/$name") },
                        profileViewModel = profileViewModel,
                        context = context,
                        jwtRepository = jwtRepository,
                        onClickLogout = { navController.navigate("sign_in") })
                }
            }
        }

        composable(
            route = "student_schedule/{group}",
            arguments = listOf(
                navArgument("group") {
                    type = NavType.StringType
                    defaultValue = group
                    nullable = true
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("group")?.let { group ->
                val studentViewModel = hiltViewModel<StudentViewModel>()
                StudentNavBar(navController = navController, group = group) {
                    StudentScreen(
                        studentViewModel = studentViewModel,
                        mainViewModel = mainViewModel,
                        group = group
                    )
                }
            }

        }

        composable(
            route = "teacher_schedule/{name}",
            arguments = listOf(
                navArgument("name") {
                type = NavType.StringType
                defaultValue = group
                nullable = true
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("name")?.let { name ->
                val teacherViewModel = hiltViewModel<TeacherViewModel>()
                TeacherNavBar(navController = navController, name = name) {
                    TeacherScreen(
                        teacherViewModel = teacherViewModel,
                        mainViewModel = mainViewModel,
                        name = name
                    )
                }
            }

        }

        composable("sign_in") {
            SignInScreen(
                navController = navController,
                onSuccessSignIn = {
                    navController.navigate("launch") {
                        launchSingleTop = true
                        restoreState = true
                    }

                },
                onNoAccountClick = {
                    navController.navigate("choice_screen") {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                signInViewModel = signInViewModel,
                context = context,
                jwtRepository = jwtRepository
            )
        }

        composable("choice_screen") {
            ChoiceScreen(
                onBackClick = {
                    navController.navigate("sign_in") {
                        popUpTo("sign_in")
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onGroupsClick = {
                    navController.navigate("faculty_search") {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onTeacherClick = {
                    navController.navigate("teacher_search") {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onAuditoriumClick = {
                    navController.navigate("building_search") {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable("faculty_search") {
            FacultySearch(
                onBackClick = {
                    navController.navigate("choice_screen") {
                        popUpTo("choice_screen")
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navController = navController,
                mainViewModel = mainViewModel
            )
        }

        composable(
            "group_search/{faculty}",
            arguments = listOf(navArgument("faculty") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("faculty")?.let { faculty ->
                GroupSearch(
                    onBackClick = {
                        navController.navigate("faculty_search") {
                            popUpTo("faculty_search")
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    navController = navController,
                    faculty = faculty,
                    mainViewModel = mainViewModel
                )
            }
        }

        composable("building_search") {
            BuildingSearch(
                onBackClick = {
                    navController.navigate("choice_screen") {
                        popUpTo("choice_screen")
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navController = navController,
                mainViewModel = mainViewModel
            )
        }

        composable(
            route = "auditorium_search/{building}",
            arguments = listOf(navArgument("building") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("building")?.let { building ->
                AuditoriumSearch(
                    onBackClick = {
                        navController.navigate("building_search") {
                            popUpTo("building_search")
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    navController = navController,
                    building = building,
                    mainViewModel = mainViewModel
                )
            }
        }

        composable(
            route = "schedule?group={group}&teacher={teacher}&building={building}&auditorium={auditorium}",
            arguments = listOf(
                navArgument("group") {
                    nullable = true
                    defaultValue = null
                    type = NavType.StringType
                },
                navArgument("teacher") {
                    nullable = true
                    defaultValue = null
                    type = NavType.StringType
                },
                navArgument("building") {
                    nullable = true
                    defaultValue = null
                    type = NavType.StringType
                },
                navArgument("auditorium") {
                    nullable = true
                    defaultValue = null
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val group = backStackEntry.arguments?.getString("group")
            val teacher = backStackEntry.arguments?.getString("teacher")
            val building = backStackEntry.arguments?.getString("building")
            val auditorium = backStackEntry.arguments?.getString("auditorium")
            ScheduleScreen(
                mainViewModel = mainViewModel,
                group = group,
                teacher = teacher,
                building = building,
                auditorium = auditorium,
            )


        }

        composable("teacher_search") {
            TeacherSearch(
                onBackClick = {
                    navController.navigate("choice_screen") {
                        popUpTo("choice_screen")
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navController = navController,
                mainViewModel = mainViewModel
            )
        }
    }
}