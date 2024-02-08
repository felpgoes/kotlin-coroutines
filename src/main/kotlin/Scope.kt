import kotlinx.coroutines.*

fun main() {
    println("[1] Starting block")

    runBlocking {
        launch {
            delay(1000L)
            println("[2] Task from runBlocking")
        }

        GlobalScope.launch {
            delay(500L)
            println("[3] Task from GlobalScope")
        }

        coroutineScope {
            launch {
                delay(1500L)
                println("[4] Task from coroutineScope")
            }
        }
    }

    println("[5] Block finished")
}