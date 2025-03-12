package com.example.demo

data class Student(
    var name: String,
    var age : Int,
    var gpa : Float,
    var gender : Char,
    var scholarship : Boolean = gpa >= 8
)
