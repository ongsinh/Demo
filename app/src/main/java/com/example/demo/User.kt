package com.example.demo

data class User(
    val id : Int,
    val name : String,
    val sachDaMuon : MutableList<Book> = mutableListOf()
)