package com.eaglesakura.armyknife.runtime.coroutines

import com.eaglesakura.armyknife.runtime.Random
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import org.junit.Ignore
import org.junit.Test
import java.util.concurrent.TimeUnit

class FlexibleThreadPoolDispatcherTest {

    @Test
    fun launch_success() = runBlocking {
        val dispatcher = FlexibleThreadPoolDispatcher.newDispatcher(5, 10, TimeUnit.MILLISECONDS)

        withContext(dispatcher) {
            delay(10)
            println("dispatch success")
        }
    }

    @Test
    fun launch_success_with_current() = runBlocking {
        val dispatcher = FlexibleThreadPoolDispatcher.newDispatcher(5, 10, TimeUnit.MILLISECONDS)

        withContext(coroutineContext + dispatcher) {
            delay(10)
            println("dispatch success")
        }
    }

    @Ignore
    @Test
    fun launch_auto_scale() = runBlocking {
        val dispatcher = FlexibleThreadPoolDispatcher.newDispatcher(5, 10, TimeUnit.MILLISECONDS)

        val loop = 10000
        val channel = Channel<Int>(loop)
        for (i in 0..loop) {
            GlobalScope.launch(dispatcher) {
                delay((Random.int32() % 10 + 1).toLong())
                channel.send(i)
            }
        }

        // Can't Run this test
//        while (!channel.isFull) {
//            delay(1)
//        }
    }
}