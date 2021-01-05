package com.eaglesakura.armyknife.runtime

import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

class OneshotRunnerTest {

    @Test
    fun oneshot() {
        val runner = OneshotRunner<String>()
        val first = runner.oneshot { Random.largeString() }
        val second = runner.oneshot {
            fail()
            "fail()"
        }
        assertEquals(first, second)
    }
}
