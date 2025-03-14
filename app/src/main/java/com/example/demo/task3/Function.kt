package com.example.demo.task3

fun operation(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

fun main() {
    val result = operation(3, 6) { a, b -> a + b }
    println(result)

    val result1 = operation(5, 9) { a, b -> a * b }
    println(result1)
}

