package com.sopt.dive.core.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

fun <T> Flow<T>.stateInWhileSubscribed(
    scope: CoroutineScope,
    initialValue: T,
    timeout: Long = 5_000L
): StateFlow<T> =
    this.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(timeout),
        initialValue = initialValue,
    )
