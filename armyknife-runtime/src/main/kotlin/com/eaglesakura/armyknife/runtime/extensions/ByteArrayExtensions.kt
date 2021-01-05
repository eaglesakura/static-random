package com.eaglesakura.armyknife.runtime.extensions

import com.eaglesakura.armyknife.runtime.Base64Impl
import java.security.MessageDigest

/**
 * Byte array to base64 encoded string.
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
fun ByteArray.encodeBase64(): String = Base64Impl.byteArrayToString(this)

/**
 * Byte array to MD5 encoded string.
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
fun ByteArray.encodeMD5(): String = MessageDigest.getInstance("MD5").digest(this).toHexString()

/**
 * Byte array to SHA1 encoded string.
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
fun ByteArray.encodeSHA1(): String = MessageDigest.getInstance("SHA-1").digest(this).toHexString()

/**
 * Byte array to SHA1 encoded string.
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
fun ByteArray.encodeSHA256(): String = MessageDigest.getInstance("SHA-256").digest(this).toHexString()

/**
 * Byte array to SHA1 encoded string.
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
fun ByteArray.encodeSHA512(): String = MessageDigest.getInstance("SHA-512").digest(this).toHexString()

/**
 * Byte array to hex string.
 * This method returns hex string, it uses characters only "[0-9,a-f]"
 * Alphabet characters are lower-case.
 *
 * example) [0x01, 0x02, FF] -> "0102ff"
 *
 * @author @eaglesakura
 * @link https://github.com/eaglesakura/armyknife-runtime
 */
fun ByteArray.toHexString(): String {
    val sBuffer = StringBuffer(size * 2)
    for (b in this) {
        val s = Integer.toHexString(b.toInt() and 0xff)
        if (s.length == 1) {
            sBuffer.append('0')
        }
        sBuffer.append(s)
    }

    return sBuffer.toString()
}
