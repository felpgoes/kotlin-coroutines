import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main () {
    runBlocking {
        val firstDeferred = async { getFirstValue() }
        val secondDeferred = async { getSecondValue() }

        println("Doing some processing...")
        delay(500L)
        println("Waiting for values")

//        val firstValue = firstDeferred.await()
//        val secondValue = secondDeferred.await()

        val (f, s) = awaitAll(firstDeferred, secondDeferred)

        println("The total is ${f + s}")
    }
}

suspend fun getFirstValue(): Int {
    delay(1000L)
    val value =  Random.nextInt(100)
    println("getFirstValue.value=$value")
    return value
}

suspend fun getSecondValue(): Int {
    delay(2000L)
    val value =  Random.nextInt(1000)
    println("getSecondValue.value=$value")
    return value
}