package com.shurdev.survey.utils

interface SurveyActionListener {
    fun onNextClick()
    fun onBackClick()
    fun onSkipClick()
    fun onAnswerClick(answerId: Int)
    fun onSwipe(targetPage: Int)
    fun onFinishSurvey()
}