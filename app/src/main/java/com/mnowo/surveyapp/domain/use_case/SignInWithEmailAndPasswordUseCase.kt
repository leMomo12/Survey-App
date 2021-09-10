package com.mnowo.surveyapp.domain.use_case

import android.util.Log.d
import com.mnowo.surveyapp.common.Resource
import com.mnowo.surveyapp.domain.repository.SurveyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class SignInWithEmailAndPasswordUseCase @Inject constructor(
    private val repository: SurveyRepository
) {

    operator fun invoke(email: String, password: String) : Flow<Resource<Boolean>> = flow {
        try {
            d("Login", "Use case $email , $password ")
            emit(Resource.Loading<Boolean>())
            val status = repository.signInWithEmailAndPassword(email, password)
            emit(Resource.Success<Boolean>(status))
        } catch (e: Exception) {
            d("Login", "$e")
            emit(Resource.Error<Boolean>(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}