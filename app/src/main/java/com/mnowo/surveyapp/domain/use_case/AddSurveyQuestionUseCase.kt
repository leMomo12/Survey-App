package com.mnowo.surveyapp.domain.use_case

import android.util.Log.d
import com.mnowo.surveyapp.common.Resource
import com.mnowo.surveyapp.domain.model.SurveyQuestion
import com.mnowo.surveyapp.domain.repository.SurveyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class AddSurveyQuestionUseCase @Inject constructor(
    private val surveyRepository: SurveyRepository
) {

    suspend operator fun invoke(surveyQuestion: SurveyQuestion): Flow<Resource<Boolean>> = flow {
        try {
            d("AddSurvey", "questionTitle: ${surveyQuestion.questionTitle} , questionAnswer: ${surveyQuestion.questionAnswers}")
            emit(Resource.Loading<Boolean>())
            surveyRepository.addSurveyQuestion(surveyQuestion)
            emit(Resource.Success<Boolean>(true))
        } catch (e: Exception) {
            emit(Resource.Error<Boolean>(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}