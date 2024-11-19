package com.shurdev.my_plants.screens.create.models

import com.shurdev.ui_kit.viewModel.form.FormValidationError

data class CreatePlantFormValidationError(
    val name: String,
) : FormValidationError()