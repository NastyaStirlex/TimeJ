package com.example.timej

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.timej.data.repository.JwtRepository
import com.example.timej.navigation.Navigation
import com.example.timej.ui.screen.MainViewModel
import com.example.timej.ui.screen.signIn.SignInViewModel
import com.example.timej.ui.theme.TimeJTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

val calendar = Calendar.getInstance()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeJTheme {
                val signInViewModel = hiltViewModel<SignInViewModel>()

                val mainViewModel = hiltViewModel<MainViewModel>()

                val jwtRepository = JwtRepository()
                val group = jwtRepository.getGroupNumber(this)
                val name = jwtRepository.getTeacherName(this)

                val isAuth = jwtRepository.getAccessToken(context = this) != null

                Navigation(
                    context = this,
                    jwtRepository = jwtRepository,
                    signInViewModel = signInViewModel,
                    mainViewModel = mainViewModel,
                    group = group,
                    name = name,
                    isAuth = isAuth
                )
            }
        }
    }
}
