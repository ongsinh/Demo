package com.example.demo.task2


fun main(){

    val libraryManager = LibraryManager()
    while (true){
        println("Chọn chức năng : ")
        println("1. Thêm thông tin sách ")
        println("2. Hiện thông tin sách ")
        println("3. Xóa thông tin sách ")
        println("4. Hiện thông tin NXB")
        println("5. Thêm thông tin người mượn")
        println("6. Hiện thông tin người mượn")
        println("7. Xóa thông tin người mượn")
        println("8. Tìm kiếm sách theo tên")
        println("9. Mượn sách")
        println("10. Danh sách sách người dùng đã mượn")
        println("11. Thoát")
        when(readlnOrNull()?.toIntOrNull()){
            1 -> libraryManager.addBook()
            2 -> libraryManager.displayBook()
            3 -> libraryManager.deleteBook()
            4 -> libraryManager.displayPublisher()
            5 -> libraryManager.addUser()
            6  -> libraryManager.displayUser()
            7 -> libraryManager.deleteUser()
            8 -> libraryManager.findBookByTitle()
            9 -> libraryManager.borrowBook()
            10 -> libraryManager.displayBookBorrowed()
            11 -> return
            else -> println("Vui long nhap lai")
        }
    }

}