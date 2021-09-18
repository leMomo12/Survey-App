package com.mnowo.surveyapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "newSurvey_table")
data class NewSurvey(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var description: String
)
