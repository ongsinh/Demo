package com.example.demo.task2.system


import com.example.demo.task2.data.LibraryData
import com.example.demo.task2.`interface`.BookRepository
import com.example.demo.task2.model.Book
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
                EBook.generateId(LibraryData.listAllBooks),
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
                Book.generateId(LibraryData.listAllBooks),
                bookTitle,
                author,
                publicationYear,
                genre,
                publisher,
                false,
                pageNumber
            )
        }

        LibraryData.listAllBooks.add(book)
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
//        val id = readonlyOrNull()?.toIntOrNull() ?: return
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
            LibraryData.listAllBooks.remove(it)
            println("Delete book successful")
            true
        } ?: run {
            println("Delete book fail")
            false
        }
    }

    override fun displayBook() {
        if (LibraryData.listAllBooks.isEmpty()) {
            println("List book empty")
        } else {
            LibraryData.listAllBooks.forEach {
                println(it)
            }
        }
    }


    override fun searchBookByTitle() {
        println("Enter title book : ")
        val nameBook = readlnOrNull()?.uppercase() ?: return
        val search = LibraryData.listAllBooks.filter { it.bookTitle.uppercase().contains(nameBook) }

        if (search.isEmpty()) {
            println("Book not found ")
        } else {
            println("Result search :")
            search.forEach {
                println(it)
            }
        }
    }

    private fun findBookById(bookID: Int): BookBase? {
        return LibraryData.listAllBooks.find { it.id == bookID } ?: run {
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
        "Total book : ${LibraryData.listAllBooks.size} \n" +
                "Book : ${LibraryData.listAllBooks.count { it is Book }} \n" +
                "EBook : ${LibraryData.listAllBooks.count { it is EBook }} \n" +
                "Book borrowed : ${LibraryData.listAllBooks.count { it.bookStatus }}"
    )

    override fun filterBookByYear(): List<BookBase> {
        return filterBook { it.publicationYear > 2000 }
    }

    override fun filterBookByPageNumber(): List<BookBase> {
        return filterBook { ((it as? Book)?.pageNumber ?: 0) > 200 }
    }

    override fun filterBookByFormat(format: String): List<BookBase> {
        return filterBook { (it as? EBook)?.format?.contains(format, ignoreCase = true) == true }
    }

    override fun filterBookByFormatAndYear(format: String, year: Int): List<BookBase> {
        return filterBook {
            (it as? EBook)?.format?.contains(
                format,
                ignoreCase = true
            ) == true && it.publicationYear > year
        }
    }

    override fun sortByBookByYear(): List<BookBase> {
        return compareBook { a, b -> a.publicationYear.compareTo(b.publicationYear) }
    }

//    fun sortByBookTitle(): List<BookBase> {
//        return compareBook { a, b -> a.bookTitle.compareTo(b.bookTitle) }
//    }


    private fun filterBook(condition: (BookBase) -> Boolean): List<BookBase> {
        return LibraryData.listAllBooks.filter(condition).also {
            println(it)
        }
    }


    private fun compareBook(compareFunction: (BookBase, BookBase) -> Int): List<BookBase> {
        return LibraryData.listAllBooks.sortedWith(compareFunction).also {
            println(it)
        }
    }

}