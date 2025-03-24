package com.example.demo.task2.`interface`

import com.example.demo.task2.model.BookBase


interface BookRepository {
    fun addBook()
    fun deleteBook(bookId: Int): Boolean
    fun displayBook()
    fun updateBook()
    fun searchBookByTitle()
    fun countsBook()
    fun filterBookByYear(): List<BookBase>?
    fun filterBookByPageNumber(): List<BookBase>?
    fun filterBookByFormat(format: String): List<BookBase>?
    fun filterBookByFormatAndYear(format: String, year: Int): List<BookBase>?
    fun sortByBookByYear(): List<BookBase>?
}