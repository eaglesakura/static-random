package com.eaglesakura.armyknife.runtime.extensions

import java.util.concurrent.TimeUnit
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

class ThreadExtensionsKtTest {

    @Test
    fun success_blockingThread() = runBlocking {
        val result = blockingThread("newThread") {
            assertEquals("newThread", Thread.currentThread().name)
            "ok"
        }

        assertEquals("ok", result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun fail_blockingThread(): Unit = runBlocking {
        blockingThread("failThread") {
            throw IllegalArgumentException("example error")
        }
    }

    @Test(expected = TimeoutCancellationException::class)
    fun timeout_blockingThread(): Unit = runBlocking {
        withTimeout(TimeUnit.SECONDS.toMillis(1)) {
            blockingThread("timeout") {
                Thread.sleep(30 * 1000)
            }
        }
    }

    @Test
    fun timeout_checkInThread(): Unit = runBlocking {

        val channel = Channel<String>()

        try {
            withTimeout(TimeUnit.SECONDS.toMillis(1)) {
                blockingThread("timeout") {
                    while (isActive) {
                        Thread.sleep(1)
                    }
                    channel.send(Dispatchers.Default, "inactive")
                }
            }

            fail()
        } catch (e: TimeoutCancellationException) {
        }

        assertEquals("inactive", channel.receiveOrError())
    }

    @Test(expected = CancellationException::class)
    fun cancel_inOtherThread(): Unit = runBlocking {
        blockingThread("cancel") {
            Thread.sleep(1)
            cancel(CancellationException("cancel from other thread"))
        }
        fail()
    }
}
