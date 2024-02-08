import kotlinx.coroutines.*


//@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
fun main () {
    runBlocking {
//        launch(Dispatchers.Main) {
//            println("Main dispatcher: Thread: ${Thread.currentThread().name}")
//        }

        launch(Dispatchers.Unconfined) {
            println("Unconfined1 dispatcher: Thread: ${Thread.currentThread().name}")
            delay(1000L)
            println("Unconfined2 dispatcher: Thread: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Default) {
            println("Default dispatcher: Thread: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.IO) {
            println("IO dispatcher: Thread: ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("MyThread")) {
            println("newSingleThreadContext: Thread: ${Thread.currentThread().name}")
        }

    }
}