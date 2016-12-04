# Currying

Currying is a technique of converting a function that takes multiple arguments into a function that takes them one at a time. Each time the function is called, it only accepts one argument and returns a function that takes one argument until all arguments are passed. The original functions arguments are bound from left to right in its curried version. In mathematics, given a function with `n` parameters `f: T1 x T2 x ... x TN -> R`, we describe the modified, curried version of this function as `curry(f): T1 -> (T2 -> (...(TN -> R)...))`. The curried function returns, if applied, a new function `f'` with one parameter less of function `f`.

So if a functional interface has one or more input parameters and returns a result of same type as its input parameters, it provides a *curried* method, which will return an equal curried version of the functional interface itself. The following example demonstrates the use of *curried* method:

```java
BiFunction2<Integer, Integer, Integer> sum = (a, b) -> a + b; // f

Function2<Integer, Function2<Integer, Integer>> curriedSum = sum.curried(); // curry(f) = a -> b -> a + b
Function2<Integer, Integer> fixedToFirst = curriedSum.apply(10); // first parameter a fixed to value 10; returns f'

assertEquals("10 + 5 = 15", 15, fixedToFirst.apply(5)); // second parameter b fixed to value 5; returns result
```
