package com.mnowo.surveyapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "surveyQuestion_table")
data class SurveyQuestion(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var questionTitle: String,
    var questionAnswers: String
)
