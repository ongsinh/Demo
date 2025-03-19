package com.example.demo.task2.model

data class Book(
    var id: Int,
    var bookTitle: String,
    var author: String,
    var publicationYear: Int,
    var genre: String,
    var publisher: String
) {
    companion object {
        fun generateId(books: List<Book>): Int {
            return (books.maxOfOrNull { it.id } ?: 0) + 1
        }
    }

    override fun toString(): String {
        return "Book(id=$id, bookTitle='$bookTitle', author='$author', publicationYear=$publicationYear, genre='$genre', publisher='$publisher')"
    }

}