package com.eaglesakura.armyknife.runtime.extensions

import kotlin.math.min
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay

/**
 * Auto retry function.
 */
suspend fun <T> withRetry(
    times: Int = Int.MAX_VALUE,
    initialDelay: Long = 100,
    maxDelay: Long = 1000,
    backOff: Double = 2.0,
    isRetryError: (e: Throwable) -> Boolean = { it !is CancellationException && it !is Error },
    block: suspend () -> T
): T {
    var run = 0
    var currentDelay = initialDelay.toDouble()
    var lastError: Throwable

    do {
        try {
            return block()
        } catch (e: Throwable) {
            lastError = e
            if (!isRetryError(e)) {
                throw e
            }
        }

        delay(currentDelay.toLong())
        currentDelay = min(currentDelay * backOff, maxDelay.toDouble())
        ++run
    } while (run < times)

    throw lastError
}
