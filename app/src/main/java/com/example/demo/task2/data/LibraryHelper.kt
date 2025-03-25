package com.example.demo.task2.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object LibraryHelper {

    fun <T> displayList(item: List<T>) {
        item.forEach { println(it) }
    }

    suspend fun getBookTitle(): String = withContext(Dispatchers.IO) {
        var bookTitle: String?
        do {
            println("Enter book title: ")
            bookTitle = readlnOrNull()?.takeIf { it.isNotBlank() }
        } while (bookTitle == null)

        return@withContext bookTitle
    }

    suspend fun getAuthor(): String = withContext(Dispatchers.IO) {
        var author: String?
        do {
            println("Enter book author: ")
            author = readlnOrNull()?.takeIf { it.isNotBlank() }
        } while (author == null)

        return@withContext author
    }


    suspend fun getPublicationYear(): Int = withContext(Dispatchers.IO) {
        var publicationYear: Int?
        do {
            println("Enter publication year:")
            publicationYear = readlnOrNull()?.toIntOrNull()
        } while (publicationYear == null)

        return@withContext publicationYear
    }

    suspend fun getPublisher(): String = withContext(Dispatchers.IO) {
        var publisher: String?
        do {
            println("Enter publisher: ")
            publisher = readlnOrNull()?.takeIf { it.isNotBlank() }
        } while (publisher == null)

        return@withContext publisher
    }

    suspend fun getFormat(): String = withContext(Dispatchers.IO) {
        var format: String?
        do {
            println("Enter format:")
            format = readlnOrNull()?.takeIf { it.isNotBlank() }
        } while (format == null)

        return@withContext format
    }

    suspend fun getPageNumber(): Int = withContext(Dispatchers.IO) {
        var pageNumber: Int?
        do {
            println("Enter page number:")
            pageNumber = readlnOrNull()?.toIntOrNull()
        } while (pageNumber == null)

        return@withContext pageNumber
    }

    suspend fun getBookId(): Int = withContext(Dispatchers.IO) {
        var bookId: Int?
        do {
            println("Enter book id:")
            bookId = readlnOrNull()?.toIntOrNull()
        } while (bookId == null)

        return@withContext bookId
    }
}