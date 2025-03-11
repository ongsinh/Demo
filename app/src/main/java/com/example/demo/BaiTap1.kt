package com.example.demo

import java.io.Console

data class Student(
    var id : Long,
    var name: String,
    var age : Int,
    var gpa : Float,
    var gender : Char,
    var scholarship : Boolean = gpa >= 8
)

fun gender(age: Int) : String{
    return when{
        age < 18 -> "Vị thành niên"
        age in 18..22 -> "Vị thành niên"
        else -> "Đã tót nghiệp hoăc đi làm "
    }
}

fun inputGPA() : Float{
    while(true){
        println("Nhap GPA : ")
        val gpa = readLine()?.toFloatOrNull()
        if(gpa != null && gpa in  0.0..10.0) return gpa
        println("Nhập lại điểm GPA")
    }
}

fun main(){
    var listStudents = mutableListOf(
        Student(1, "Nguyen Van A", 19, 8.5f, 'M', true),
        Student(2, "Tran Thi B", 22, 6.2f, 'F', false),
        Student(3, "Le Van C", 17, 9.0f, 'M', true),
        Student(4, "Pham Thi D", 21, 5.5f, 'F', false),
        Student(5, "Hoang Van E", 20, 7.8f, 'M', false),
        Student(6, "Nguyen Thi F", 23, 8.9f, 'F', true),
        Student(7, "Do Van G", 18, 6.7f, 'M', false),
        Student(8, "Pham Van H", 19, 9.2f, 'M', true),
        Student(9, "Le Thi I", 20, 6.9f, 'F', false),
        Student(10, "Vu Van J", 22, 8.0f, 'M', true),
        Student(11, "Bui Thi K", 18, 7.0f, 'F', false),
        Student(12, "Ngo Van L", 23, 9.5f, 'M', true),
        Student(13, "Pham Thi M", 21, 6.2f, 'F', false),
        Student(14, "Tran Van N", 19, 7.6f, 'M', false),
        Student(15, "Nguyen Thi O", 20, 8.3f, 'F', true),
        Student(16, "Dinh Van P", 17, 5.5f, 'M', false),
        Student(17, "Le Thi Q", 22, 9.8f, 'F', true),
        Student(18, "Bui Van R", 18, 6.0f, 'M', false),
        Student(19, "Vu Thi S", 21, 8.1f, 'F', true),
        Student(20, "Hoang Van T", 19, 7.2f, 'M', false),
        Student(21, "Pham Thi U", 20, 9.4f, 'F', true),
        Student(22, "Tran Van V", 22, 5.8f, 'M', false),
        Student(23, "Le Thi W", 17, 7.9f, 'F', false),
        Student(24, "Ngo Van X", 23, 8.6f, 'M', true),
        Student(25, "Nguyen Thi Y", 18, 6.3f, 'F', false),
        Student(26, "Do Van Z", 21, 9.1f, 'M', true),
        Student(27, "Bui Thi AA", 20, 7.4f, 'F', false),
        Student(28, "Pham Van BB", 19, 8.7f, 'M', true),
        Student(29, "Tran Thi CC", 22, 6.1f, 'F', false),
        Student(30, "Vu Van DD", 17, 7.5f, 'M', false),
        Student(31, "Le Thi EE", 18, 9.0f, 'F', true),
        Student(32, "Nguyen Van FF", 23, 6.8f, 'M', false),
        Student(33, "Pham Thi GG", 21, 7.3f, 'F', false),
        Student(34, "Hoang Van HH", 20, 8.5f, 'M', true),
        Student(35, "Bui Thi II", 19, 5.7f, 'F', false),
        Student(36, "Tran Van JJ", 22, 9.9f, 'M', true),
        Student(37, "Ngo Thi KK", 17, 6.4f, 'F', false),
        Student(38, "Vu Van LL", 18, 7.1f, 'M', false),
        Student(39, "Le Thi MM", 21, 8.2f, 'F', true),
        Student(40, "Nguyen Van NN", 19, 5.9f, 'M', false),
        Student(41, "Pham Thi OO", 20, 9.3f, 'F', true),
        Student(42, "Do Van PP", 22, 6.5f, 'M', false),
        Student(43, "Bui Thi QQ", 17, 7.7f, 'F', false),
        Student(44, "Tran Van RR", 23, 8.4f, 'M', true),
        Student(45, "Le Thi SS", 18, 5.6f, 'F', false),
        Student(46, "Nguyen Van TT", 21, 9.6f, 'M', true),
        Student(47, "Pham Thi UU", 20, 6.6f, 'F', false),
        Student(48, "Hoang Van VV", 19, 7.0f, 'M', false),
        Student(49, "Bui Thi WW", 22, 8.8f, 'F', true),
        Student(50, "Tran Van XX", 23, 5.4f, 'M', false)
    )

    //in thông tin từng sinh vien
    for(item in listStudents){
        println(item.toString())
    }

    //set : Chua cac nganh hoc khong trung lap
    val courses = setOf("Math", "English","VietNamese")
    println("Danh sach khoa hoc : $courses" )

    //map : anh xa id voi ten sinh vien
    val studentsMap = listStudents.associateBy { it.id }
    println("Danh sach sinh vien $studentsMap")

    //tim sinh vien có học bổng
    val schoolarshipStudent = listStudents.filter { it.scholarship }
    println("Danh sach sinh vien có học bổng : $schoolarshipStudent")

    //tinh tong GPA
    val totalGPAStudent = listStudents.map { it.gpa }.reduce { acc, gpa -> acc + gpa }
    val avgGPA = totalGPAStudent / listStudents.size
    println("GPA trung binh : $avgGPA")


    //fist va last
    val firstStudent = listStudents.first()
    val lastStudent = listStudents.last()
    println(firstStudent)
    println(lastStudent)


    while(true){
        println("Chọn chức năng : ")
        println("1. Nhập sinh viên ")
        println("2. Sắp xếp sinh viên ")
        println("3. Thống kê số lượng sinh viên theo từng học lực ")
        println("5. In danh sach sv")
        println("4. Thoát")
        println("Nhap lua chon : ")
        when(readLine()?.toIntOrNull()){
            1 -> {
                print("Nhap id sinh vien :")
                val id = readLine()?.toLongOrNull() ?: return
                println("Nhap ten sv :")
                val name = readLine()?.toString() ?: return
                print("Nhap tuoi sv :")
                val age = readLine()?.toIntOrNull() ?: return
                val gpa = inputGPA()
                print("Nhập giới tính (M/F): ")
                val gender = readLine()?.firstOrNull()?.uppercaseChar() ?: return
                listStudents.add(Student(id, name, age, gpa, gender))
                println("Đã thêm sinh viên ")
            }

            2 -> {
                listStudents.sortByDescending { it.gpa }
                println("Danh sach sv sau khi sx : $listStudents")
            }

            3 -> {
                val thongkeSV = listStudents.groupBy {
                    when {
                        it.gpa >= 8 -> "Gioi"
                        it.gpa >= 6.5 -> "Kha"
                        it.gpa > 5 -> "Trung binh"
                        else -> "Yếu"
                    }
                }

                println("Thống ke so luong sinh vien theo hoc luc")
                thongkeSV.forEach { (hocLuc, sinhVien) ->
                    println("$hocLuc : ${sinhVien.size}")
                }
            }

            4 -> return
            else -> println("Vui lòng nhạp lại")
        }
    }

}

