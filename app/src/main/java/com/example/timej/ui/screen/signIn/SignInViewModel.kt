package com.example.timej.ui.screen.signIn

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timej.data.dto.TokenDto
import com.example.timej.data.dto.UserDto
import com.example.timej.data.repository.DataStoreRepository
import com.example.timej.data.repository.UserAuthRepository
import com.example.timej.data.net.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userAuthRepository: UserAuthRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    val role = userAuthRepository.role

    var groupId = userAuthRepository.groupId
    var groupNumber = userAuthRepository.groupNumber

    var email = userAuthRepository.email
    var password = userAuthRepository.password

    init {
        viewModelScope.launch {
            if (userAuthRepository.isAuthenticated() && userAuthRepository.role != null) {
                if (userAuthRepository.isStudent()) {
                    groupId = userAuthRepository.groupId
                    groupNumber = userAuthRepository.groupNumber

                } else if (userAuthRepository.isTeacher()) {}
            }
        }
    }

    var _userDetails = mutableStateOf<UserDto?>(null)

    val signInScreenState = mutableStateOf<Event<TokenDto>>(Event.default())

    private val _emailState = mutableStateOf<String>("")
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
        if (_rememberMeState.value) {
            dataStoreRepository.saveEmail(_emailState.value)
        }

        signInScreenState.value = Event.success(null)

//        userAuthRepository.login(
//            loginBody = LoginBodyDto(
//                email = _emailState.value,
//                password = _passwordState.value
//            ),
//            signInScreenState = signInScreenState
//        )
//
//        signInScreenState.value.data?.let {
//            userAuthRepository.getUserDetails(accessToken = it.accessToken)
//        }
        //delay(1500)
    }

    val emailFlow = viewModelScope.launch {
        dataStoreRepository.getEmail()
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit("")
                } else {
                    throw it
                }
            }
            .collect { _emailState.value = it }
    }

}