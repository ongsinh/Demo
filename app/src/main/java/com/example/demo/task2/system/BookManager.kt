package com.example.demo.task2.system


import com.example.demo.task2.model.Book
import com.example.demo.task2.data.LibraryData
import com.example.demo.task2.`interface`.BookRepository
import com.example.demo.task2.model.BookBase
import com.example.demo.task2.model.EBook

class BookManager : BookRepository {
    override fun addBook() {
        println("Enter book title: ")
        val bookTitle = readlnOrNull()?.takeIf { it.isNotBlank() } ?: return

        println("Enter author: ")
        val author = readlnOrNull()?.takeIf { it.isNotBlank() } ?: return

        println("Enter publication year:")
        val publicationYear = readlnOrNull()?.toIntOrNull() ?: return

        println("Enter genre :")
        val genre = readlnOrNull()?.takeIf { it.isNotBlank() } ?: return

        println("Enter publisher ")
        val publisher = readlnOrNull()?.takeIf { it.isNotBlank() } ?: return

        println("Is this Ebook? (yes/no)")
        val isEbook = readlnOrNull()?.lowercase() == "yes"


        val book = if (isEbook) {
            println("Enter format:")
            val format = readlnOrNull()?.takeIf { it.isNotBlank() } ?: return
            EBook(
                EBook.generateId(LibraryData.listBook),
                bookTitle,
                author,
                publicationYear,
                genre,
                publisher,
                false,
                format
            )
        } else {
            println("Enter page number:")
            val pageNumber = readlnOrNull()?.toIntOrNull() ?: return
            Book(
                Book.generateId(LibraryData.listBook),
                bookTitle,
                author,
                publicationYear,
                genre,
                publisher,
                false,
                pageNumber
            )
        }

        LibraryData.listBook.add(book)
        println("Add book successful!")
    }

    override fun updateBook() {
        println("Enter book id to update")
        val id = readlnOrNull()?.toIntOrNull() ?: return
        val book = findBookById(id)
        book?.apply {
            println("Enter book title: ")
            bookTitle = readlnOrNull()?.takeIf { it.isNotBlank() } ?: bookTitle
            println("Enter author: ")
            author = readlnOrNull()?.takeIf { it.isNotBlank() } ?: author
            println("Enter publication year:")
            publicationYear = readlnOrNull()?.toIntOrNull() ?: publicationYear
            println("Enter genre :")
            genre = readlnOrNull()?.takeIf { it.isNotBlank() } ?: genre
            println("Enter publisher ")
            publisher = readlnOrNull()?.takeIf { it.isNotBlank() } ?: publisher

            when (this) {
                is EBook -> {
                    println("Enter format ebook")
                    format = readlnOrNull()?.takeIf { it.isNotBlank() } ?: format
                }

                is Book -> {
                    println("Enter page number")
                    pageNumber = readlnOrNull()?.toIntOrNull() ?: pageNumber
                }
            }
            println("Update book successfully")
        }

    }
//    override fun deleteBook() {
//        println("Enter the book id to delete :")
//        val id = readlnOrNull()?.toIntOrNull() ?: return
//        val isBookBorrowed = LibraryData.listUser.any { user ->
//            user.borrowedBooks.any { it.bookStatus == true }
//        }
//        if (isBookBorrowed) {
//            println("Books that are being borrowed cannot be deleted.")
//        } else {
//            if (LibraryData.listBook.removeIf { it.id == id }) {
//                println("Delete book successful")
//            } else {
//                println("Delete book fail")
//            }
//        }
//    }

    override fun deleteBook(bookId: Int): Boolean {
        return findBookById(bookId)?.let {
            LibraryData.listBook.remove(it)
            println("Delete book successful")
            true
        } ?: run {
            println("Delete book fail")
            false
        }
    }

    override fun displayBook() {
        if (LibraryData.listBook.isEmpty()) {
            println("List book empty")
        } else {
            LibraryData.listBook.forEach {
                println(it)
            }
        }
    }


    override fun searchBookByTitle() {
        println("Enter title book : ")
        val nameBook = readlnOrNull()?.uppercase() ?: return
        val search = LibraryData.listBook.filter { it.bookTitle.uppercase().contains(nameBook) }

        if (search.isEmpty()) {
            println("Book not found ")
        } else {
            println("Result search :")
            search.forEach {
                println(it)
            }
        }
    }

    fun findBookById(bookID: Int): BookBase? {
        return LibraryData.listBook.find { it.id == bookID } ?: run {
            println("Book not found")
            null
        }
    }

    fun displayAllBook() {
        if (LibraryData.listEBooks.isEmpty()) {
            println("No eBooks available!")
        } else {
            LibraryData.listEBooks.forEach { println(it.displayInfo()) }
        }
    }

    fun displayAllEbook() {
        if (LibraryData.listBooks.isEmpty()) {
            println("No eBooks available!")
        } else {
            LibraryData.listBooks.forEach { println(it.displayInfo()) }
        }
    }

    override fun countsBook() = println(
        "Total book : ${LibraryData.listBook.size} \n" +
                "Book : ${LibraryData.listBook.count { it is Book }} \n" +
                "EBook : ${LibraryData.listBook.count { it is EBook }} \n" +
                "Book borrowed : ${LibraryData.listBook.count { it.bookStatus }}"
    )


}