package com.eaglesakura.armyknife.runtime.extensions

import org.junit.Test
import kotlin.test.assertTrue

class ReflectionExtensionsKtTest {

    @Test
    fun instanceOf() {
        val item = ExtendsClass()
        assertTrue { item.instanceOf(ExtendsClass::class) }
        assertTrue { item.instanceOf(BaseClass::class) }
        assertTrue { item.instanceOf(Any::class) }
    }

    open class BaseClass
    open class ExtendsClass : BaseClass()
}