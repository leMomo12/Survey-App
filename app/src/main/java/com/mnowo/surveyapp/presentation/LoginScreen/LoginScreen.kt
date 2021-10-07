package com.mnowo.surveyapp.presentation.LoginScreen


import android.util.Log.d
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navArgument
import com.mnowo.surveyapp.R
import com.mnowo.surveyapp.common.TestTags
import com.mnowo.surveyapp.presentation.util.Screen
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val istokweb = FontFamily(
        Font(R.font.istokweb_bold, FontWeight.Bold),
        Font(R.font.istokweb_bolditalic, FontWeight.ExtraBold),
        Font(R.font.istokweb_italic, FontWeight.Normal),
        Font(R.font.istokweb_regular, FontWeight.Medium),
    )

    if (state.isLoggedIn) {
        viewModel.setStateIsFinished(true)
        if (!state.isFinished) {
            navController.navigate(Screen.MainScreen.route)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Spacer(modifier = Modifier.padding(vertical = 30.dp))
            Text(
                text = "Welcome to",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = istokweb
            )
            Text(
                text = "Surveyticon",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = istokweb
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = "Please login for continue",
                fontWeight = FontWeight.Light,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            OutlinedTextField(
                value = viewModel.emailText.value,
                onValueChange = {
                    viewModel.setEmailText(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .testTag(TestTags.LOGIN_EMAIL),
                label = {
                    Text(text = "Enter E-Mail", fontWeight = FontWeight.Light)
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Email, contentDescription = "")
                },
                singleLine = true,
                isError = viewModel.isErrorEmail.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                enabled = viewModel.uiEnable.value
            )
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            OutlinedTextField(
                value = viewModel.passwordText.value,
                visualTransformation = if (viewModel.passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                onValueChange = {
                    viewModel.setPasswordText(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .testTag(TestTags.LOGIN_PASSWORD),
                label = {
                    Text(text = "Enter password", fontWeight = FontWeight.Light)
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Lock, contentDescription = "")
                },
                singleLine = true,
                isError = viewModel.isErrorPassword.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                enabled = viewModel.uiEnable.value,
                trailingIcon = {
                    val image = if (viewModel.passwordVisibility.value)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    IconButton(onClick = {
                        viewModel.setPasswordVisibility(!viewModel.passwordVisibility.value)
                    }) {
                        Icon(imageVector = image, "")
                    }
                }
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Button(
                onClick = {
                    viewModel.signInWithEmailAndPassword(
                        viewModel.emailText.value,
                        viewModel.passwordText.value
                    )
                },
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .testTag(TestTags.LOGIN_BUTTON),
                enabled = viewModel.uiEnable.value
            ) {
                Text(text = "Sign in", fontFamily = istokweb, fontSize = 25.sp)
            }
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Text(text = "Don't have an account?")
            TextButton(
                onClick = { navController.navigate(Screen.RegisterScreen.route) },
                enabled = viewModel.uiEnable.value,
                modifier = Modifier.testTag(TestTags.LOGIN_SCREEN_TEXT_BUTTON)
            ) {
                Text(text = "Register here")
            }
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            if (state.error.isNotBlank()) {
                d("Login", "Error: ${state.error}")
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        "${state.error}",
                        duration = SnackbarDuration.Short
                    )
                }
            }
            d("Status", "heeyyy")
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp), contentAlignment = Center
        ) {

            if (state.isLoading) {
                d("Login", "isLoading")
                viewModel.setUiEnable(false)
                CircularProgressIndicator(modifier = Modifier.testTag(TestTags.LOGIN_PROGRESSBAR))
            } else {
                viewModel.setUiEnable(true)
            }
        }
    }
}






