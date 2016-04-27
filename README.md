Spring Boot Web - Sync and Async calls
===

Simple project to demonstrate how to execute synchronous and asynchronous tasks using Spring.

### Synchronous
There is 1 synchronous task that will spend at least 5 seconds to return:

1. <http://localhost:8080/sync>

  Simplest way to execute a task using request and request style. The request thread will block until that task is done;

### Asynchronous
There are 3 asynchronous tasks that will be executed in another thread:

1. <http://localhost:8080/async>

  Using `@Async` annotation and enabling that functionality with `@EnableAsync`, you tell Spring to execute the annotated method asynchronously. This method can return `void` or `Future<T>`. Spring will automatically manage the thread pool or you can create one implementing `AsyncConfigurer`.
  
2. <http://localhost:8080/callable>
3. <http://localhost:8080/deferred>
  
  Deferred and Callable works almost the same way: Spring will create another thread to execute the slow process and release the thread from the originated request. However, the client connection will be waiting the response and Spring will call the `Callable` return or execute the `Deferred` when it is complete. Then return the result to the client.
  The main difference is that Spring will manage the thread pool when using `Callable` and, with `Deferred`, you can specify an existing `ExecutorService`.