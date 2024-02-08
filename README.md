
## Coroutines

As **coroutines** são conceitos básicos em programação assíncrona. Aqui estão alguns termos importantes relacionados a coroutines:

### Escopo (Scope)

- O escopo (ou *scope*) é responsável por criar e executar coroutines. Ele também fornece eventos de ciclo de vida.
- O escopo define o contexto no qual a coroutine é executada.

### Contexto (Context)

- O contexto é um conjunto de dados relacionados à coroutine.
- Todos os coroutines têm um contexto associado.
- Elementos importantes do contexto incluem:
    - **Dispatcher**: Determina em qual thread ou *thread pool* a coroutine é executada.
    - **Job**: Representa o ciclo de vida da coroutine.

### Funções de Suspensão (Suspending Functions)

- As funções de suspensão são aquelas que podem ser executadas em uma coroutine.
- Elas permitem que chamadas assíncronas sejam feitas de forma mais natural.
- Exemplo de função de suspensão:
    ```kotlin
    suspend fun dizerOla() {
        println("Olá!")
    }
    ```

### Jobs

- Um *job* é retornado quando usamos `.launch()` para criar uma coroutine.
- Os *jobs* permitem manipular o ciclo de vida da coroutine.
- Eles vivem na hierarquia de outros *jobs*, podendo ser pais ou filhos.
- Se um *job* é cancelado, todos os seus pais e filhos também serão cancelados.

### Dispatchers

- Um *dispatcher* determina em qual thread ou *thread pool* a coroutine é executada.
- Existem diferentes *dispatchers* disponíveis, dependendo da especificidade da tarefa.
- Alguns *dispatchers* comuns são:
    - **Main**: Usado para atualizações na interface do usuário (por exemplo, em aplicativos Android).
    - **Default**: Útil para tarefas intensivas de CPU.
    - **IO**: Útil para comunicação de rede ou leitura/escrita de arquivos.
    - **Unconfined**: Inicia a coroutine na thread atual.


## async

`async` é semelhante ao `launch`, exceto que ele retorna um resultado na forma de uma `Deferred` (promessa futura de um valor retornado). Quando precisamos do valor, chamamos `await()` (chamada bloqueante):
- Se o valor estiver disponível, ele será retornado imediatamente.
- Se o valor não estiver disponível, a execução será pausada até que ele esteja.

## async

```kotlin
suspend fun getRandom() = Random.nextInt(1000)
val valueDeferred = GlobalScope.async { getRandom() }
// Faça algum processamento aqui
val finalValue = valueDeferred.await()
```

## withContext

`withContext` nos permite alterar facilmente o contexto, alternando entre despachantes. É muito leve:
```kotlin
launch(Dispatchers.Default) {
    // Contexto padrão
    withContext(Dispatchers.IO) {
        // Contexto de I/O
    }
    // De volta ao contexto padrão
}
```
## Tratamento de exceções

Manipulador de exceções:
```kotlin
val myHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    // Lidar com a exceção
}

launch(myHandler) {
    // Faça alguma tarefa aqui
    throw IndexOutOfBoundsException()
}

val myHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    // Lidar com a exceção
}

launch(Dispatchers.Default + myHandler) {
    // Faça alguma tarefa aqui
    throw IndexOutOfBoundsException()
}
```