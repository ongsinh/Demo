package com.example.demo.task2

class LibararyManager {
    val listBook = mutableListOf(
        Book("Dế Mèn Phiêu Lưu Ký", "Tô Hoài", 1941, "Văn học thiếu nhi"),
        Book("Sherlock Holmes", "Arthur Conan Doyle", 1892, "Trinh thám"),
        Book("Nhà Giả Kim", "Paulo Coelho", 1988, "Triết học"),
        Book("Tắt Đèn", "Ngô Tất Tố", 1939, "Hiện thực phê phán"),
        Book("Harry Potter và Hòn Đá Phù Thủy", "J.K. Rowling", 1997, "Fantasy"),
        Book("Chí Phèo", "Nam Cao", 1941, "Hiện thực phê phán"),
        Book("Bố Già", "Mario Puzo", 1969, "Tiểu thuyết"),
        Book("Đắc Nhân Tâm", "Dale Carnegie", 1936, "Kỹ năng sống"),
        Book("Tội Ác và Hình Phạt", "Fyodor Dostoevsky", 1866, "Văn học Nga"),
        Book("Thép Đã Tôi Thế Đấy", "Nikolai Ostrovsky", 1932, "Cách mạng")
    )

    val listUser = mutableListOf(
        User(1, "Nguyễn Văn A"), User(2, "Trần Thị B"), User(3, "Lê Văn C")
    )


    fun addBook() {
        println("Nhap ten sach : ")
        val tenSach = readlnOrNull() ?: return
        println("Nhap tac gia :")
        val tacGia = readlnOrNull() ?: return
        println("Nhap nam xuat ban :")
        val namXuatBan = readlnOrNull()?.toIntOrNull() ?: return
        println("Nhap the loai (0 : Sách giấy - 1 : Sách điện tử) : ")
        val theloai = when (readlnOrNull()?.toIntOrNull()) {
            0 -> "Sach giay"
            1 -> "Sach dien tu"
            else -> {
                println("Lua chon khong hop le ")
                return
            }
        }
        val book = Book(tenSach, tacGia, namXuatBan, theloai)
        listBook.add(book)
        println("Them sach thanh cong")
    }

    fun showListBook() {
        if (listBook.isEmpty()) {
            println("Danh sach trong")
        } else {
            listBook.forEach {
                println(it)
            }
        }
    }

    fun timkiemSach() {
        println("Nhap ten sach can tim : ")
        val tenSach = readlnOrNull()?.uppercase() ?: return
        val timkiem = listBook.filter { it.tenSach.uppercase().contains(tenSach) }

        if (timkiem.isEmpty()) {
            println("Khong tim thay sach")
        } else {
            println("Ket qua tim kiem :")
            timkiem.forEach {
                println(it)
            }
        }
    }

    fun muonSach() {
        println("Nhập thông tin khách mượn sách")
        print("Nhập mã người dùng: ")
        val maKH = readlnOrNull()?.toIntOrNull() ?: return
        print("Nhập tên người dùng: ")
        val tenKH = readlnOrNull() ?: return

        val user = User(maKH, tenKH)
        listUser.add(user)

        val sachChuaMuon = getSachChuaMuon()

        if (sachChuaMuon.isEmpty()) {
            println(" Không có sách nào có sẵn để mượn!")
            return
        }

        println("Danh sách sách chưa mượn:")
        sachChuaMuon.forEach { println(it) }

        print("Nhập tên sách cần mượn: ")
        val tenSachMuon = readlnOrNull()?.trim()?.uppercase() ?: return
        val sachMuon = sachChuaMuon.find { it.tenSach.uppercase().contains(tenSachMuon) }

        if (sachMuon != null) {
            user.sachDaMuon.add(sachMuon)
            println("${user.name} đã mượn sách: ${sachMuon.tenSach}")
        } else {
            println(" Không tìm thấy sách phù hợp!")
        }
    }


    fun listMuonSach() {
        println("Danh sách sách mà người dùng đã mượn:")
        listUser.forEach { user ->
            println("Danh sach sach đã mượn cua nguoi dung ${user.name}")
            if (user.sachDaMuon.isEmpty()) {
                println("Chưa mượn sách nào.")
            } else {
                user.sachDaMuon.forEach { println(it) }
            }
        }
    }

    fun getSachChuaMuon(): List<Book> {
        return listBook.filter { sach ->
            listUser.none() { user -> user.sachDaMuon.contains(sach) }
        }
    }
}