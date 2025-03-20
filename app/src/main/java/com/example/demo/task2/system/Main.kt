package com.example.demo.task2.system

import task2.system.BookManager
import task2.system.UserManager


fun main() {
    val userManager = UserManager()
    val borrowedManager = BorrowedManager()
    while (true) {
        println("Select program")
        println("1. User management ")
        println("2. Book management ")
        println("3. Borrowed book management")
        println("4. Exit ")
        when (readlnOrNull()?.toIntOrNull()) {
            1 -> {
                manageUser()
            }

            2 -> {
                manageBook()
            }

            3 -> {
                manageBorrowedBook()
            }

            4 -> return
            else -> println("Vui long nhap lai")
        }
    }

}

fun manageBorrowedBook() {
    val borrowedManager = BorrowedManager()
    while (true) {
        println("Select program")
        println("1. Borrow book ")
        println("2. Return book")
        println("3. Display book borrowed")
        println("4. Exit")
        when (readlnOrNull()?.toIntOrNull()) {
            1 -> borrowedManager.borrowBook()
            2 -> borrowedManager.returnBook()
            3 -> borrowedManager.displayBookBorrowed()
            4 -> return
            else -> println("Please re-enter")
        }
    }
}

fun manageBook() {
    val bookManager = BookManager()
    while (true) {
        println("Select program")
        println("1. Add new book ")
        println("2. Delete book by ID")
        println("3. Display books")
        println("4. Display type ebook")
        println("5. Display type book")
        println("6. Update information book")
        println("7. Search book by title")
        println("8. Book count statistics")
        println("9. Exit")
        when (readlnOrNull()?.toIntOrNull()) {
            1 -> bookManager.addBook()
            2 -> bookManager.deleteBook()
            3 -> bookManager.displayBook()
            4 -> bookManager.displayAllEbook()
            5 -> bookManager.displayAllBook()
            6 -> bookManager.updateBook()
            7 -> bookManager.searchBookByTitle()
            8 -> bookManager.countsBook()
            9 -> return
            else -> println("Please re-enter")
        }
    }
}

fun manageUser() {
    val userManager = UserManager()
    while (true) {
        println("Select program")
        println("1. Add new user ")
        println("2. Delete user")
        println("3. Update user")
        println("4. Search user by id")
        println("5. Display user")
        println("6. Search user by name")
        println("7. Exit")
        when (readlnOrNull()?.toIntOrNull()) {
            1 -> userManager.addUser()
            2 -> userManager.deleteUser()
            3 -> userManager.updateUser()
            4 -> userManager.searchUserById()
            5 -> userManager.displayUser()
            6 -> userManager.findUserByName()
            7 -> return
            else -> println("Please re-enter")
        }
    }
}
