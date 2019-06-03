package com.eaglesakura.armyknife.runtime.io

import kotlinx.coroutines.CancellationException
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import java.io.ByteArrayInputStream

class CancelableInputStreamTest {

    @Test
    fun readOnce() {
        val stream = CancelableInputStream(ByteArrayInputStream(ByteArray(1024)), { false }).also {
            it.bufferSize = 128
        }

        assertEquals(stream.read(ByteArray(512)), 128)
    }

    @Test
    fun readAll() {
        val stream = CancelableInputStream(ByteArrayInputStream(ByteArray(1024)), { false }).also {
            it.bufferSize = 4096
        }

        assertEquals(stream.read(ByteArray(4096)), 1024)
    }

    @Test(expected = CancellationException::class)
    fun readCancel() {
        val stream = CancelableInputStream(ByteArrayInputStream(ByteArray(1024)), { true }).also {
            it.bufferSize = 128
        }
        stream.read() // throw exception in function.
        fail()
    }
}