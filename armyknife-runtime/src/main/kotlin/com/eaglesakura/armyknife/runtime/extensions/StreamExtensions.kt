package com.eaglesakura.armyknife.runtime.extensions

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import kotlinx.coroutines.yield

/**
 * Read all data from InputStream.
 * This method supported for Cancel in read process.
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
@Throws(IOException::class)
suspend fun InputStream.readBytesInCoroutines(readOnce: Int = 1024): ByteArray {
    val out = ByteArrayOutputStream(readOnce)
    val buffer = ByteArray(readOnce)

    do {
        val bytes = read(buffer)
        if (bytes < 0) {
            yield()
            return out.toByteArray()
        } else {
            yield()
            out.write(buffer, 0, bytes)
        }
    } while (true)
}
