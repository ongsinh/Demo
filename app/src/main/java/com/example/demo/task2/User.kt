package com.example.demo.task2

data class User(
    val id : Int,
    val name : String,
    val sachDaMuon : MutableList<Book> = mutableListOf()
)