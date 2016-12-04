# Partial Application

Partial application means applying a function partially, yielding a new function by pre-filling some of the arguments to the original function. Partial application helps you to create simpler functions from more complex ones by baking in data when you have it, which means that it is not required to wait until all arguments for a function are available. In mathematics, given a function `f: T1 x T2 x T3 -> R`, we describe the same function partially applied to `a` as `f(a): a x T2 x T3 -> R`.

If a functional interface has at least two or more input arguments, it provides the *papply* method. The following example demonstrates the use of *papply* method:

```java
IntBinaryOperator2 sum = (a, b) -> a + b;
IntUnaryOperator2 partiallyAppliedSum = sum.papplyAsInt(3); // (b) -> 3 + b
ssertEquals("3 + 7 = 10", 10, partiallyAppliedSum.applyAsInt(7)); // 10
```

The main difference to Currying is that a function of smaller arity is returned, which does not always mean that the function has an arity of one. So instead of returning a function of arity one, which again returns a function of arity one and so forth, we simply return a functional interface with decremented arity of the original one.
