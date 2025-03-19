package com.example.demo.task2.model

data class Publisher (
    val id : Int,
    val name : String,
    val address : String,
    val contact : String
){
    override fun toString(): String {
        return "Publisher(id=$id, name='$name', address='$address', contact='$contact')"
    }
}