package com.example.demo.task3

//Hàm nhận một hàm khác làm tham số
fun operation(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

//
fun trick() {
    println("Trick!")
}

val trick = {
    println("Trick !")
}
val treat = {
    println("Treat !")
}

//haàm là loaại tra ve
fun trickOrTreat(isTrick: Boolean): () -> Unit {
    return if (isTrick) trick else treat
}

fun multiply(a: Int, b: Int): Int {
    return a * b
}

//trueen ham vao ham khac lam doi so
fun treatOrTrick(isTrick: Boolean, extraTreat: (Int) -> String): () -> Unit {
    if (isTrick) {
        return trick
    } else {
        println(extraTreat(5))
        return treat
    }
}

//inline function
inline fun excute(block: () -> Unit) {
    println("Truoc khi th")
    block()
    println("Sau khi thu hien")
}

//enum class -> tap hop cac gia tri co dinh, khong can trang thai phuc tap
enum class OderStatus {
    PENDING,
    PROCESSING,
    DELIVERY
}

fun CkeckStatus(oderStatus: OderStatus) {
    when (oderStatus) {
        OderStatus.PENDING -> println("Don hang dang duoc xu ly")
        OderStatus.PROCESSING -> println("Don hang dang duoc giao ")
        OderStatus.DELIVERY -> println("Don hang da duoc giao")
    }
}

//scald class
sealed class PaymentsStatus {
    object Success : PaymentsStatus()
    object Fail : PaymentsStatus()
    data class Pending(val estimatedTime: Int) : PaymentsStatus()
}

fun handlePaymentStatus(status: PaymentsStatus) {
    when (status) {
        is PaymentsStatus.Success -> println("Thành công")
        is PaymentsStatus.Fail -> println("Thất bại")
        is PaymentsStatus.Pending -> println("Thơi gioi xu ly du kien ${status.estimatedTime} giay")
    }
}

//generic trong class
class Box<T>(var item: T) {
    fun getItem() {
        println(item)
    }
}

//generic trong function
fun <T> printItem(item: T) {
    println(item)
}

//out trong generic trong class
class Person<out T>(val value: T) {
    fun get(): T {
        return value
    }
}

//int trong generic trong class
class Person1<in T>() {
    fun say(value: T) {
        println("${value.hashCode()}")
    }
}

//generic trong interface
interface Repository<T> {
    fun save(item: T)
    fun getById(id: Int): T
}

class UserRepository() : Repository<String> {
    override fun save(item: String) {
        println("Save user $item")
    }

    override fun getById(id: Int): String {
        return "User $id"
    }

}


interface Producer<out T> {
    fun produce(): T
}

interface Consumer<in T> {
    fun consumer(item: T)
}

class FruitProducer : Producer<String> {
    override fun produce(): String = "Apple"

}

class StringCosumer : Consumer<String> {
    override fun consumer(item: String) {
        println("Consuming $item")
    }
}

//variance
data class Course(val name: String)
class OddList<T>(val list: List<T>) {
    fun OddItem(): List<T> {
        return list.filterIndexed { index, _ -> index % 2 == 1 } //loc vi tri phần tử %2 = 1
    }
}

//Covariance
class CovarianceSample<T>



fun main() {
    val result = operation(3, 6) { a, b -> a + b }
    println(result)

    val result1 = operation(5, 9) { a, b -> a * b }
    println(result1)

    //lamda expression
    val add: (Int, Int) -> Int = { a, b -> a + b }
    println(add(3, 5))

    val multiplyfuc: (Int, Int) -> Int = ::multiply
    println(multiplyfuc(3, 5))

    trickOrTreat(true)()
    trickOrTreat(false)()

    val coins: (Int) -> String = { coins ->
        "$coins"
    }
    treatOrTrick(
        false,
        extraTreat = coins,
    )

    excute {
        println("Hello")
    }

    CkeckStatus(oderStatus = OderStatus.PENDING)
    val status = PaymentsStatus.Pending(5)
    handlePaymentStatus(status)

    val intBox = Box(10)
    val stringBox = Box("Hello")

    println(intBox.getItem())
    println(stringBox.getItem())

    /**
     * fatherObject la 1 super type cua sonObject
     * su dung tu khoa out gan gia tri cua sonObject cho fatherObject la dc
     */


    val sonObject = Person(Son())
    val fatherObject: Person<Father>
    fatherObject = sonObject

    val fatherObject1: Person1<Father> = Person1()
    val sonOnbject1: Person1<Son>
    sonOnbject1 = fatherObject1

    //out -> tra ve
    val fruitProducer: Producer<String> = FruitProducer()
    println(fruitProducer.produce())

    //in -> chi dung de ghi
    printItem(100)
    printItem("Nguyen Văn A")

    //in -> chi ghi
    val stringConsumer: Consumer<String> = StringCosumer()
    stringConsumer.consumer("Nguyen Van B")

    val userRepository = UserRepository()
    userRepository.save("Nguyen Van Anh")
    userRepository.getById(1)


    val listOfString = listOf("Nguyen Van A", "Nguyen Van B", "Nguyen Van C")
    val resultOfItem : OddList<String> = OddList(listOfString)

    println(resultOfItem.OddItem())

    val course = listOf(Course("Kotlin"), Course("Java"), Course("Python"))
    val resultOfCourse = OddList(course).OddItem()
    println(resultOfCourse)

    val secondSample : CovarianceSample<out Any> = CovarianceSample<String>()
}

