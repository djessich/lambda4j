# Nesting

Every throwable function provides the possibility to nest a possibly thrown *Throwable* in a *RuntimeException* to map the throwable function into an equal, non-throwable one. For that, you can use the *nest* or *nestWith* methods.

## nest

The *nest* method safely wraps a thrown *Throwable* from a throwable function in a *ThrownByFunctionalInterfaceException*, which is a subsidiary implementation of *RuntimeException*. Thereby the original *Throwable* message is used as message for the *ThrownByFunctionalInterfaceException* with the original *Throwable* appended to it (cause). The following example demonstrates this:

```java
ThrowableFunction<Object, Object, Exception> f = obj -> {
    throw new Exception("Exception thrown from a functional interface");
};

Throwable e = expectThrows(ThrownByFunctionalInterfaceException.class, f.nest().apply(null)); // JUnit 5
assertEquals("Exception thrown from a functional interface", e.getMessage());
```

*Note: Errors will never be wrapped but thrown as-is.*

## nestWith

The *nestWith* method does exactly the same as *nest* method, but with the difference that the *RuntimeException* used for wrapping the *Throwable*, which may be thrown by the throwable function, is created using a mapper function. This way of nesting allows you to implement your own way of handling the thrown *Throwable* from the thwowable function. The following example demonstrates this:

```java
ThrowableFunction<Object, Object, Exception> f = obj -> {
    throw new Exception("Exception thrown from a functional interface");
};
Function2<Object, Object> nestedF = f.nestWith(throwable -> new IllegalStateException(throwable.getMessage(), throwable));

Throwable e = expectThrows(IllegalStateException.class, nestedF.apply(null)); // JUnit 5
assertEquals("Exception thrown from a functional interface", e.getMessage());
```
