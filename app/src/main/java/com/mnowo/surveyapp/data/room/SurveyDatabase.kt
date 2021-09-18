package com.mnowo.surveyapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mnowo.surveyapp.data.room.dao.SurveyDao
import com.mnowo.surveyapp.domain.model.NewSurvey

@Database(
    entities = [NewSurvey::class],
    version = 1,
    exportSchema = false
)
abstract class SurveyDatabase : RoomDatabase() {

    abstract fun surveyDao(): SurveyDao
}