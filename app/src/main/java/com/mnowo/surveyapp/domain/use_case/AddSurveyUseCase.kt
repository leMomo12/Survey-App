package com.mnowo.surveyapp.domain.use_case

import com.mnowo.surveyapp.common.Resource
import com.mnowo.surveyapp.domain.model.Survey
import com.mnowo.surveyapp.domain.repository.SurveyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddSurveyUseCase @Inject constructor(
    private val repository: SurveyRepository
) {

    operator fun invoke(survey: Survey) : Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            val status = repository.addSurvey(survey)
            emit(Resource.Success<Boolean>(status))
        } catch (e: Exception) {
            emit(Resource.Error<Boolean>(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}