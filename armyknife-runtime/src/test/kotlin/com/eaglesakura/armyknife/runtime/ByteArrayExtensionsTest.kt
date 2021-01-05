package com.eaglesakura.armyknife.runtime

import com.eaglesakura.armyknife.runtime.extensions.toHexString
import org.junit.Assert.assertEquals
import org.junit.Test

class ByteArrayExtensionsTest {
    @Test
    fun toHex() {
        byteArrayOf().toHexString().also {
            assertEquals("", it)
        }
        byteArrayOf(0x01, 0x02, 0xFF.toByte()).toHexString().also {
            assertEquals("0102ff", it)
        }
    }
}
