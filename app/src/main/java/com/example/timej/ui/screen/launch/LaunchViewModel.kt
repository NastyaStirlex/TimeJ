package com.example.timej.ui.screen.launch

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timej.data.repository.UserAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    userAuthRepository: UserAuthRepository
) : ViewModel() {

    val isStudent = mutableStateOf(false)
    val isTeacher = mutableStateOf(false)

    val role = userAuthRepository.role

    var groupId = userAuthRepository.groupId
    var groupNumber = userAuthRepository.groupNumber

    var teacherName = userAuthRepository.teacherName

    init {
        viewModelScope.launch {
            if (userAuthRepository.isAuthenticated() && userAuthRepository.role != null) {
                if (userAuthRepository.isStudent()) {
                    isStudent.value = true
                    groupId = userAuthRepository.groupId
                    groupNumber = userAuthRepository.groupNumber

                } else if (userAuthRepository.isTeacher()) {
                    isTeacher.value = true
                    teacherName = userAuthRepository.teacherName
                }
            }
        }
    }


}