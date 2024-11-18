package com.shurdev.survey.utils

interface SurveyActionListener {
    fun onNextClick()
    fun onBackClick()
    fun onSkipClick()
    fun onAnswerClick(answerIndex: Int)
    fun onSwipe(targetPage: Int)
    fun onFinishSurvey()
}