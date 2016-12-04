# Consumation

Most of the functional interfaces provide a *consume* method, which consumes the return type directly after the function is applied using a consuming function. The consuming function must be a function with *void* as its return type, to indicate a "consume" of the result from the original function (the function of which *compose* is called). In mathematics, we describe this with a function `f: T -> R` and a function `g: R -> 0`, whereas function `f` represents the original function and function `g` represents a consuming one. This yields to a function `h: g(f(x)) -> 0`.

This method is useful in situations where consuming functions are required but we have only a function which returns a result. So for example we have got a function which implements some altering operation. A method getting a consuming function as argument, will use the consuming function returned by `consume` method, rather then a consuming function declared using lambda syntax. The following example demonstrates the use of *consume* method:

```java
// We use a mapper function which changes each element before it is consumed by printing it to the console
Arrays.asList("Hello", "World").forEach(Function2.of(str -> "?" + str + "!").consume(System.out::println));
```
