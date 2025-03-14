//package com.example.demo.task3
//
////Hàm nhận một hàm khác làm tham số
//fun operation(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
//    return operation(a, b)
//}
//
////
//fun trick() {
//    println("Trick!")
//}
//
//val trick = {
//    println("Trick !")
//}
//val treat ={
//    println("Treat !")
//}
//
////haàm là loaại tra ve
//fun trickOrTreat(isTrick: Boolean): () -> Unit{
//    return if(isTrick) trick else treat
//}
//fun multiply(a: Int, b: Int): Int {
//    return a * b
//}
////trueen ham vao ham khac lam doi so
//fun treatOrTrick(isTrick: Boolean, extraTreat: (Int) -> String): () -> Unit {
//    if (isTrick) {
//        return trick
//    } else {
//        println(extraTreat(5))
//        return treat
//    }
//}
////inline function
//inline fun excute(block: () -> Unit){
//    println("Truoc khi th")
//    block()
//    println("Sau khi thu hien")
//}
//
////enum class -> tap hop cac gia tri co dinh, khong can trang thai phuc tap
//enum class OderStatus{
//    PENDING,
//    PROCESSING,
//    DELIVERY
//}
//
//fun CkeckStatus(oderStatus: OderStatus){
//    when(oderStatus){
//        OderStatus.PENDING -> println("Don hang dang duoc xu ly")
//        OderStatus.PROCESSING -> println("Don hang dang duoc giao ")
//        OderStatus.DELIVERY -> println("Don hang da duoc giao")
//    }
//}
//
////scald class
//sealed class PaymentsStatus{
//    object Success : PaymentsStatus()
//    object Fail : PaymentsStatus()
//    data class Pending (val estimatedTime : Int) : PaymentsStatus()
//}
//
//fun handlePaymentStatus(status: PaymentsStatus){
//    when(status){
//        is PaymentsStatus.Success -> println("Thành công")
//        is PaymentsStatus.Fail -> println("Thất bại")
//        is PaymentsStatus.Pending -> println("Thơi gioi xu ly du kien ${status.estimatedTime} giay")
//    }
//}
//
////generic trong class
//class Box<T>(var item : T ){
//    fun getItem(): T = item
//}
//fun main() {
//    val result = operation(3, 6) { a, b -> a + b }
//    println(result)
//
//    val result1 = operation(5, 9) { a, b -> a * b }
//    println(result1)
//
//    //lamda expression
//    val add: (Int, Int) -> Int = { a, b -> a + b }
//    println(add(3, 5))
//
//    val multiplyfuc: (Int, Int) -> Int = ::multiply
//    println(multiplyfuc(3, 5))
//
//    trickOrTreat(true)()
//    trickOrTreat(false)()
//
//    val coins: (Int) -> String = { coins ->
//        "$coins"
//    }
//    treatOrTrick(
//        false,
//        extraTreat = coins,
//    )
//
//    excute {
//        println("Hello")
//    }
//
//    CkeckStatus(oderStatus = OderStatus.PENDING)
//    val status = PaymentsStatus.Pending(5)
//    handlePaymentStatus(status)
//
//    val intBox = Box(10)
//    val stringBox = Box("Hello")
//
//}
//
