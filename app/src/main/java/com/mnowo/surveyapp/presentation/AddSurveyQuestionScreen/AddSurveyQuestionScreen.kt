package com.mnowo.surveyapp.presentation.AddSurveyQuestionScreen

import android.util.Log.d
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Title
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mnowo.surveyapp.common.Constants
import com.mnowo.surveyapp.domain.model.Survey
import com.mnowo.surveyapp.domain.model.SurveyQuestion
import com.mnowo.surveyapp.presentation.theme.blue
import com.mnowo.surveyapp.presentation.util.Screen

@Composable
fun AddSurveyQuestionScreen(
    navController: NavController,
    viewModel: AddSurveyQuestionViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        when {
            state.isAdded -> { d("AddSurvey", "Added successfully") }
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        IconButton(
            onClick = { navController.navigate(Screen.NewSurveyScreen.route) },
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp)
                .align(Start)
        ) {
            Icon(Icons.Default.ArrowBackIos, "")
        }
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text(text = "Add a new question", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(vertical = 25.dp))
        OutlinedTextField(
            value = viewModel.surveyTitle.value,
            onValueChange = {
                viewModel.setSurveyTitle(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            label = {
                Text(text = "Enter question title", fontWeight = FontWeight.Light)
            },
            leadingIcon = {
                Icon(Icons.Outlined.Title, contentDescription = "")
            },
            singleLine = true,
            isError = viewModel.itTitleError.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            enabled = viewModel.uiEnabled.value
        )
        Spacer(modifier = Modifier.padding(vertical = 15.dp))
        LazyColumn {
            items(viewModel.answerCount.value) {
                SurveyQuestionListItem(viewModel, state)
            }


            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { viewModel.increaseAnswerCount() }) {
                        Icon(Icons.Default.Add, "", tint = blue)
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 20.dp))
                    IconButton(onClick = { viewModel.decreaseAnswerCount() }) {
                        Icon(Icons.Default.Remove, "")
                    }
                }
            }

        }
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Button(onClick = {
            viewModel.setButtonClicked(true)
        }) {

        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp), contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            d("Login", "isLoading")
            viewModel.setUiEnabled(false)
            CircularProgressIndicator()
        } else {
            viewModel.setUiEnabled(true)
        }
    }
}

@Composable
fun SurveyQuestionListItem(viewModel: AddSurveyQuestionViewModel, state: AddSurveyQuestionState) {

    var answer by remember {
        mutableStateOf("")
    }

    if (state.buttonClicked) {
        val surveyQuestion = SurveyQuestion(0, viewModel.surveyTitle.value, answer)
        viewModel.addSurvey(surveyQuestion)
        viewModel.setButtonClicked(false)
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, start = 20.dp, end = 20.dp)
            .background(Color.White)
    ) {
        TextField(
            value = answer,
            onValueChange = {
                if (it.length <= Constants.MAX_SURVEY_QUESTION_LETTERS) answer = it
            },
            label = {
                Text(text = "Enter answer option")
            },
            singleLine = true,
            enabled = viewModel.uiEnabled.value
        )
    }
}