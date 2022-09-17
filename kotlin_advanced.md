## OOP

### enum class

```kotlin
enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VOILET
}

enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);

    fun rgb() = (r * 256 + g) * 256 + b
}
println(Color.BLUE.rgb()) // 255

fun getWarmth(color: Color) = when(color) {
    Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
    Color.GREEN -> "neutral"
    Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
}
println(getWarmth(Color.ORANGE)) // warm

fun mix(c1: Color, c2: Color) = when (setOf(c1, c2)) {
    setOf(RED, YELLOW) -> ORANGE
    setOf(YELLOW, BLUE) -> GREEN
    setOf(BLUE, VIOLET) -> INDIGO
    else -> throw Exception("Dirty color")
}
println(mix(BLUE, YELLOW)) // GREEN
```

### Class

```kotlin
// primary constructor
class Person(var firstName: String = "John", var lastName: String = "Doe")

// The same but detailed
class Person constructor(_firstName: String = "John", _lastName: String = "Doe") {
    // properties of the class
    var firstName: String
    var lastName: String

    // initializer block
    init {
        this.firstName = _firstName
        this.lastName = _lastName
        println("Initialized a new Person object with firstName = $firstName and lastName = $lastName")
    }
}

// alternative:
class Person(_firstName: String = "John", _lastName: String = "Doe") {
    var firstName: String = _firstName
    var lastName: String = _lastName

    init {
        println("Initialized a new Person object with firstName = $firstName and lastName = $lastName")
    }
}

// alternative:
// parameters of the constructor
class Person constructor(var firstName: String = "John", var lastName: String = "Doe") {
    init {
        println("Initialized a new Person object with firstName = $firstName and lastName = $lastName")
    }
}

var mark = Person("Mark", "Zhang")  // initialized a new Person object with firstName = Mark and lastName = Zhang
var john = Person()  // initialized a new Person object with firstName = John and lastName = Doe

class Person(var firstName: String, var lastName: String) {
    var age: Int? = null
    var hobby: String = "Watch Netflix"
    var myFirstName = firstName

    // secondary constructor
    constructor(firstName: String, lastName: String, age: Int): this(firstName, lastName)  {
        this.age = if (age > 0) age else throw IllegalArgumentException("Age must be greater than zero")
    }

    fun stateHobby(){
        println("$myFirstName\'s Hobby is: $hobby" )
    }
}
// use primary constructor (age will be null)
val person1 = Person("Denis", "Panjuta")

// use secondary constructor
val person2 = Person("Elon", "Musk", 48)

// class with multiple overloads:
class Person(var firstName: String, var lastName: String) {
    var age: Int? = null
    var eyeColor: String? = null

    // Secondary Constructor
    constructor(firstName: String, lastName: String, age: Int): this(firstName, lastName)  {
        this.age = if(age > 0) age else throw IllegalArgumentException("Age must be greater than zero")
    }

    // Secondary Constructor
    constructor(firstName: String, lastName: String, age: Int, eyeColor: String): this(firstName, lastName, age)  {
        this.eyeColor = eyeColor
    }
}

class Car() {
    lateinit var owner: String

    val brand: String = "BMW"
        // custom getter
        get() {
            return  field.lowercase()
        }

    var maxSpeed: Int = 250
        get() = field
        set(value) {
            field = if (value > 0) value else throw IllegalArgumentException("maxSpeed cannot be less than 0")
        }

    var model: String = "M5"
        // accessor visibility
        private set // cannot set it in other class

    init {
        // created car object will be with model "M3" by default, here it calls to set model
        this.model = "M3"
        this.owner = "Owner"
    }
}
```

### Getter/Setter

```kotlin
class User(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println("""Address was changed for $name:
            "$field" -> "$value".""".trimIndent())
            field = value
        }
}
val user = User("Alice")
user.address = "Elsenheimerstrasse 47, 80687 Muenchen"
// Address was changed for Alice:
// "unspecified" -> "Elsenheimerstrasse 47, 80687 Muenchen".
```

### Data Class

In `data class`, necessary methods are automatically generated for you. e.g. toString(), equals(), and hashCode(). Properties that aren't declared in the `primary constructor` don't take part in the `equality` checks and `hash code` calculation.

```kotlin
data class User(val id: Long, var name: String)
val user1 = User(1, "Ziyi")
val (id, name) = user1  // de-constructor

user1.id = 2            // cannot change because of val
user1.name = "Mark"     // works because of var
val user2 = User(1, "Mark")
user1.equals(user2)     // true
val updatedUser = user1.copy(name="updated name")
print(user1)            // (id=1, name=Mark)
print(updatedUser)      // (id=1, name=updated name)
```

## Inheritance

`open` - can be inherited by others

`sealed` - cannot be inherited

```kotlin
open class Vehicle {}
open class Car: Vehicle() {}
class ElectricCar: Car() {}

open class Car(val name: String, val brand: String) {
    open var range: Double = 0.0
    // final by default
    fun extendRange(amount: Double) {
        if (amount > 0)
            range += amount
    }
    open fun drive(distance: Double) {
        println("Drove for $distance KM")
    }
}

class EletricCar(name: String, brand: String): Car(name, brand) {...}
audiA3.drive(200.0) // Drove for 200 KM
teslaS.drive(200.0) // Drove for 200 KM
teslaS.extendRange(200)

class EletricCar(name: String, brand: String, batteryLife: Double): Car(name, brand) {
    // to override in a child class, has to be OPEN in the parent class
    override var range = batteryLife * 6
    // custom property for the child class
    var chargerType = "type1"

    // inherit from Car
    override fun drive(distance: Double) {
        println("Drove for $distance KM on electricity")
    }
    // method for ElectricCar class only - no parameter
    fun drive() {
        println("Drove for $range KM on electricity")
    }
}

var audiA3 = Car("A3", "Audi")
var teslaS = ElectricCar("ModelS", "Tesla", 85.0)

// implement properties declared in interface
interface User {
    val nickname: String
}
class PrivateUser(override val nickname: String): User
class SubscribingUser(val email: String): User {
    override val nickname: String
        get() = email.substringBefore("@")
}
class FacebookUser(val accountId: Int): User {
    override val nickname = getFacebookName(accountId)
}
```

### Sealed class

一个有特定数量子类的类，看上去和枚举有点类似，所不同的是，在枚举中，我们每个类型只有一个对象(实例)；而在密封类中，同一个类可以拥有几个对象。

```kotlin
sealed class Expr {
    class Num(val value: Int): Expr()
    class Sum(val left: Expr, val right: Expr): Expr()
}
// The “when” expression covers all possible cases, so no “else” branch is needed
fun eval(e: Expr): Int = when(e) {
    is Expr.Num -> e.value
    is Expr.Sum -> eval(e.right) + eval(e.left)
}
```

## Interface

```kotlin
interface Drivable {
    val maxSpeed: Double
    // may have implementation or not
    fun drive(): String
    fun brake() {
        println("The drivable is braking")
    }
}

open class Car(override val maxSpeed: Double,
                val name: String,
                val brand: String
): Drivable {
    open var range: Double = 0.0
    fun extendRange(amount: Double) {
        if (amount > 0)
            range += amount
    }
    // this override function is required - public by default
    override fun drive(): String {
        return "driving the interface drive"
    }
    open fun drive(distance: Double) {
        println("Drove for $distance KM")
    }
}

class EletricCar(maxSpeed: Double,
                    name: String,
                    brand: String,
                    batteryLife: Double
): Car(maxSpeed, name, brand) {
    // override range in the Car class
    override var range = batteryLife * 6
    var chargerType = "type1"

    // inherit from Car
    override fun drive(distance: Double) {
        println("Drove for $distance KM on electricity")
    }
    override fun drive(): String {
        return "Drove for $range KM on electricity"
    }
    // override Drivable interface
    override fun brake() {
        super.brake()
    }
}
```

## Abstract Class

A `class` can impletement multiple `interfaces` but only one `class`.

`Abstract class` can have constructors and properties initilized

```kotlin
// cannot instantiate an abstract class, but you can inherit from it
// these 3 parameters are concrete (non abstract) properties
abstract class Mammal(private val name: String,
                      private val origin: String,
                      private val weight: Double) {
    // abstract property must be overridden by subclass
    abstract var maxSpeed: Double

    // abstract methods must be implemented by subclass
    abstract fun run()
    abstract fun breath()

    // concrete (non abstract) method
    fun displayDetails() {
        print("Name: $name, Origin: $origin, Weight: $weight, maxSpeed: $maxSpeed")
    }
}

class Human(name: String,
            origin: String,
            weight: Double,
            override var maxSpeed: Double): Mammal(name, origin, weight) {
    override fun run() {
        print("run() implementation")
    }
    override fun breath() {
        print("breath() implementation")
    }
}
```

## Coroutines

The primary thread, which manages the work the user interacts with directly, is called the `main thread` or `UI thread`.
`Coroutines` provide a high-level and safer set of tools to help you build asynchronous code.

When you use a coroutine to perform tasks like making a network request, the code making the request will suspend while the task is performed. When your code is in this suspended state, other parts of your program can still execute, freeing up your main thread and keeping your program responsive. When the network request completes, the suspended code resumes right where it left off.

Behind the scenes, Kotlin saves and restores the function state between suspending function calls. This allows the original function call to be temporarily freed from memory until it is ready to be resumed. Because of these optimizations, coroutines are considerably more resource efficient than native threads.

Most coroutine builders also start the coroutine immediately after creating it. Several builders are defined for you in the Coroutines library. The most commonly used coroutine builder is `launch`, a function defined as an extension to a class called `CoroutineScope`. You will see more information on scopes shortly; for now, you will use the subclass `GlobalScope`.

Launch a new coroutine by wrapping your call to `fetchFlight` in a call to the `launch` function defined on `GlobalScope`.

```kotlin
data class FlightStatus(
    val flightNumber: String,
    val passengerName: String,
    val passengerLoyaltyTier: String,
    val originAirport: String,
    val destinationAirport: String,
    val status: String,
    val departureTimeInMinutes: Int
) {
    companion object {
        fun parse(
            flightResponse: String,
            loyaltyResponse: String,
            passengerName: String
        ): FlightStatus {
            val (flightNumber, originAirport, destinationAirport,
                status, departureTimeInMinutes) = flightResponse.split(",")

            val (loyaltyTierName, milesFlown, milesToNextTier) =
                loyaltyResponse.split(",")

            return FlightStatus(
                flightNumber,
                passengerName,
                loyaltyTierName,
                originAirport,
                destinationAirport,
                status,
                departureTimeInMinutes.toInt()
            )
        }
    }
}

fun main() {
    runBlocking {
        println("Started")
        launch {
            val flight = fetchFlight("Mark")
            println(flight)
        }
        println("Finished")
    }
}

suspend fun fetchFlight(passengerName: String): FlightStatus {
    val client= HttpClient(CIO)
    val flightResponse = client.get<String>(FLIGHT_ENDPOINT)
    val loyaltyResponse = client.get<String>(LOYALTY_ENDPOINT)
    return FlightStatus.parse(flightResponse, loyaltyResponse, passengerName)
}
```

`async` is a coroutine builder that can be used as an alternative to `launch`. Much like `launch`, `async` accepts a lambda expression as an argument, which is where you can call other code that suspends. The big difference between these two functions is their return types: `launch` returns a `Job`, but `async` returns an instance of `Deferred`.

A `Deferred` value represents a value that might not be ready at this moment, but will be available eventually.

To access the deferred value, you call `await` on the `Deferred`. This `await` is also a suspend function: When you call it, it will immediately return the result if the work has finished, otherwise it will suspend until the value is ready.

```kotlin
/*
Started
Finished
Started fetching flight info
Started fetching loyalty info
Combining flight data
Finished fetching loyalty info
Finished fetching flight info
FlightStatus(flightNumber=JW3178, passengerName=Mark, passengerLoyaltyTier=Silver, originAirport=CYM, destinationAirport=XXF, status=Canceled, departureTimeInMinutes=35)
*/
fun main() {
    runBlocking {
        println("Started")
        launch {
            val flight = fetchFlight("Mark")
            println(flight)
        }
        println("Finished")
    }
}

suspend fun fetchFlight(passengerName: String): FlightStatus = coroutineScope {
    val client= HttpClient(CIO)
    val flightResponse = async {
        println("Started fetching flight info")
        client.get<String>(FLIGHT_ENDPOINT).also {
            println("Finished fetching flight info")
        }
    }

    val loyaltyResponse = async {
        println("Started fetching loyalty info")
        client.get<String>(LOYALTY_ENDPOINT).also {
            println("Finished fetching loyalty info")
        }
    }

    delay(500)
    println("Combining flight data")

    FlightStatus.parse(flightResponse.await(), loyaltyResponse.await(), passengerName)
}

```
