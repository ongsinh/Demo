package com.example.demo.task2.model

data class User(
    val id: Int,
    var name: String,
    var phoneNumber : String,
    var borrowedBooks: MutableList<BookBase> = mutableListOf()
) {
    companion object {
        fun generateId(users: List<User>): Int {
            return (users.maxOfOrNull { it.id } ?: 0) + 1
        }
    }

    override fun toString(): String {
        return "User(id=$id, name='$name', borrowedBooks=$borrowedBooks)"
    }


}