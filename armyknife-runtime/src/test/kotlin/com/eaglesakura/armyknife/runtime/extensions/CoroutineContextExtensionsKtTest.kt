package com.eaglesakura.armyknife.runtime.extensions

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

class CoroutineContextExtensionsKtTest {

    @Test
    fun withChildContext_test() {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        runBlocking {
            val channel = Channel<String>()

            val job = launch {
                withChildContext(scope.coroutineContext) {
                    withChildContext(Dispatchers.Default) {
                        try {
                            delay(1000 * 2)
                            fail()
                        } catch (e: CancellationException) {
                            e.printStackTrace()
                            withContext(NonCancellable) {
                                channel.send("OK")
                            }
                        }
                    }
                }
            }
            delay(1000)
            job.cancel(CancellationException("TestCancel"))

            assertEquals("OK", channel.receive())
            delay(2000)
        }
    }
}
