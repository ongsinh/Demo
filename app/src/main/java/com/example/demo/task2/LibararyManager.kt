package com.example.demo.task2

@Suppress("NAME_SHADOWING")
class LibararyManager : IAction {

    private val listBook = mutableListOf(
        Book(1, "Dế Mèn Phiêu Lưu Ký", "Tô Hoài", 1941, "Văn học thiếu nhi"),
        Book(2, "Sherlock Holmes", "Arthur Conan Doyle", 1892, "Trinh thám"),
        Book(3, "Nhà Giả Kim", "Paulo Coelho", 1988, "Triết học"),
        Book(4, "Tắt Đèn", "Ngô Tất Tố", 1939, "Hiện thực phê phán"),
        Book(5, "Harry Potter và Hòn Đá Phù Thủy", "J.K. Rowling", 1997, "Fantasy"),
        Book(6, "Chí Phèo", "Nam Cao", 1941, "Hiện thực phê phán"),
        Book(7, "Bố Già", "Mario Puzo", 1969, "Tiểu thuyết"),
        Book(8, "Đắc Nhân Tâm", "Dale Carnegie", 1936, "Kỹ năng sống"),
        Book(9, "Tội Ác và Hình Phạt", "Fyodor Dostoevsky", 1866, "Văn học Nga"),
        Book(10, "Thép Đã Tôi Thế Đấy", "Nikolai Ostrovsky", 1932, "Cách mạng")
    )

    private val listUser = mutableListOf(
        User(1, "Nguyễn Văn A"), User(2, "Trần Thị B"), User(3, "Lê Văn C")
    )


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

        val book = Book(Book.generateId(listBook), tenSach, tacGia, namXuatBan, theLoai)
        listBook.add(book)
        println("Thêm sách thành công!")
    }


    override fun addUser() {
        println("Nhập tên người mượn sách :")
        val name = readlnOrNull() ?: return
        val user = User(User.generateId(listUser), name)
        listUser.add(user)
        println("Thêm người mượn sách thành công")
    }

    override fun findBookByTitle() {
        println("Nhập tên sách cần tiìm : ")
        val tenSach = readlnOrNull()?.uppercase() ?: return
        val timkiem = listBook.filter { it.tenSach.uppercase().contains(tenSach) }

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
        if (listBook.isEmpty()) {
            println("Danh sách trống")
        } else {
            listBook.forEach {
                println(it)
            }
        }
    }

    override fun displayUser() {
        if (listUser.isEmpty()) {
            println("Danh sách trống")
        } else {
            listUser.forEach {
                println("Id : ${it.id} , Name : ${it.name}")
            }
        }
    }

    override fun borrowBook() {
        println("Nhập thông tin khách mượn sách")
        print("Nhập mã người dùng: ")
        val maKH = readlnOrNull()?.toIntOrNull() ?: return
        val user = listUser.find { it.id == maKH }
        if (user == null) {
            print("Nhập tên người dùng: ")
            val tenKH = readlnOrNull() ?: return

            val user = User(maKH, tenKH)
            listUser.add(user)
        }

        val sachChuaMuon = getSachChuaMuon()

        if (sachChuaMuon.isEmpty()) {
            println(" Không có sách nào có sẵn để mượn!")
            return
        }

        println("Danh sách sách chưa mượn:")
        sachChuaMuon.forEach { println(it) }

        print("Nhập id sách cần mượn: ")
        val idSachMuon = readlnOrNull()?.toIntOrNull() ?: return
        val sachMuon = sachChuaMuon.find { it.id == idSachMuon }

        if (sachMuon != null) {
            user?.sachDaMuon?.add(sachMuon)
            println("${user?.name} đã mượn sách: ${sachMuon.tenSach}")
        } else {
            println(" Không tìm thấy sách phù hợp!")
        }
    }

    override fun displayBookBorrowed() {
        listUser.forEach { user ->
            println("Danh sách sách đã mượn của người dùng ${user.name}")
            if (user.sachDaMuon.isEmpty()) {
                println("Chưa mượn sách nào.")
            } else {
                user.sachDaMuon.forEach { println(it) }
            }
        }
    }


    private fun getSachChuaMuon(): List<Book> {
        return listBook.filter { sach ->
            listUser.none() { user -> user.sachDaMuon.contains(sach) }
        }
    }
}