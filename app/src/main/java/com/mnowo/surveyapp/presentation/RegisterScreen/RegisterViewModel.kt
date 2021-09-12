package com.mnowo.surveyapp.presentation.RegisterScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnowo.surveyapp.common.Resource
import com.mnowo.surveyapp.domain.use_case.RegisterWithEmailAndPasswordUseCase
import com.mnowo.surveyapp.presentation.LoginScreen.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerWithEmailAndPasswordUseCase: RegisterWithEmailAndPasswordUseCase
) : ViewModel() {

    private val _passwordVisibility = mutableStateOf(false)
    val passwordVisibility: State<Boolean> = _passwordVisibility

    private val _uiEnable = mutableStateOf(true)
    val uiEnable: State<Boolean> = _uiEnable

    private val _isErrorPassword = mutableStateOf(false)
    val isErrorPassword: State<Boolean> = _isErrorPassword

    private val _isErrorEmail = mutableStateOf(false)
    val isErrorEmail: State<Boolean> = _isErrorEmail

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    private val _emailText = mutableStateOf("")
    val emailText: State<String> = _emailText

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    fun setPasswordText(password: String) {
        _passwordText.value = password
    }

    fun setEmailText(email: String) {
        _emailText.value = email
    }

    fun setUiEnable(status: Boolean) {
        _uiEnable.value = status
    }

    fun setPasswordVisibility(isVisible: Boolean) {
        _passwordVisibility.value = isVisible
    }

    fun registerWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            when {
                email == "" -> {
                    if(password == "") {
                        _isErrorPassword.value = true
                        _isErrorEmail.value = true
                    } else {
                        _isErrorEmail.value = true
                        _isErrorPassword.value = false
                    }
                }
                password == "" -> {
                    _isErrorPassword.value = true
                    _isErrorEmail.value = false
                }
                else -> {
                    _isErrorEmail.value = false
                    _isErrorPassword.value = false
                    registerWithEmailAndPasswordUseCase.invoke(email, password).onEach {
                        when (it) {
                            is Resource.Loading -> {
                                _state.value = RegisterState(isLoading = true)
                            }
                            is Resource.Success -> {
                                _state.value = RegisterState(isRegistered = true)
                            }
                            is Resource.Error -> {
                                _state.value = RegisterState(error = "An unexpected error occurred")
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }
        }
    }
}