# Arity

The arity of a function specifies the number of arguments a function takes. The arity of a function can be determined by calling its *arity* method. As this library includes lambdas with arity `[0..3]` only, the *arity* method will return a nonnegative value in that range. Regarding the functional interfaces arity, we group them together in the following groups:

* nullary functions have an arity of 0 (f.e. *Supplier* or *Runnable* functional interfaces)
* unary functions have an arity of 1 (f.e. *Consumer*, *Function*, *UnaryOperator* or *Predicate* functional interfaces)
* binary functions have an arity of 2 (f.e. *BiConsumer*, *BiFunction*, *BinaryOperator* or *BiPredicate* functional interfaces)
* ternary functions have an arity of 3 (f.e. *TriConsumer*, *TriFunction*, *TernaryOperator* or *TriPredicate* functional interfaces)

The *arity* method can be called from any functional interface provided by this library. Refer to the following example.

```java
assertEquals("Function2 class arity is 1", 1, Function.constant(null).arity());
```
