package com.example.demo.task2.system

import com.example.demo.task2.data.LibraryData
import com.example.demo.task2.`interface`.Borrowable
import com.example.demo.task2.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class BorrowedManager : Borrowable {

    override suspend fun borrowBook() {
        withContext(Dispatchers.IO) {
            println("Enter information user")
            print("Enter user id : ")
            val idUser = readlnOrNull()?.toIntOrNull() ?: return@withContext
            var user = LibraryData.listUser.find { it.id == idUser }
            if (user == null) {
                println("Enter the name :")
                val name = readlnOrNull() ?: return@withContext
                var phoneNumber: String?
                do {
                    println("Enter phone number :")
                    phoneNumber = readlnOrNull()
                    when {
                        phoneNumber.isNullOrBlank() -> println("Phone number can't be empty")
                        !isValidPhoneNumber(phoneNumber) -> println("10 digit phone number with no special characters")
                        else -> break
                    }
                } while (true)
                user = User(User.generateId(LibraryData.listUser), name, phoneNumber.toString())
                LibraryData.listUser.add(user)
                println("Add people successfully borrowed books")
            }

            val availableBook = LibraryData.listAllBooks.filter { !it.bookStatus }

            if (availableBook.isEmpty()) {
                println(" There are no books available to borrow.!")
                return@withContext
            }

            println("List of books not borrowed")
            availableBook.forEach { println(it) }

            print("Enter id book to borrowed ")
            val idBook = readlnOrNull()?.toIntOrNull() ?: return@withContext
            val borrowBook = availableBook.find { it.id == idBook }

            if (borrowBook != null) {
                user.borrowedBooks.add(borrowBook)
                println("${user.name} borrowed: ${borrowBook.bookTitle}")
            } else {
                println(" No matching books found!")
            }
        }
    }

    override fun returnBook() {
        println("Enter user ID : ")
        val idUser = readlnOrNull()?.toIntOrNull() ?: return
        val user =
            LibraryData.listUser.find { it.id == idUser } ?: return println("Invalid user id")

        println("Enter book ID to return :")
        val idBook = readlnOrNull()?.toIntOrNull() ?: return
        if (user.borrowedBooks.removeIf { it.id == idBook }) {
            println("Returned books successfully")
        } else {
            println("Returned books fail")
        }

    }

    override fun getBookBorrowedUser() {
        println("Enter user ID : ")
        val idUser = readlnOrNull()?.toIntOrNull() ?: return
        LibraryData.listUser.find { it.id == idUser }?.let { user ->
            if (user.borrowedBooks.isEmpty()) {
                println("List book borrowed empty")
            } else {
                user.borrowedBooks.forEach { println(it) }
            }
        }
    }

//    inline fun List<BookBase>.getBorrowedBooks() : List<BookBase> {
//        return  this.filter { it.bookStatus }
//    }

    override suspend fun displayBookBorrowed() {

        val borrowedBooks = CoroutineScope(Dispatchers.IO).async {
            LibraryData.listUser.filter { it.borrowedBooks.isNotEmpty() }
        }.await()
        borrowedBooks.forEach {
            println(it)
        }
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.matches(Regex("^\\d{10}$"))
    }


}

