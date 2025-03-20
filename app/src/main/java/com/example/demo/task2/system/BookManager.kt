package task2.system

import BookRepository
import EBook
import com.example.demo.task2.model.Book

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

        val id = generateBookId()

        val book = if (isEbook) {
            println("Enter format:")
            val format = readlnOrNull()?.takeIf { it.isNotBlank() } ?: return
            EBook(id, bookTitle, author, publicationYear, genre, publisher, false, format)
        } else {
            println("Enter page number:")
            val pageNumber = readlnOrNull()?.toIntOrNull() ?: return
            Book(id, bookTitle, author, publicationYear, genre, publisher, false, pageNumber)
        }

        LibraryData.listBook.add(book)
        println("Add book successful!")
    }

    override fun deleteBook() {
        println("Enter the book id to delete :")
        val id = readlnOrNull()?.toIntOrNull() ?: return
        val isBookBorrowed = LibraryData.listUser.any { user ->
            user.borrowedBooks.any { it.bookStatus == true }
        }
        if (isBookBorrowed) {
            println("Books that are being borrowed cannot be deleted.")
        } else {
            if (LibraryData.listBook.removeIf { it.id == id }) {
                println("Delete book successful")
            } else {
                println("Delete book fail")
            }
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

    override fun updateBook() {
        println("Enter book id to update")
        val id = readlnOrNull()?.toIntOrNull() ?: return
        val book = LibraryData.listBook.find { it.id == id }
        if (book == null) {
            println("Book not found")
        } else {
            println("Enter book title: ")
            val newTitle = readlnOrNull()?.takeIf { it.isNotBlank() } ?: book.bookTitle
            println("Enter author: ")
            val newAuthor = readlnOrNull().takeIf { it.isNullOrBlank() } ?: book.author
            println("Enter publication year:")
            val newPublicationYear = readlnOrNull()?.toIntOrNull() ?: book.publicationYear
            println("Enter genre :")
            val newGenre = readlnOrNull().takeIf { it.isNullOrBlank() } ?: book.genre
            println("Enter publisher ")
            val newPublisher = readlnOrNull().takeIf { it.isNullOrBlank() } ?: book.publisher

            book.bookTitle = newTitle
            book.author = newAuthor
            book.publicationYear = newPublicationYear
            book.genre = newGenre
            book.publisher = newPublisher
            if (book is EBook) {
                println("Enter format ebook")
                val newFormat = readlnOrNull().takeIf { it.isNullOrBlank() } ?: book.format
                book.format = newFormat
            } else if (book is Book) {
                println("Enter page number")
                val newPageNumber = readlnOrNull()?.toIntOrNull() ?: book.pageNumber
                book.pageNumber = newPageNumber
            }
        }

        println("Update book successfully")

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

    fun displayAllBook(){
        if (LibraryData.listEBooks.isEmpty()) {
            println("📚 No eBooks available!")
        } else {
            LibraryData.listEBooks.forEach { println(it.displayInfo()) }
        }
    }

    fun displayAllEbook(){
        if (LibraryData.listBooks.isEmpty()) {
            println("📚 No eBooks available!")
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



    private fun generateBookId(): Int {
        return (LibraryData.listBook.maxOfOrNull { it.id } ?: 0) + 1
    }

}