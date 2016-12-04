# Memoization

Memoization is an optimization technique to speed up a functions execution using caching. A memoized function executes with given parameters once and caches the result before returning it using an internal cache. When the function is called with the same arguments again, then the cached result is returned, instead of computing the result again. We can say, that a memoized function "remembers" the result regarding its input parameters.

If a functional interface has at least one input parameter and returns a result which can be referred to in the cache, the functional interface provides the *memoized* method. This method returns an equal function which represents a memoized version of the actual function (the function of which *memoized* is called), which therefore caches its computed values. The following example will implement the calculation of the factorial of a given number, but as we will use *memoized* method, the result is only computed once.

First we need to provide the factorial calculation algorithm, so we create a method to be called later by the memoized function:

```java
long factorial(long n) {
    if (n <= 1) {
        return 1;
    } else {
        return n * factorial(n - 1);
    }
}
```

Next, we will create a new memoized function which calls the factorial computation algorithm from above, by using the functions input as input for the algorithm. If the function is applied one or multiple times, the factorial will be computed only once.

```java
LongUnaryOperator2 memoized = LongUnaryOperator2.of(n -> factorial(n)).memoized();

assertEquals("10! equals 3.628.800", 3628800, memoized.applyAsLong(10)); // calculates the factorial
assertEquals("10! equals 3.628.800", 3628800, memoized.applyAsLong(10)); // returns from caches
```
