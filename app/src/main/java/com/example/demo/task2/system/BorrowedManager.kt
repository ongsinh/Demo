package com.example.demo.task2.system

import Borrowable
import com.example.demo.task2.model.User

class BorrowedManager : Borrowable {

    override fun borrowBook() {
        println("Enter information user")
        print("Enter user id : ")
        val idUser = readlnOrNull()?.toIntOrNull() ?: return
        var user = LibraryData.listUser.find { it.id == idUser }
        if (user == null) {
            println("Enter the name :")
            val name = readlnOrNull() ?: return
            var phoneNumber: String?
            do {
                println("Enter phone number :")
                phoneNumber = readlnOrNull()
                if (phoneNumber.isNullOrBlank()) {
                    println("Phone number can't be empty")
                } else if (!isValidphoneNumber(phoneNumber)) {
                    println("10 digit phone number with no special characters")
                } else break
            } while (true)
            user = User(User.generateId(LibraryData.listUser), name, phoneNumber.toString())
            LibraryData.listUser.add(user)
            println("Add people successfully borrowed books")
        }

        val availableBook = LibraryData.listBook.filter { !it.bookStatus }

        if (availableBook.isEmpty()) {
            println(" Không có sách nào có sẵn để mượn!")
            return
        }

        println("List of books not borrowed")
        availableBook.forEach { println(it) }

        print("Enter id book to borrowed ")
        val idBook = readlnOrNull()?.toIntOrNull() ?: return
        val borrowBook = availableBook.find { it.id == idBook }

        if (borrowBook != null) {
            user.borrowedBooks.add(borrowBook)
            println("${user.name} borrowed: ${borrowBook.bookTitle}")
        } else {
            println(" No matching books found!")
        }
    }

    override fun returnBook() {
        println("Enter user ID : ")
        val idUser = readlnOrNull()?.toIntOrNull() ?: return
        val user = LibraryData.listUser.find { it.id == idUser } ?: return println("Invalid user id")

        println("Enter book ID to return :")
        val idBook = readlnOrNull()?.toIntOrNull() ?: return
        if (user.borrowedBooks.removeIf { it.id == idBook }) {
            println("Returned books successfully")
        } else {
            println("Returned books fail")
        }

    }

    override fun displayBookBorrowed() {
        LibraryData.listUser.forEach { user ->
            println("List of books borrowed by users ${user.name}")
            if (user.borrowedBooks.isEmpty()) {
                println("Haven't borrowed any books yet.")
            } else {
                user.borrowedBooks.forEach { println(it) }
            }
        }
    }

    private fun isValidphoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.matches(Regex("^\\d{10}$"))
    }


}

