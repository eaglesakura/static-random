package com.eaglesakura.armyknife.runtime.extensions

import java.time.Duration
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Job

/**
 * Returns an job object from coroutines.
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
val CoroutineContext.job: Job
    get() = this[Job]!!

/**
 * Delay duration.
 */
suspend fun delay(duration: Duration) {
    kotlinx.coroutines.delay(duration.toMillis())
}
