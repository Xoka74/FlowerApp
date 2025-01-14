package com.shurdev.my_plants.forms

import com.shurdev.domain.forms.FormValidationError
import com.shurdev.domain.forms.ValidationError

data class MyPlantFormValidationError(
    val nameError: ValidationError? = null,
) : FormValidationError()