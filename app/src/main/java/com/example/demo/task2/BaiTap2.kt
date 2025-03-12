package com.example.demo.task2

fun main(){

    val libararyManager = LibararyManager()

    while (true){
        println("Chọn chức năng : ")
        println("1. Thêm thông tin sách ")
        println("2. Hiện thông tin sách ")
        println("3. Thêm thông tin người mượn")
        println("4. Hiện thông tin người mượn")
        println("5. Tìm kiếm sách theo tên")
        println("6. Mượn sách")
        println("7. Danh sách sách người dùng đã mượn")
        println("8. Thoát")
        when(readlnOrNull()?.toIntOrNull()){
            1 -> libararyManager.addBook()
            2 -> libararyManager.displayBook()
            3 -> libararyManager.addUser()
            4  -> libararyManager.displayUser()
            5 -> libararyManager.findBookByTitle()
            6 -> libararyManager.borrowBook()
            7 -> libararyManager.displayBookBorrowed()
            8 -> return
            else -> println("Vui long nhap lai")
        }
    }

}