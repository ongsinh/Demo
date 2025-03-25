package com.example.demo.task2.data

import com.example.demo.task2.model.Book
import com.example.demo.task2.model.BookBase
import com.example.demo.task2.model.EBook
import com.example.demo.task2.model.Genre
import com.example.demo.task2.model.User

object LibraryData {

    val listBooks = mutableListOf(
        Book(1, "Clean Code", "Robert C. Martin", 2008, Genre.FANTASY, "Prentice Hall", true, 464),
        Book(2, "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 1997, Genre.FANTASY, "Bloomsbury", true, 309),
        Book(4, "The Great Gatsby", "F. Scott Fitzgerald", 1925, Genre.FANTASY, "Charles Scribner's Sons", false, 180),
        Book(6, "The Hobbit", "J.R.R. Tolkien", 1937, Genre.FANTASY, "George Allen & Unwin", false, 310),
        Book(8, "The Psychology of Money", "Morgan Housel", 2020, Genre.FANTASY, "Harriman House", false, 256)
    )


    val listEBooks = mutableListOf(
        EBook(3, "A Brief History of Time", "Stephen Hawking", 1988, Genre.FANTASY, "Bantam Books", true, "PDF"),
        EBook(5, "Atomic Habits", "James Clear", 2018, Genre.FANTASY, "Bantam Books", false, "EPUB"),
        EBook(7, "Sapiens: A Brief History of Humankind", "Yuval Noah Harari", 2011, Genre.FANTASY, "Harvill Secker", false, "MOBI"),
        EBook(9, "Deep Learning", "Ian Goodfellow, Yoshua Bengio, Aaron Courville", 2016, Genre.FANTASY, "MIT Press", false, "PDF"),
        EBook(10, "Kotlin in Action", "Dmitry Jemerov & Svetlana Isakova", 2017, Genre.FANTASY, "Manning", false, "EPUB")
    )


    val listAllBooks: MutableList<BookBase> = mutableListOf<BookBase>().apply {
        addAll(listBooks)
        addAll(listEBooks)
    }


    val listUser = mutableListOf(
        User(1, "Nguyễn Văn A", "0123456789", mutableListOf(listAllBooks[0], listAllBooks[1])),
        User(2, "Trần Thị B", "0123456789"),
        User(3, "Lê Văn C", "0123456789"),
        User(4, "Phạm Thị D", "0123456789", mutableListOf(listAllBooks[2])),
        User(5, "Hoàng Văn E", "0123456789"),
        User(6, "Vũ Thị F", "0123456789"),
        User(7, "Đặng Văn G", "0123456789"),
        User(8, "Bùi Thị H", "0123456789")
    )

}