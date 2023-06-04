package com.example.timej.ui.screen.profile

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timej.data.repository.JwtRepository
import com.example.timej.data.repository.UserAuthRepository
import com.example.timej.data_classes.Event
import com.example.timej.ui.screen.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) : ViewModel() {

    var name = userAuthRepository.teacherName ?: userAuthRepository.studentName

    init {
        name = userAuthRepository.teacherName ?: userAuthRepository.studentName
    }

    val profileScreenState = mutableStateOf<Event<String>>(Event.default())

    fun onClickLogout() = viewModelScope.launch {
        userAuthRepository.logout(profileScreenState)
    }
}