package com.mnowo.surveyapp.presentation.AddSurveyQuestionScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mnowo.surveyapp.presentation.theme.blue

@Composable
fun AddSurveyQuestionScreen(
    navController: NavController,
    viewModel: AddSurveyQuestionViewModel = hiltViewModel()
) {
    LazyColumn {
        item {
            Text(
                text = "Add new Survey questions",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp)
            )
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
                    modifier = Modifier.padding(5.dp)
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
            TextField(
                value = title,
                onValueChange = { title = it },
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
    ) {
        TextField(value = description, onValueChange = { description = it })
    }
}