package com.mnowo.surveyapp.presentation.SurveyListItem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnowo.surveyapp.presentation.theme.blue

@Composable
fun SurveyListItem(title: String, description: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(20.dp)) {
        Card(shape = RoundedCornerShape(20.dp), backgroundColor = blue, modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
            Column(Modifier.fillMaxSize()) {
                Text(text = title, fontSize = 25.sp, fontWeight = FontWeight.Black, fontFamily = FontFamily.Serif)
                Text(text = description, fontSize = 22.sp, fontWeight = FontWeight.Normal, fontFamily = FontFamily.Serif)
            }
        }
    }
}

