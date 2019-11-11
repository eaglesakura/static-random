package com.eaglesakura.armyknife.runtime.extensions

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RuntimeExtensionsKtTest {

    @Test
    fun withRetry_noFail() = runBlocking {
        var count = 0
        withRetry(times = 10) {
            ++count
        }

        assertEquals(1, count)
    }

    @Test
    fun withRetry() = runBlocking {
        var count = 0
        withRetry(times = 10) {
            ++count
            check(count == 10)
        }

        assertEquals(10, count)
    }

    @Test(expected = IllegalStateException::class)
    fun withRetry_cancel() = runBlocking {
        var count = 0
        try {
            withRetry(times = 10) {
                ++count
                throw IllegalStateException()
            }

            assertEquals(10, count)
        } finally {
            assertEquals(10, count)
        }
    }
}