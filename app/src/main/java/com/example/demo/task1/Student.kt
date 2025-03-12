package com.example.demo.task1

data class Student(
    var name: String,
    var age : Int,
    var gpa : Float,
    var gender : Char,
    var scholarship : Boolean = gpa >= 8
)
