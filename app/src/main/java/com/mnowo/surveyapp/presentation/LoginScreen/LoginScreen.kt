package com.mnowo.surveyapp.presentation.LoginScreen


import android.util.Log.d
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mnowo.surveyapp.R
import com.mnowo.surveyapp.presentation.util.Screen

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val state = viewModel.state.value

    val istokweb = FontFamily(
        Font(R.font.istokweb_bold, FontWeight.Bold),
        Font(R.font.istokweb_bolditalic, FontWeight.ExtraBold),
        Font(R.font.istokweb_italic, FontWeight.Normal),
        Font(R.font.istokweb_regular, FontWeight.Medium),
    )
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
            fontWeight = FontWeight.ExtraLight,
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
                .padding(start = 20.dp, end = 20.dp),
            label = {
                Text(text = "Enter E-Mail", fontWeight = FontWeight.Light)
            },
            leadingIcon = {
                Icon(Icons.Outlined.Email, contentDescription = "")
            }

        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        OutlinedTextField(
            value = viewModel.passwordText.value,
            onValueChange = {
                viewModel.setPasswordText(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            label = {
                Text(text = "Enter password", fontWeight = FontWeight.Light)
            },
            leadingIcon = {
                Icon(Icons.Outlined.Lock, contentDescription = "")
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Button(
            onClick = {
                viewModel.loginWithEmailAndPassword(
                    viewModel.emailText.value,
                    viewModel.passwordText.value
                )
            },
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text(text = "Sign In", fontFamily = istokweb, fontSize = 25.sp)
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(text = "Don't have an account?")
        TextButton(onClick = { navController.navigate(Screen.RegisterScreen.route) }) {
            Text(text = "Register here")
        }


        if(state.error.isNotBlank()) {
            d("Login", "Error: ${state.error}")
        }

        if(state.isLoading) {
            d("Login", "isLoading")
        }

    }
}


