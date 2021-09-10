package com.mnowo.surveyapp.presentation.LoginScreen

import android.util.Log.d
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnowo.surveyapp.common.Resource
import com.mnowo.surveyapp.domain.use_case.SignInWithEmailAndPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase
) : ViewModel() {

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    private val _emailText = mutableStateOf("")
    val emailText: State<String> = _emailText

    private val _state = mutableStateOf(LoginState())
    val state : State<LoginState> = _state

    fun setPasswordText(password: String) {
        _passwordText.value = password
    }

    fun setEmailText(email: String) {
        _emailText.value = email
    }

    fun loginWithEmailAndPassword(email: String, password: String) {
        when {
            email == "" -> {
                d("Login", "Email is empty")
            }
            password == "" -> {
                d("Login", "Password is empty")
            }
            else -> {
                d("Login", "Nothing empty, password: $password, email: $email")
                signInWithEmailAndPasswordUseCase.invoke(email, password).onEach { result ->
                    when(result) {
                        is Resource.Loading -> {
                            _state.value = LoginState(isLoading = true)
                        }
                        is Resource.Success -> {
                            _state.value = LoginState(isLoggedIn = true)
                        }
                        is Resource.Error -> {
                            _state.value = LoginState(
                                error = result.message ?: "An unexpected error occurred"
                            )
                        }
                    }
                }
            }
        }
    }
}