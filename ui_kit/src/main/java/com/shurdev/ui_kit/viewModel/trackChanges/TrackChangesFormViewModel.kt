package com.shurdev.ui_kit.viewModel.trackChanges

import com.shurdev.ui_kit.viewModel.form.FormValidationError
import com.shurdev.ui_kit.viewModel.form.FormViewModel

abstract class TrackChangesFormViewModel<TError : FormValidationError, TData : TrackChangesForm<TError>>(
    initialData: TData,
) : FormViewModel<TError, TData>(initialData) {
    private var lastSavedFormData: TData = initialData

    protected fun saveChanges() {
        lastSavedFormData = formData
    }

    fun hasChanges(): Boolean = formData.hasChanges(lastSavedFormData)
}