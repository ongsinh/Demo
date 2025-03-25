package com.example.demo.demo1.task3

//Covariance
open class Food(
    val name: String
)

class Pizza(name: String, val size: Int) : Food(name)
class Burger(name: String, isDouble: Boolean) : Food(name)

//tao interface FoodProvider
interface FoodProvider<out T : Food> {
    fun provideFood(): T //tra ve kieu dl T
}

class PizzaProvider : FoodProvider<Pizza> {
    override fun provideFood(): Pizza = Pizza("Peperoni", 10)
}

class BurgerProvider : FoodProvider<Burger> {
    override fun provideFood(): Burger = Burger("Cheese Burger", true)
}

fun getFoodProvider(provider: FoodProvider<Food>){
    val food = provider.provideFood()
    println("Recived ${food.name}")
}


//Contravarience -> in
open class Payment(val amount: Double)


class CashPayment (amount: Double , val recevidedAmount : Double ) : Payment(amount)
class CardPayment (amount: Double , val cardNumber : String) : Payment(amount)

interface PaymentProcess<in T : Payment>{
    fun paymentProcess(payment: T)
}

class GeneralPaymentProcess : PaymentProcess<Payment> {
    override fun paymentProcess(payment: Payment) {
        println("Processing payment of ${payment.amount} USD" )
    }

}

//Invariance
open class Customer(val name : String)

class VIPCustomer(name : String, val membership : String) : Customer(name)
class RegularyCustomer(name : String, amount: Double) : Customer(name)

// Class generic `CustomerList<T>` (Invariant)
class CustomerList<T>{
    private val customers = mutableListOf<T>()

    fun getAllCustommer() : List<T> = customers

    fun addCustomer(customer: T){
        customers.add(customer)
    }

}

fun main(){
    //khoi tao
    val pizzaProvider : FoodProvider<Pizza> = PizzaProvider()
    val burgerProvider : FoodProvider<Burger> = BurgerProvider()

    //convarient cho phep gan FoodProvider<Pizza> cho FoodProvider<Food>
    getFoodProvider(pizzaProvider)
    getFoodProvider(burgerProvider)


    //khoi tao
    val generalPaymentProcess : PaymentProcess<Payment> = GeneralPaymentProcess()
    val cashPayment : PaymentProcess<CashPayment> = generalPaymentProcess
    val cardPayment : PaymentProcess<CardPayment> = generalPaymentProcess

    cashPayment.paymentProcess(CashPayment(1000.0,10.0))
    cardPayment.paymentProcess(CardPayment(1500.0, "082611428832123"))


    val vipCustomer = CustomerList<VIPCustomer>()
    vipCustomer.addCustomer(VIPCustomer("Nguyen Van C", "BBBB"))
    val regularyCustomer = CustomerList<RegularyCustomer>()
    regularyCustomer.addCustomer(RegularyCustomer("Nguyen Van D", 100.0))

    //là Invariance không thể gán in hoặc out
    println("Vip Customer : ${vipCustomer.getAllCustommer().map { it.name }}")
    println("Regulary Customer : ${regularyCustomer.getAllCustommer().map { it.name }}")


}