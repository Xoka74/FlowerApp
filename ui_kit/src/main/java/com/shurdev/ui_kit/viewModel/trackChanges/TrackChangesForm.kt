package com.shurdev.ui_kit.viewModel.trackChanges

import com.shurdev.ui_kit.viewModel.form.Form
import com.shurdev.ui_kit.viewModel.form.FormValidationError

abstract class TrackChangesForm<T : FormValidationError> : Form<T>() {
    abstract fun hasChanges(other: TrackChangesForm<T>): Boolean
}