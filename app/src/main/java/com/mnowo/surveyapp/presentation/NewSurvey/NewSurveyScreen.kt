package com.mnowo.surveyapp.presentation.NewSurvey

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Alignment.Companion.TopStart
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
fun NewSurveyScreen(navController: NavController, viewModel: NewSurveyViewModel = hiltViewModel()) {

    val istokweb = FontFamily(
        Font(R.font.istokweb_bold, FontWeight.Bold),
        Font(R.font.istokweb_bolditalic, FontWeight.ExtraBold),
        Font(R.font.istokweb_italic, FontWeight.Normal),
        Font(R.font.istokweb_regular, FontWeight.Medium),
    )

    if (viewModel.successfulAdded.value) {
        viewModel.setSuccessFullAdded(false)
        navController.navigate(Screen.AddSurveyQuestionScreen.route)
    }

    Scaffold(
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
            ) {
                IconButton(onClick = { navController.navigate(Screen.MainScreen.route) }) {
                    Icon(
                        Icons.Default.ArrowBackIos,
                        contentDescription = "",
                    )
                }
                Text(
                    text = "Get started,",
                    fontSize = 32.sp,
                    fontFamily = istokweb,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 25.dp, start = 20.dp, end = 20.dp)
                )
                Text(
                    text = "and create a new Survey",
                    fontSize = 32.sp,
                    fontFamily = istokweb,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                )
                Spacer(modifier = Modifier.padding(vertical = 20.dp))
                OutlinedTextField(
                    value = viewModel.surveyTitle.value,
                    onValueChange = {
                        viewModel.setSurveyTitle(it)
                    },
                    label = {
                        Text(text = "Enter survey title")
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Title, "")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    singleLine = true,
                    isError = viewModel.surveyTitleError.value
                )
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                OutlinedTextField(
                    value = viewModel.surveyDescription.value,
                    onValueChange = {
                        viewModel.setSurveyDescription(it)
                    },
                    label = {
                        Text(text = "Enter survey description")
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Description, "")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    singleLine = true,
                    isError = viewModel.surveyDescriptionError.value
                )
                Spacer(modifier = Modifier.padding(vertical = 40.dp))
                Button(
                    onClick = {
                        viewModel.checkInputs(
                            viewModel.surveyTitle.value,
                            viewModel.surveyDescription.value
                        )
                    },
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                ) {
                    Text(text = "Next Step", fontFamily = istokweb, fontSize = 25.sp)
                }
            }
        }
    )
}

