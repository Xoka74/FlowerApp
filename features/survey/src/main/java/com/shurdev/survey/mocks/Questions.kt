package com.shurdev.survey.mocks

import com.shurdev.domain.models.survey.Question

val SINGLE_ANSWER_OPTIONS = listOf(
    "Да",
    "Скорее да",
    "Не знаю",
    "Скорее нет",
    "Нет"
)

val QUESTIONS = listOf(
    Question(
        id = 1,
        question = "Вы живете в теплом регионе?",
        answerOptions = SINGLE_ANSWER_OPTIONS
    ),
    Question(
        id = 2,
        question = "У вас есть аллергия на цветы?",
        answerOptions = SINGLE_ANSWER_OPTIONS
    ),
    Question(
        id = 3,
        question = "Сколько цветов вы собираетесь выращивать?",
        answerOptions = SINGLE_ANSWER_OPTIONS
    ),
    Question(
        id = 4,
        question = "У вас есть дети?",
        answerOptions = SINGLE_ANSWER_OPTIONS
    ),
    /*QuestionWithSingleAnswer(
        id = 5,
        question = "У вас есть домашние животные?",
        answerOptions = SINGLE_ANSWER_OPTIONS
    ),*/
    /*QuestionWithMultipleAnswers(
        id = 6,
        question = "Какие виды цветов вы предпочитаете?",
        answerOptions = listOf(
            "Колючие", "Зеленые", "Ароматные"
        )
    ),
    QuestionWithCustomAnswer(
        id = 7,
        question = "Укажите, какой у вас знак зодиака",
        answerOptions = ""
    )*/
)