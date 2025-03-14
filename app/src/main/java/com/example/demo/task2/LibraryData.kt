package com.example.demo.task2

import com.example.demo.task2.model.Book
import com.example.demo.task2.model.Publisher
import com.example.demo.task2.model.User

object LibraryData {
    //tạo object truy cập bất kì đâu
    val listPublisher = mutableListOf(
        Publisher(1,"NXB Giáo dục", "Hà Nội", "0123456789"),
        Publisher(2,"NXB Văn hóa Văn nghệ", "Hồ Chí Minh", "0987654321"),
        Publisher(3,"NXB Thế giới", "Đà Nẵng", "0123456780"),
        Publisher(4,"NXB Phụ nữ", "Hải Phòng", "0987654320"),
    )

    val listBook = mutableListOf(
        Book(1, "Dế Mèn Phiêu Lưu Ký", "Tô Hoài", 1941, "Văn học thiếu nhi", listPublisher[0].name),
        Book(2, "Sherlock Holmes", "Arthur Conan Doyle", 1892, "Trinh thám", listPublisher[1].name),
        Book(3, "Nhà Giả Kim", "Paulo Coelho", 1988, "Triết học", listPublisher[2].name),
        Book(4, "Tắt Đèn", "Ngô Tất Tố", 1939, "Hiện thực phê phán",listPublisher[3].name),
        Book(5, "Harry Potter và Hòn Đá Phù Thủy", "J.K. Rowling", 1997, "Fantasy", listPublisher[0].name),
        Book(6, "Chí Phèo", "Nam Cao", 1941, "Hiện thực phê phán", listPublisher[1].name),
        Book(7, "Bố Già", "Mario Puzo", 1969, "Tiểu thuyết", listPublisher[1].name),
        Book(8, "Đắc Nhân Tâm", "Dale Carnegie", 1936, "Kỹ năng sống", listPublisher[2].name),
        Book(9, "Tội Ác và Hình Phạt", "Fyodor Dostoevsky", 1866, "Văn học Nga", listPublisher[3].name),
        Book(10, "Thép Đã Tôi Thế Đấy", "Nikolai Ostrovsky", 1932, "Cách mạng", listPublisher[0].name)
    )

    val listUser = mutableListOf(
        User(1, "Nguyễn Văn A", mutableListOf(listBook[0], listBook[1])),
        User(2, "Trần Thị B"),
        User(3, "Lê Văn C")
    )
}