package com.movieapp.domain.usecase.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class UseCaseBase : CoroutineScope {

    protected val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    abstract fun execute()

    open fun cancel() {
        if (job.isActive) {
            job.cancel()
        }
    }
}