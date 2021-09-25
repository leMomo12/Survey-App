package com.mnowo.surveyapp.data.local

import androidx.room.*
import com.mnowo.surveyapp.domain.model.NewSurvey
import com.mnowo.surveyapp.domain.model.SurveyQuestion

@Dao
interface SurveyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSurveyTitleAndDescriptionCaching(newSurvey: NewSurvey)

    @Query("SELECT * FROM newSurvey_table")
    suspend fun getSurveyTitleAndDescription() : NewSurvey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSurveyQuestion(surveyQuestion: SurveyQuestion)

    @Query("DELETE FROM surveyQuestion_table")
    suspend fun deleteSurveyQuestion()
}