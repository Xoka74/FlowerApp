package com.shurdev.ui_kit.viewModel.form

abstract class Form<T : FormValidationError> {
    abstract fun validate(): T?
}