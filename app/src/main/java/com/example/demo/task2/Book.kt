package com.example.demo.task2

data class Book(
    var id: Int,
    var tenSach: String,
    var tacGia: String,
    var namXuatBan: Int,
    var theLoai: String
) {
    companion object {
        fun generateId(books: List<Book>): Int {
            return (books.maxOfOrNull { it.id } ?: 0) + 1
        }
    }
}