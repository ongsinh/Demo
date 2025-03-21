package com.example.demo.task2.system

import com.example.demo.task2.model.User
import com.example.demo.task2.data.LibraryData

class UserManager {
    fun addUser() {
        println("Enter the name :")
        val name = readlnOrNull() ?: return
        var phoneNumber: String?
        do {
            println("Enter phone number :")
            phoneNumber = readlnOrNull()
            if (phoneNumber.isNullOrBlank()) {
                println("Phone number can't be empty")
            } else if (!isValidPhoneNumber(phoneNumber)) {
                println("10 digit phone number with no special characters")
            } else break
        } while (true)
        val user = User(User.generateId(LibraryData.listUser), name, phoneNumber.toString())
        LibraryData.listUser.add(user)
        println("Add people successfully borrowed books")
    }

    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.matches(Regex("^\\d{10}$"))
    }

    fun deleteUser() {
        println("Enter the user id to delete:")
        val id = readlnOrNull()?.toIntOrNull() ?: return

        if (LibraryData.listUser.removeIf { it.id == id }) {
            println("Delete user success ")
        } else {
            println("Delete user failed")
        }
    }

    fun displayUser() {
        if (LibraryData.listUser.isEmpty()) {
            println("List is empty")
        } else {
            LibraryData.listUser.forEach {
                println("Id : ${it.id} , Name : ${it.name}, PhoneNumber : ${it.phoneNumber}")
            }
        }
    }

    fun searchUserById()  {
        println("Enter id user")
        val id = readlnOrNull()?.toIntOrNull() ?: return
        val search = LibraryData.listUser.filter { it.id == id }
        if (search.isEmpty()) {
            println("User not found")
        } else {
            search.forEach {
                println(it)
            }
        }
    }


    fun updateUser() {
        println("Enter user ID: ")
        val id = readlnOrNull()?.toIntOrNull() ?: return

        val user = LibraryData.listUser.find { it.id == id }

        if (user == null) {
            println("User not found!")
            return
        }

        println("Enter new name : ")
        val newName = readlnOrNull()?.takeIf { it.isNotBlank() } ?: user.name

        println("Enter new email : ")
        val newPhoneNumber = readlnOrNull()?.takeIf { it.isNotBlank() && isValidPhoneNumber(it) } ?: user.phoneNumber

        user.name = newName
        user.phoneNumber = newPhoneNumber

        println("User updated successfully!")
    }

    fun findUserByName(): List<User>? {
        println("Enter user name")
        val name = readlnOrNull()?.uppercase() ?: return null
        val result = LibraryData.listUser.filter { it.name.uppercase() == name }
        return if (result.isEmpty()) {
            emptyList()
        } else {
            result.forEach { println("ID: ${it.id}, Name: ${it.name}, Phone: ${it.phoneNumber}") }
            result
        }
    }


}