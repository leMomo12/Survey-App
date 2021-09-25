package com.mnowo.surveyapp.domain.use_case

import com.mnowo.surveyapp.domain.repository.SurveyRepository
import javax.inject.Inject

class DeleteSurveyQuestionUseCase @Inject constructor(
    private val surveyRepository: SurveyRepository
) {

    suspend operator fun invoke() {
        surveyRepository.deleteSurveyQuestion()
    }
}