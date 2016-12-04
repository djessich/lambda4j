# Partial Application

Partial application means applying a function partially, yielding a new function by pre-filling some of the arguments to the original function. To partially apply a functional interface you can use *papply* method of it. Partial application helps you to create simpler functions from more complex ones by baking in data when you have it. The following example demonstrates this:

```java
IntBinaryOperator2 sum = (a, b) -> a + b;
IntUnaryOperator2 partiallyAppliedSum = sum.papplyAsInt(3); // (b) -> 3 + b
partiallyAppliedSum.applyAsInt(7); // 10
```

The main difference to Currying is that a functional interface of smaller arity is returned. So instead of returning a functional interface of arity one, which again returns a functional interface of arity one and so forth, we simply return a functional interface with decremented arity of the original one.
