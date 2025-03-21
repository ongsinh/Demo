package com.example.demo.task2.`interface`

interface BookRepository {
    fun addBook()
    fun deleteBook(bookId : Int): Boolean
    fun displayBook()
    fun updateBook()
    fun searchBookByTitle()
    fun countsBook()
}