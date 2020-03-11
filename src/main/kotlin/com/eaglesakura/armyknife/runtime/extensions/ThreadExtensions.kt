package com.eaglesakura.armyknife.runtime.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlin.concurrent.thread

/**
 *  run & await `newThreadBlock` action on new thread..
 *  e.g.)
 *  suspend fun example() {
 *      blockingThread("newThreadAction") {
 *          // execute thread.
 *      }
 *  }
 */
suspend fun <T> blockingThread(name: String, newThreadBlock: CoroutineScope.() -> T): T = coroutineScope {
    val channel = Channel<T>()
    val scope = this
    thread(name = name) {
        try {
            channel.send(Dispatchers.Default, newThreadBlock(scope))
        } catch (e: Throwable) {
            channel.cancelByError(e)
        }
    }

    channel.receiveOrError()
}