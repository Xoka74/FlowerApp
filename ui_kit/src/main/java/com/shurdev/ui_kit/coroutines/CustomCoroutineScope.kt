package com.shurdev.ui_kit.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CustomCoroutineScope(
    private val job: Job = Job()
) {
    private val scope = CoroutineScope(Dispatchers.IO + job)

    fun launch(task: suspend () -> Unit) {
        scope.launch {
            task()
        }
    }

    fun cancel() {
        job.cancel()
    }
}