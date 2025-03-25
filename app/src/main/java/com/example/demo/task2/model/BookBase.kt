package com.example.demo.task2.model

enum class Genre {
    FICTION, NON_FICTION, SCIENCE, HISTORY, FANTASY, TECHNOLOGY;

    companion object {
        fun getGenre(index: Int): Genre? {
            return entries.getOrNull(index)
        }
        fun displayGenre(){
            entries.forEachIndexed{ index, genre -> println("${index + 1}. $genre") }
        }
    }
}

abstract class BookBase(
    open val id: Int,
    open var bookTitle: String,
    open var author: String,
    open var publicationYear: Int,
    open var genre: Genre,
    open var publisher: String,
    open var bookStatus: Boolean
) {
    abstract fun displayInfo(): String

}