package com.example.demo.task2.`interface`

import com.example.demo.task2.model.BookBase


interface BookRepository {
    suspend fun addBook()
    suspend fun deleteBook(bookId: Int): Boolean
    fun displayBook()
    suspend fun updateBook()
    suspend fun searchBookByTitle()
    fun countsBook()
    fun filterBookByYear(): List<BookBase>?
    fun filterBookByPageNumber(): List<BookBase>?
    fun filterBookByFormat(format: String): List<BookBase>?
    fun filterBookByFormatAndYear(format: String, year: Int): List<BookBase>?
    fun sortByBookByYear(): List<BookBase>?
}