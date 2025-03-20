package com.example.demo.task2.model

abstract class BookBase(
    open val id: Int,
    open var bookTitle: String,
    open var author: String,
    open var publicationYear: Int,
    open var genre: String,
    open var publisher: String,
    open var bookStatus: Boolean
) {
    abstract fun displayInfo(): String
}