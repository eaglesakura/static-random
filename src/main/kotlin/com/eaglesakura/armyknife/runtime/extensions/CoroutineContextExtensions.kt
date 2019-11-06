package com.eaglesakura.armyknife.runtime.extensions

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import java.time.Duration
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

/**
 * Returns an job object from coroutines.
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/army-knife
 */
val CoroutineContext.job: Job
    get() = this[Job]!!

/**
 * Make a cancel-callback function from job.
 * Use to non-coroutine functions or in the java world.
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/army-knife
 */
fun CoroutineContext.asCancelCallback(): () -> Boolean {
    val currentJob = this.job
    // Jobの状態をチェックする.
    // Jobがない場合はキャンセルせずに実行終了を待つ.
    return { currentJob.isCancelled }
}

/**
 * Delay duration.
 */
suspend fun delay(duration: Duration) {
    kotlinx.coroutines.delay(duration.toMillis())
}

/**
 * run suspend function in child coroutine context.
 *
 * e.g.)
 * suspend fun example() {
 *      withChildContext(Dispatchers.IO) {
 *          try {
 *              // do heavy something...
 *          } catch(e: CancellationException) {
 *              log("cancel by parent coroutineContext")
 *              throw e
 *          }
 *      }
 * }
 */
public suspend fun <T> withChildContext(
    context: CoroutineContext,
    block: suspend CoroutineScope.() -> T
): T {
    val scope = CoroutineScope(coroutineContext)
    val deferred: Deferred<Pair<Any?, Throwable?>> = scope.async(context) {
        try {
            Pair(block(this), null)
        } catch (e: Throwable) {
            Pair(Unit, e)
        }
    }

    try {
        return deferred.await().let {
            if (it.second != null) {
                throw it.second!!
            }
            it.first as T
        }
    } catch (e: CancellationException) {
        deferred.cancel(CancellationException("withContextChild(context=$context/$e)"))
        throw e
    }
}