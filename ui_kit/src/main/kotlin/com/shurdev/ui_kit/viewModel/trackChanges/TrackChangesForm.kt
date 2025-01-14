package com.shurdev.ui_kit.viewModel.trackChanges

import com.shurdev.domain.forms.Form
import com.shurdev.domain.forms.FormValidationError

abstract class TrackChangesForm<T : FormValidationError> : Form<T>() {
    abstract fun hasChanges(other: TrackChangesForm<T>): Boolean
}