package com.example.demo.task2

import com.example.demo.task2.model.Book
import com.example.demo.task2.model.User

class LibraryManager : IAction {

    override fun addBook() {
        println("Nhập tên sách: ")
        val tenSach = readlnOrNull() ?: return
        println("Nhập tác giả: ")
        val tacGia = readlnOrNull() ?: return
        println("Nhập năm xuất bản: ")
        val namXuatBan = readlnOrNull()?.toIntOrNull() ?: return

        var theLoai: String
        do {
            println("Nhập thể loại (0: Sách giấy - 1: Sách điện tử): ")
            theLoai = when (readlnOrNull()?.toIntOrNull()) {
                0 -> "Sách giấy"
                1 -> "Sách điện tử"
                else -> {
                    println("Lựa chọn không hợp lệ, vui lòng nhập lại!")
                    ""
                }
            }
        } while (theLoai.isEmpty())
        println("Nhập id nhà xuất bản")
        val idNXB = readlnOrNull()?.toIntOrNull() ?: return
        val book = Book(
            Book.generateId(LibraryData.listBook),
            tenSach,
            tacGia,
            namXuatBan,
            theLoai,
            LibraryData.listPublisher[idNXB].name
        )
        LibraryData.listBook.add(book)
        println("Thêm sách thành công!")
    }

    override fun deleteBook() {
        println("Nhập id sách cần xóa :")
        val id = readlnOrNull()?.toIntOrNull() ?: return
        val book = LibraryData.listBook.find { it.id == id }
        if (book != null) {
            LibraryData.listBook.remove(book)
            println("Xóa sách thành công ")
        } else {
            println("Không tìm thấy sách cần xóa")
        }
    }

    override fun displayPublisher() {
        if (LibraryData.listPublisher.isEmpty()) {
            println("Danh sách trống")
        } else {
            LibraryData.listPublisher.forEach {
                println(it)
            }
        }
    }

    override fun addUser() {
        println("Nhập tên người mượn sách :")
        val name = readlnOrNull() ?: return
        val user = User(User.generateId(LibraryData.listUser), name)
        LibraryData.listUser.add(user)
        println("Thêm người mượn sách thành công")
    }

    override fun deleteUser() {
        println("Nhap id người dùng cần xóa :")
        val id = readlnOrNull()?.toIntOrNull() ?: return
        val user = LibraryData.listUser.find { it.id == id }
        if (user != null) {
            LibraryData.listUser.remove(user)
            println("Xóa thông tin người dùng thành công")
        } else {
            println("Không tìm thấy người dùng cần xóa")
        }
    }

    override fun findBookByTitle() {
        println("Nhập tên sách cần tiìm : ")
        val tenSach = readlnOrNull()?.uppercase() ?: return
        val timkiem = LibraryData.listBook.filter { it.bookTitle.uppercase().contains(tenSach) }

        if (timkiem.isEmpty()) {
            println("Không tìm thấy sách ")
        } else {
            println("Kết quả tìm kiếm :")
            timkiem.forEach {
                println(it)
            }
        }
    }


    override fun displayBook() {
        if (LibraryData.listBook.isEmpty()) {
            println("Danh sách trống")
        } else {
            LibraryData.listBook.forEach {
                println(it)
            }
        }
    }


    override fun displayUser() {
        if (LibraryData.listUser.isEmpty()) {
            println("Danh sách trống")
        } else {
            LibraryData.listUser.forEach {
                println("Id : ${it.id} , Name : ${it.name}")
            }
        }
    }

    override fun borrowBook() {
        println("Nhập thông tin khách mượn sách")
        print("Nhập mã người dùng: ")
        val maKH = readlnOrNull()?.toIntOrNull() ?: return
        var user = LibraryData.listUser.find { it.id == maKH }
        if (user == null) {
            println("Mã người dùng không tồn tại")
            print("Nhập tên người dùng: ")
            val tenKH = readlnOrNull() ?: return

            user = User(User.generateId(LibraryData.listUser), tenKH)
            LibraryData.listUser.add(user)
        }

        val availableBook = getAvailableBooks(user)

        if (availableBook.isEmpty()) {
            println(" Không có sách nào có sẵn để mượn!")
            return
        }

        println("Danh sách sách chưa mượn:")
        availableBook.forEach { println(it) }

        print("Nhập id sách cần mượn: ")
        val idBook = readlnOrNull()?.toIntOrNull() ?: return
        val borrowBook = availableBook.find { it.id == idBook }

        if (borrowBook != null) {
            user.borrowedBooks.add(borrowBook)
            println("${user.name} đã mượn sách: ${borrowBook.bookTitle}")
        } else {
            println(" Không tìm thấy sách phù hợp!")
        }
    }

    override fun displayBookBorrowed() {
        LibraryData.listUser.forEach { user ->
            println("Danh sách sách đã mượn của người dùng ${user.name}")
            if (user.borrowedBooks.isEmpty()) {
                println("Chưa mượn sách nào.")
            } else {
                user.borrowedBooks.forEach { println(it) }
            }
        }
    }


    private fun getAvailableBooks(user: User?): List<Book> {
        if (user != null) {
            return LibraryData.listBook.filter { sach ->
                LibraryData.listUser.none { user ->
                    user.borrowedBooks.contains(sach)
                }
            }
        }
        return LibraryData.listBook
    }

//    fun List<Book>.findBookByCategory(category: String): List<Book>{
//        return this.filter { it.theLoai.contains(category,ignoreCase = true) }
//    }
}