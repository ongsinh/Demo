package com.example.demo.task2.model

data class User(
    val id: Int,
    val name: String,
    val borrowedBooks: MutableList<Book> = mutableListOf()
) {
    companion object {
        fun generateId(users: List<User>): Int {
            return (users.maxOfOrNull { it.id } ?: 0) + 1
        }
    }

}