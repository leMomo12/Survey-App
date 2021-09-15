package com.mnowo.surveyapp.presentation.AddSurveyQuestionScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddSurveyQuestionViewModel @Inject constructor(

) :ViewModel() {

    private val _listCount = mutableStateOf(1)
    val listCount: State<Int> = _listCount

    fun setListCount() {
        _listCount.value += 1
    }
}
