# Kotlin

```kotlin
var - mutable
val - immutable
```

## Data Types
```kotlin
fun main() {
    println("Hello, world!!! $name ${name.length}")
    // val is used for variables which are immutable (not changable)
    val myName = "Denis"
    // myName = "Michael" // ERROR: Val cannot be reassigned
 
    /*
     var is used for variables which are mutable/changeable. 
     Kotlin is a strongly typed language that supports Type Inference.
     The compiler will directly assume the size and type to a variable by that.
     For example, if you assign any whole number to a variable,
     the compiler will directly assume that the variable is of type Int
    */
    var myAge = 31
 
    // Integer TYPES: Byte (8 bit), Short (16 bit), Int (32 bit), Long (64 bit)
    val myByte: Byte = 127          // 2^7 - 1
    val myShort: Short = 32767      // 2^15 - 1
    val myInt: Int = 2_147_483_647  // 2^31 - 1
    val myLong: Long = 9_223_372_036_854_775_807    // 2^63 - 1
 
    // Floating Point number Types: Float (32 bit), Double (64 bit)
    val myFloat: Float = 13.37F
    val myDouble: Double = 3.14159265358979323846

    var isSunny: Boolean = true
 
    // Characters
    val letterChar = 'A'  // type Char
    val digitChar = '1'   // type Char
 
    // Strings
    val myStr = "Hello World"
    var firCharInStr = myStr[0]
    var lastCharInStr = myStr[myStr.length - 1]
}
```

## if when while for statements
```kotlin
if () { ... } 
else { ... }

// when
var season = 3
when(season) {
    1 -> println("Spring")
    2 -> println("Summer")
    3 -> {
        println("Fall")
        println("Autumn")
    }
    4 -> println("Winter")
    else -> println("invalid")
}

var month = 3
when(month) {
    in 3..5 -> println("Spring")
    in 6..8 -> println("Summer")
    in 9..11 -> println("Fall")
    12, 1, 2 -> println("Winter")
    else -> println("invalid")
}

when(age){
    !in 0..20  -> print("now you may drink in the US")
    in 18..20  -> print("now you may vote")
    16,17 -> print("you now may drive")
    else -> print("you're too young")
}

var x: Any = 13.37f
var x: Any = "string"
when(x) {
    is Int -> println("$x is an Int")
    !is Double -> println("$x is not Double")
    is String -> println("$x is a String")
    else -> println("$x is none of the above")
}
// $x is not Double
// $x is not Double

val x : Any = 13.37
val result =  when(x) {
    is Int -> "is an Int"
    !is Double -> "is not Double"
    is String -> "is a String"
    else -> "is none of the above"
}
print("$result") // 13.37 is none of the above

for(num in 1..10) {
    print("$num ")
}

for(i in 1 until 10) { // Same as - for(i in 1.until(10))
    print("$i ")
}

for(i in 10 downTo 1) {  // Same as - for(i in 10.downTo(1))
    print("$i ")
}

for(i in 10 downTo 1 step 2) { // Same as - for(i in 10.downTo(1).step(2))
    print("$i ") // 10 8 6 4 2
}
```

## function
```kotlin
fun addUp(a: Int, b: Int): Int {
    return a + b
}
```

## nullable
NULLABLES/OPTIONALS in Kotlin, that means You have the ability to declare whether a variable can hold a null value or not.
By supporting nullability in the type system, the compiler can detect possible NullPointerException errors at compile time

```kotlin
var name: String = "Denis"
name = null // Compilation Error!
 
var nullableName: String? = "Denis"
nullableName = null // Works
 
// name is not nullable
val len = name.length
val lower = name.toLowerCase()

val len2 = nullableName.length // Compilation Error
val lower2 = nullableName.toLowerCase()  // Compilation Error
 
// So how can we solve this? We could do a null check before hand 
if (nullableName != null) {
    println("Hello, ${nullableName.toLowerCase()}.")
    println("Your name is ${nullableName.length} characters long.")
} else {
    println("Hello, Guest")
}

// Kotlin provides a 'safe call' operator, ?.  
// It allows you to combine a null-check and a method call in a single expression.
nullableName?.length
nullableName?.toLowerCase()
 
// This is the same as:
if (nullableName != null)
    nullableName.toLowerCase()
else
    null
 
// You can use methods on a nullable variable like this
val nullableName3: String? = null
println(nullableName3?.toLowerCase()) // prints null
println(nullableName3?.length) // prints null
 
 、




// You can perform a chain safe calls:
val wifesAge: String? = user?.wife?.age
 
// In order to perform an operation only if the variable is not null, we can use the 'safe call' operator with 'let'
val nullableName4: String? = null 
nullableName4?.let { println(it.toLowerCase()) }
nullableName4?.let { println(it.length) }
// Prints nothing because nullableName4 is null 

// Then we can use the elvis operator ?: to enter a default value
val name = nullableName4 ?: "Guest"
val wifesAge: Int = user?.wife?.age ?: 0

// Not null assertion: !! operator. The !! operator converts a nullable type to a non-null type,
// and throws a NullPointerException if the nullable type holds a null value. 
// This is risky, and you should only use it if you are 100% certain, that there will be a value in the variable.
val nullableName5: String? = null
nullableName5!!.toLowerCase() // Results in NullPointerException
```

## OOP
### Class
```kotlin
class Person constructor(_firstName: String = "John", _lastName: String = "Doe") { // parameters of the constructor
    // properties of the class
    var firstName: String
    var lastName: String

    // Initializer Block
    init {
        this.firstName = _firstName
        this.lastName = _lastName
        println("Initialized a new Person object with firstName = $firstName and lastName = $lastName")
    }
}
 
// Alternatively:
class Person constructor(_firstName: String = "John", _lastName: String = "Doe") {
    var firstName: String = _firstName
    var lastName: String = _lastName
 

    init {
        println("Initialized a new Person object with firstName = $firstName and lastName = $lastName")
    }
}

// Alternative:
lass Person constructor(var firstName: String = "John", var lastName: String = "Doe") { // parameters of the constructor
    init {
        println("Initialized a new Person object with firstName = $firstName and lastName = $lastName")
    }
}

var mark = Person("Mark", "Zhang")
println("firstName ${ mark.firstName } and lastName ${mark.lastName}")
// Initialized a new Person object with firstName = Mark and lastName = Zhang
// firstName Mark and lastName Zhang
var john = Person()
println("firstName ${ john.firstName } and lastName ${john.lastName}")
// Initialized a new Person object with firstName = John and lastName = Doe
// firstName John and lastName Doe
 
class Person(firstName: String, lastName: String) {
    var age: Int? = null
    var hobby: String = "Watch Netflix"
    var myFirstName = firstName

    // Secondary Constructor
    constructor(firstName: String, lastName: String, age: Int): this(firstName, lastName)  {
        this.age = if (age > 0) age else throw IllegalArgumentException("Age must be greater than zero")
    }

    fun stateHobby(){
        println("$myFirstName\'s Hobby is: $hobby" )
    }
}
// You can use primary or secondary Constructor to create an object
// Calls the primary constructor (Age will be null in this case)
val person1 = Person("Denis", "Panjuta")
 
// Calls the secondary constructor
val person2 = Person("Elon", "Musk", 48) 

 
// Having multiple overloads:
class Person(var firstName: String, var lastName: String) {
    var age: Int? = null
    var eyeColor: String? = null
    
    // Secondary Constructor
    constructor(firstName: String, lastName: String, age: Int): this(firstName, lastName)  {
        this.age = if(age > 0) age else throw IllegalArgumentException("Age must be greater than zero")
    }
 
    // Secondary Constructor
    constructor(firstName: String, lastName: String, age: Int, eyeColor: String): 
            this(firstName, lastName, age)  {
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
        private set // cannot set it in other class

    init {
        // created car object will be with model "M3" by default, here it calls to set model
        this.model = "M3"
        this.owner = "Owner"
    }
}
```

### Data Class
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
```kotlin
// open - can be inherited by others
// sealed class - a class that cannot be inherited
open class Vehicle {}
open class Car: Vehicle() {}
class ElectricCar: Car() {}

open class Car(val name: String, val brand: String) {
    open var range: Double = 0.0

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
    // to override in a child class, has to be open in parent class
    override var range = batteryLife * 6
    // custom property for the child class
    var chargerType = "type1"

    // inherit from Car
    override fun drive(distance: Double) {
        println("Drove for $distance KM on electricity")
    }
    // method for ElectricCar class only - no params
    fun drive() {
        println("Drove for $range KM on electricity")
    }
}

var audiA3 = Car("A3", "Audi")
var teslaS = ElectricCar("ModelS", "Tesla", 85.0)
audiA3.drive(200.0)
teslaS.drive(200.0)
teslaS.drive()
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
                val brand: String): Drivable {
    open var range: Double = 0.0

    fun extendRange(amount: Double) {
        if (amount > 0)
            range += amount
    }

    // this override function is required
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
                    batteryLife: Double): Car(maxSpeed, name, brand) {
    override var range = batteryLife * 6
    var chargerType = "type1"

    // inherit from Car
    override fun drive(distance: Double) {
        println("Drove for $distance KM on electricity")
    }
    
    override fun drive(): String {
        return "Drove for $range KM on electricity"
    }

    override fun brake() {
        super.brake()
    }
}
```

## Abstract Class
A class can impletement multiple interfaces but only one class
Abstract class can have constructors and properties initilized

```kotlin
// you cannot instantiate an abstract class, but you can inherit from it
abstract class Mammal(private val name: String, // these 3 parameters are concrete (non abstract) properties
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

## Type and Casting
```kotlin
val stringList: List<String> = listOf("a", "b", "c")
val mixedTypeList: List<Any> = listOf("a", 31, b, "70.5")

for (value in mixedTypeList) {
    if (value is Int) {}
    else if (value is Double) {}
    else if (value is String) {}
    else {}

    // alternative
    when (value) {
        is Int -> println()
        is Double -> println()
        is String -> println()
        else -> println()
    }
}

// Casting
val obj1: Any = "string"
if (obj1 !is String) {
    println("Not a String")
} else {
    println("${obj1.length}")
}

// Explicit unsafe casting
val obj2: Any = 15
val str2: String = obj2 as String
str1.length    // ClassCastException

// Explicit safe casting
val obj3: Any = 1337
val str3: String? = obj3 as? String
str3 // null
```


## Collections
### Array
```kotlin
// type - IntArray/DoubleArray/BooleanArray...
val numbers: IntArray = intArrayOf(1, 2, 3, 4, 5)
val numbers = intArrayOf(1, 2, 3, 4, 5)
val numbers = arrayOf(1, 2, 3, 4, 5)
println(numbers.contentToString())

data class Fruit(val name: String, val price: Double)
val fruits = arrayOf(Fruit("Apple", 2.5), Fruit("Grape", 3.5))
print(fruits.contentToString())

for (index in fruits.indices) {
    println("${fruits[index].name} is in index $index")
}
```

### List
```kotlin
val months = listOf("Jan", "Feb", "March")
println(months[1])
val additionalMonths = months.toMutableList()
additionalMonths.add("Apr")     // [Jan, Feb, March, Apr]

val days = mutableListOf<String>("Mon", "Tue", "Wed")
days.add("Thu")
println(days)       // [Mon, Tue, Wed, Thu]
days[2] = "Sunday"  // [Mon, Tue, Sunday, Thu]
val removeList = mutableListOf<String>("Mon", "Wed")
days.removeAll(removeList)  // [Tue, Sunday, Thu]

val anyTypes = listOf(1, 2, 3, true, "string")
```





### Set/Map
```kotlin
val fruits = setOf("Orange", "Apple", "Apple", "Mango")
println(fruits.size)            // 3
println(fruits.toSortedSet())   // [Apple, Mango, Orange]

val newFruits = fruits.toMutableSet()
newFruits.add("Melon")
println(newFruits.toSortedSet())    // [Apple, Mango, Melon, Orange]


val daysWeek = mapOf(1 to "Monday", 2 to "Tuesday")
println(daysWeek[2])    // Tuesday
for (key in daysWeek.keys) {
    println("$key is to ${daysWeek[key]}")
}
val newDaysWeek = daysWeek.toMutableMap()
newDaysWeek[4] = "Thursday"
println(newDaysWeek.toSortedMap())  // {1=Monday, 2=Tuesday, 4=Thursday}
```

## Lambda function
```kotlin
// normal function
fun addNumber (a: Int, b: Int) {
    val answer = a + b
    print(answer)
}

// lambda
val sum: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
// or
val sum = { a: Int, b: Int -> println(a + b) }
sum(10, 5)
```

## Visibility Modifier
* `public`(default) - element is accessible from everywhere in the project
* `private` - element is accessible only within the block in which properties, fields are declared
* `protected` - modifier with a class or an interface allows visibility to its class or subclass only
* `internal` - makes the field visible only inside the module in which it is implemented
* All classes are `final` by default, so they can't be inherited - use `open` key word

```kotlin
open class Base {
    var a = 1                   // public by default
    private var b = 2           // private to Base class
    protected open val c = 3    // visible to the Base and the Derived class
    internal val d = 4          // visible in the same module
    protected fun e() {}        // visible to the Base and the Derived class
}
class Derived: Base() {
    // a, c, d and e() of the Base class are visible
    override val c = 9          // here, c is still protected
}

fun main(args: Array<String>) {
    val base = Base()
    // base.a and base.d are visible
    // base.b, base.c and base.e() are not visible
    val derived = Derived()
    // only derived.a and derived.d are visible
}
```

## Nested class Inner class
Nested class is a class which is created inside another class
Nested class is by default `static`
Nested class `cannot access` the data members of outer classes

Inner class is a class which is created inside another class with keyword `inner`
Inner class is able to access members of its outer class even it is private.
Inner class keeps a reference to an object of its outer class.

```kotlin
// Nested class
class OuterClass {
    private var name: String = "x"
    class NestedClass {
        var description: String = "xx"
        private var id: Int = 101
        fun foo() {
            // cannot access the outer class member `name`
        }
    }
}

fun main(args: Array<String>) {
    OuterClass.NestedClass().description    // can access member property
    var obj = OuterClass.NestedClass()
    obj.foo()                               // can access member function
}

class OuterClass {
    private var name: String = "x"
    inner class InnerClass {
        var description: String = "xx"
        private var id: Int = 101
        fun foo() {
            // can access the `private` outer class member `name`
            println("name is ${name}")
        }
    }
}

fun main(args: Array<String>) {
    OuterClass().InnerClass().description    // can access member property
    var obj = OuterClass().InnerClass()
    obj.foo()                               // can access member function
}
```

## Cast
Unsafe cast `as`
Safe cast `as?`

```kotlin
val obj: Any? = "nullable string"
val str: String? = obj as String?  // works

val location: Any = "Kotlin"
val safeString: String? = location as? String  // Kotlin
val safeInt: Int? = location as? Int  // null
```

## try catch
#### Unchecked Exception
* exception that is thrown due to mistakes in our code
* `Unchecked Exception` extends the `RuntimeException` class
* is checked at run time
* ArithmeticException
* ArrayIndexOutOfBoundException
* SecurityException
* NulPointerException

#### Checked Exception
* `Checked Exception` extends the `Throwable` class
* checked at compile time
* IOException
* SQLException

```kotlin
val str = getNumber("10.5")
println(str)    // 0
fun getNumber(str: String): Int {
    return try {
        Integer.parseInt(str)
    } catch (e: ArithmeticException) {
        0
    }
}

fun main(args: Array<String>) {
    validate(15)
    println("code after validation check")
}

fun validate(age: Int) {
    if (age < 18) {
        throw ArithmeticException("under age")
    } else {
        print()
    }
}
// Exception in thread "main: under age."
```

## dp sp
```kotlin
// LinearLayout
android:orientation="vertical"
android:layout_weight="1"
android:onClick="onPress"
(view as Button).text
android:padding="10dp"
android:gravity="right|bottom|center_vertical"

android:screenOrientation="portrait"
android:theme="@style/NoActionBarTheme"
// resize when soft keyboard displays
android:windowSoftInputMode="adjustResize"
```

sp: scalable pixel