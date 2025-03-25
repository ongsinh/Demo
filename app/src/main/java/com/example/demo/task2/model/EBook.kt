package com.example.demo.task2.model

data class EBook(
    override val id: Int,
    override var bookTitle: String,
    override var author: String,
    override var publicationYear: Int,
    override var genre: Genre,
    override var publisher: String,
    override var bookStatus: Boolean,
    var format: String,
) : BookBase(id, bookTitle, author, publicationYear, genre, publisher, bookStatus) {

    companion object {
        fun generateId(books: List<BookBase>): Int {
            return (books.maxOfOrNull { it.id } ?: 0) + 1
        }
    }

    override fun displayInfo(): String {
        return "EBook(id=$id, bookTitle='$bookTitle', author='$author', publicationYear=$publicationYear, genre='$genre', publisher='$publisher', bookStatus=$bookStatus, format='$format')"
    }

}