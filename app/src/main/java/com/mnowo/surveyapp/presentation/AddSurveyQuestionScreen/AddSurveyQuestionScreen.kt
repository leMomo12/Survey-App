package com.mnowo.surveyapp.presentation.AddSurveyQuestionScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mnowo.surveyapp.common.Constants
import com.mnowo.surveyapp.presentation.theme.blue
import com.mnowo.surveyapp.presentation.util.Screen

@Composable
fun AddSurveyQuestionScreen(
    navController: NavController,
    viewModel: AddSurveyQuestionViewModel = hiltViewModel()
) {
    LazyColumn {
        item {
            Row(Modifier.padding(start = 10.dp, top = 10.dp)) {
                IconButton(onClick = { navController.navigate(Screen.NewSurveyScreen.route) }) {
                    Icon(Icons.Default.ArrowBackIos, "")
                }
            }
        }
        item {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Add new",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    Text(
                        text = "Survey questions",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
        }
        items(viewModel.listCount.value) {
            SurveyQuestionListItem(viewModel)
        }
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp), horizontalArrangement = Arrangement.Center
            ) {
                FloatingActionButton(
                    onClick = { viewModel.setListCount() },
                    backgroundColor = blue,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(Icons.Default.Add, "")
                }
            }
        }
    }
}

@Composable
fun SurveyQuestionListItem(viewModel: AddSurveyQuestionViewModel) {
    var title by remember {
        mutableStateOf("")
    }
    var listCount by remember {
        mutableStateOf(2)
    }
    Row(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            OutlinedTextField(
                value = title,
                onValueChange = {  if (it.length <= Constants.MAX_SURVEY_TITLE_LETTERS) title = it },
                label = { Text(text = "Add question title") }
            )
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            listCount.let {
                for (i in 0 until listCount) {
                    SurveyQuestionNestedListItem()
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            Icon(Icons.Default.Add, "", modifier = Modifier.clickable {
                listCount += 1
            })
        }
    }
}

@Composable
fun SurveyQuestionNestedListItem() {
    var description by remember {
        mutableStateOf("")
    }
    Card(
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .background(Color.White)
    ) {
        TextField(
            value = description,
            onValueChange = {
                if (it.length <= Constants.MAX_SURVEY_QUESTION_LETTERS) description = it
            },
            label = {
                Text(text = "Enter answer option")
            },
            singleLine = true,

            )
    }
}