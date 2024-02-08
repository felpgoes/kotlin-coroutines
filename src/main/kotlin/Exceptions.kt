import kotlinx.coroutines.*

fun main () {
    runBlocking {
        val expectationHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("Exception handled: ${throwable.localizedMessage}")
        }

        val job = GlobalScope.launch(expectationHandler) {
            println("Throwing exception from job!")
            throw RuntimeException("Deu erro aqui!")
        }
        job.join()

        val deferred = GlobalScope.async {
            println("Throwing exception from async!")
            throw RuntimeException("Deu erro aqui também!")
        }

        try {
            deferred.await()
        } catch (e: RuntimeException) {
            println("Exception handled: ${e.localizedMessage}")

        }
    }
}