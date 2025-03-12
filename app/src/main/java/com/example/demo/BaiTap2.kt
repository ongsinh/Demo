package com.example.demo

fun main(){
    val libararyManager = LibararyManager()

    while (true){
        println("Chọn chức năng : ")
        println("1. Nhập thông tin sách ")
        println("2. Hiện thông tin sách ")
        println("3. Tìm kiếm danh sách sách theo tên")
        println("4. Muon sach")
        println("5. Danh sach sach nguoi dung muon")
        println("6. Thoat")
        when(readlnOrNull()?.toIntOrNull()){
            1 -> libararyManager.addBook()
            2 -> libararyManager.showListBook()
            3 -> libararyManager.timkiemSach()
            4  -> libararyManager.muonSach()
            5 -> libararyManager.listMuonSach()
            6 -> return
            else -> println("Vui long nhap lai")
        }
    }

}