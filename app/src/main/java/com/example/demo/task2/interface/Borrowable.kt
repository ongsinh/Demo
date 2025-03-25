package com.example.demo.task2.`interface`

interface Borrowable {
    suspend fun borrowBook()
    suspend fun displayBookBorrowed()
    fun returnBook()
    fun getBookBorrowedUser()
}