package com.example.demo.task2

import android.telecom.Call.Details

fun main(){

    val libararyManager = LibararyManager()

    while (true){
        println("Chọn chức năng : ")
        println("1. Thêm thông tin sách ")
        println("2. Hiện thông tin sách ")
        println("3. Xóa thông tin sách ")
        println("4. Hiện thông tin NXB")
        println("5. Thêm thông tin người mượn")
        println("6. Hiện thông tin người mượn")
        println("7. Tìm kiếm sách theo tên")
        println("8. Mượn sách")
        println("9. Danh sách sách người dùng đã mượn")
        println("10. Thoát")
        when(readlnOrNull()?.toIntOrNull()){
            1 -> libararyManager.addBook()
            2 -> libararyManager.displayBook()
            3 -> libararyManager.deleteBook()
            4 -> libararyManager.displayNhaXuatBan()
            5 -> libararyManager.addUser()
            6  -> libararyManager.displayUser()
            7 -> libararyManager.findBookByTitle()
            8 -> libararyManager.borrowBook()
            9 -> libararyManager.displayBookBorrowed()
            10 -> return
            else -> println("Vui long nhap lai")
        }
    }

}