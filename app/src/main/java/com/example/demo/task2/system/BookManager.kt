package com.example.demo.task2.system

import com.example.demo.task2.data.LibraryData
import com.example.demo.task2.data.LibraryHelper
import com.example.demo.task2.`interface`.BookRepository
import com.example.demo.task2.model.Book
import com.example.demo.task2.model.BookBase
import com.example.demo.task2.model.EBook
import com.example.demo.task2.model.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookManager : BookRepository {
    private val libraryHelper = LibraryHelper

    private suspend fun inputBook(): BookBase {
        return withContext(Dispatchers.IO) {
            println(Thread.currentThread())
            val bookTitle = libraryHelper.getBookTitle()

            val author = libraryHelper.getAuthor()

            val publicationYear = libraryHelper.getPublicationYear()

            val genre = selectGenre()

            val publisher = libraryHelper.getPublisher()

            println("Is this Ebook? (yes/no)")
            val isEbook = readlnOrNull()?.lowercase() == "yes"

            return@withContext if (isEbook) {
                val format = libraryHelper.getFormat()
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
                val pageNumber = libraryHelper.getPageNumber()
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
        }
    }


    //read and write in IO
    override suspend fun addBook() = withContext(Dispatchers.IO) {
        val book = inputBook()
        LibraryData.listAllBooks.add(book)
        println("Add book successful!")
    }

    private fun selectGenre(): Genre {
        println("Select a genre :")
        Genre.displayGenre()
        while (true) {
            print("Enter number to a genre : ")
            val choice = readlnOrNull()?.toIntOrNull()
            val genre = Genre.getGenre(choice ?: -1)
            if (genre != null) return genre
            println("Please re-enter")

        }
    }

    override suspend fun updateBook() {
        withContext(Dispatchers.IO) {
            val id = libraryHelper.getBookId()
            val book = findBookById(id)
            book?.apply {
                bookTitle = LibraryHelper.getBookTitle()
                author = LibraryHelper.getAuthor()
                publicationYear = LibraryHelper.getPublicationYear()
                println("Enter genre :")
                genre = selectGenre()
                publisher = LibraryHelper.getPublisher()

                when (this) {
                    is EBook -> {
                        format = LibraryHelper.getFormat()
                    }

                    is Book -> {
                        pageNumber = LibraryHelper.getPageNumber()
                    }
                }
                println("Update book successfully")
            }
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

    override suspend fun deleteBook(bookId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            findBookById(bookId)?.let {
                LibraryData.listAllBooks.remove(it)
                println("Delete book successful")
                true
            } ?: run {
                println("Delete book fail")
                false
            }
        }
    }

    override fun displayBook() {
        if (LibraryData.listAllBooks.isEmpty()) {
            println("List book empty")
        } else {
            LibraryHelper.displayList(LibraryData.listAllBooks)
        }
    }


    override suspend fun searchBookByTitle() {
        val nameBook = LibraryHelper.getBookTitle().uppercase()
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
            LibraryHelper.displayList(LibraryData.listBooks)
        }
    }

    fun displayAllEbook() {
        if (LibraryData.listBooks.isEmpty()) {
            println("No eBooks available!")
        } else {
            LibraryHelper.displayList(LibraryData.listEBooks)
        }
    }

    override fun countsBook() = println(
        "Total book : ${LibraryData.listAllBooks.size} \n" +
                "Book : ${LibraryData.listAllBooks.count { it is Book }} \n" +
                "EBook : ${LibraryData.listAllBooks.count { it is EBook }} \n" +
                "Book borrowed : ${LibraryData.listAllBooks.count { it.bookStatus }}"
    )

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

    fun sortByBookTitle(): List<BookBase> {
        return compareBook { a, b -> a.bookTitle.compareTo(b.bookTitle) }
    }


}