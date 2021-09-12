package com.mnowo.surveyapp.presentation.LoginScreen

import android.util.Log.d
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnowo.surveyapp.common.Resource
import com.mnowo.surveyapp.domain.repository.SurveyRepository
import com.mnowo.surveyapp.domain.use_case.SignInWithEmailAndPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase
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

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

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

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            when {
                email == "" -> {
                    if (password == "") {
                        _isErrorEmail.value = true
                        _isErrorPassword.value = true
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
                    signInWithEmailAndPasswordUseCase.invoke(email, password).onEach {
                        when (it) {
                            is Resource.Loading -> {
                                _state.value = LoginState(isLoading = true)
                                d("Login", "Loading")
                            }
                            is Resource.Success -> {
                                _state.value = LoginState(isLoggedIn = true)
                                d("Login", "success")
                            }
                            is Resource.Error -> {
                                _state.value = LoginState(
                                    error = it.message ?: "An unexpected error occurred"
                                )
                                d("Login", "Error")
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

}
