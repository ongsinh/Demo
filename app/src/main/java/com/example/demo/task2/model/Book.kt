package com.example.demo.task2.model

data class Book(
    override val id: Int,
    override var bookTitle: String,
    override var author: String,
    override var publicationYear: Int,
    override var genre: String,
    override var publisher: String,
    override var bookStatus: Boolean,
    var pageNumber: Int
) : BookBase(id, bookTitle, author, publicationYear, genre, publisher, bookStatus) {

    companion object {
        fun generateId(books: List<BookBase>): Int {
            return (books.maxOfOrNull { it.id } ?: 0) + 1
        }
    }

    override fun displayInfo(): String {
        return "Book(id=$id, bookTitle='$bookTitle', author='$author', publicationYear=$publicationYear, genre='$genre', publisher='$publisher', bookStatus=$bookStatus, pageNumber='$pageNumber')"
    }


}