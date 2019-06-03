package com.eaglesakura.armyknife.runtime

internal object Base64Impl {

    val byteArrayToString: (array: ByteArray) -> String

    val stringToByteArray: (str: String) -> ByteArray


    private const val ANDROID_URL_SAFE = 8

    private const val ANDROID_NO_WRAP = 2

    private const val ANDROID_NO_PADDING = 1

    private const val ANDROID_NO_CLOSE = 16

    private const val ANDROID_FLAG =
            ANDROID_NO_CLOSE or ANDROID_NO_PADDING or ANDROID_NO_WRAP or ANDROID_URL_SAFE

    init {
        val androidBase64 = try {
            Class.forName("android.util.Base64")
        } catch (err: ClassNotFoundException) {
            null
        }

        if (androidBase64 != null) {
            // for Android
            val encodeToString = androidBase64.getMethod("encodeToString")
            val decode = androidBase64.getMethod("decode")
            byteArrayToString = { bytArray -> encodeToString.invoke(androidBase64, bytArray, ANDROID_FLAG) as String }
            stringToByteArray = { str -> decode.invoke(androidBase64, str, ANDROID_FLAG) as ByteArray }
        } else {
            // for JVM
            byteArrayToString =
                    { byteArray -> java.util.Base64.getEncoder().encodeToString(byteArray) }
            stringToByteArray = { str -> java.util.Base64.getDecoder().decode(str) }
        }
    }
}