package com.mnowo.surveyapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mnowo.surveyapp.domain.model.NewSurvey

@Dao
interface SurveyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSurveyTitleAndDescriptionCaching(newSurvey: NewSurvey)

    @Query("SELECT * FROM newSurvey_table")
    suspend fun getSurveyTitleAndDescription() : NewSurvey
}