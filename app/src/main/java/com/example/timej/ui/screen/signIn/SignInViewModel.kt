package com.example.timej.ui.screen.signIn

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timej.data.callbacks.GetUserDetailsCallback
import com.example.timej.data.dto.LoginBodyDto
import com.example.timej.data.repository.UserAuthRepository
import com.example.timej.data_classes.Event
import com.example.timej.data.dto.TokenDto
import com.example.timej.data.dto.UserDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) : ViewModel() {

    val role = userAuthRepository.role


    var groupId = userAuthRepository.groupId
    var groupNumber = userAuthRepository.groupNumber

    var email = userAuthRepository.email
    var password = userAuthRepository.password

    init {
        viewModelScope.launch {
            if (userAuthRepository.isAuthenticated() && userAuthRepository.role != null) {
                Log.d("is auth and role not null", "")
                Log.d("role:", role ?: "null")
                if (userAuthRepository.isStudent()) {
                    Log.d("is student", "")
                    groupId = userAuthRepository.groupId
                    groupNumber = userAuthRepository.groupNumber

                } else if (userAuthRepository.isTeacher()) {
                    Log.d("is teacher", "")
                }
            } else {
                Log.d("is not auth", "")
            }
        }
    }

    var _userDetails = mutableStateOf<UserDto?>(null)

    val signInScreenState = mutableStateOf<Event<TokenDto>>(Event.default())

    private val _emailState = mutableStateOf("")
    val emailState: State<String>
        get() = _emailState

    private val _passwordState = mutableStateOf("")
    val passwordState: State<String>
        get() = _passwordState

    private val _rememberMeState = mutableStateOf(false)
    val rememberMeState: State<Boolean>
        get() = _rememberMeState

    private val _emptinessFieldsState = mutableStateOf(true)
    val emptinessFieldsState: State<Boolean>
        get() = _emptinessFieldsState

    fun onEmailChange(email: String) {
        _emailState.value = email
        validateEmpty()
    }

    fun onPasswordChange(password: String) {
        _passwordState.value = password
        validateEmpty()
    }

    fun onRememberMeChange() {
        _rememberMeState.value = !_rememberMeState.value
    }

    private fun validateEmpty() {
        _emptinessFieldsState.value =
            _emailState.value.isBlank() || _passwordState.value.isBlank()
    }

    fun onClickLogin() = viewModelScope.launch {
        userAuthRepository.login(
            loginBody = LoginBodyDto(
                email = _emailState.value,
                password = _passwordState.value
            ),
            signInScreenState = signInScreenState
        )

        signInScreenState.value.data?.let {
            userAuthRepository.getUserDetails(accessToken = it.accessToken, callback = object :
                GetUserDetailsCallback<UserDto> {
                override fun onSuccess(userDetails: UserDto) {
                    _userDetails.value = userDetails
                }

                override fun onError(error: String?) {
                    Log.d("Error: ", error ?: "")
                }
            })
        }
        //delay(1500)


    }
}