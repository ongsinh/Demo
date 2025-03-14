package com.example.demo.task2

data class User(
    val id: Int,
    val name: String,
    val sachDaMuon: MutableList<Book> = mutableListOf()
) {
    companion object {
        fun generateId(users: List<User>): Int {
            return (users.maxOfOrNull { it.id } ?: 0) + 1
        }
    }


}