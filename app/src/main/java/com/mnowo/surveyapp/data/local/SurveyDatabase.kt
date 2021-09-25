package com.mnowo.surveyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mnowo.surveyapp.domain.model.NewSurvey
import com.mnowo.surveyapp.domain.model.SurveyQuestion

@Database(
    entities = [NewSurvey::class, SurveyQuestion::class],
    version = 4,
    exportSchema = false
)
abstract class SurveyDatabase : RoomDatabase() {

    abstract fun surveyDao(): SurveyDao
}