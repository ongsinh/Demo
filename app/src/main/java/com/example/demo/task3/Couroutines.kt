package com.example.demo.task3

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlin.random.Random

fun main() = runBlocking {
    /*launch { doWorld() }
    println("Hello")*/
    doWorld()
    val job = launch {
        try{
            withTimeout(2000){
                repeat(5){ i ->
                    delay(1000)
                    println("job Iam sleeping $i")
                }
            }
        }finally {
            withContext(NonCancellable){
                println("job running finally")
                delay(1000)
                println("job finally done")
            }
        }
    }
    job.join()
    println("Kotlin")

    //cancellation
    val startTime = System.currentTimeMillis()
    val job2 = launch(Dispatchers.Default){
        var nextPrintTime = startTime
        var i = 0
        while(i < 5){
            if(System.currentTimeMillis() >= nextPrintTime){
                println("Job2 Iam sleeping $i")
                nextPrintTime += 500L
                i++
            }
        }
    }
    delay(1300)
    println("Main I am tired of waiting")
    job2.cancelAndJoin()
    println("Main coroutine ")
}

//suspend fun doWorld() {
//    delay(1000)
//    println("World")
//}

suspend fun doWorld() = coroutineScope {
    launch {
        delay(1000)
        println("World1")
    }

    launch {
        delay(2000)
        println("World2")
    }

    println("Hello")
}


