# Composition Methods

With Lambda4j you can compose functional interfaces together, which is called composition. In mathematics, function composition is the application of one function to the result of another to produce a third function. This means for example, that function f: T -> R and function g: R -> S can be composed to yield a function h: g(f(x)), which maps T -> S.

Lambda4j provides either `compose` or `andThen` methods. If the functional interface is a primitive one, there are also primitive mapping composition methods available. The following examples will demonstrate each available method.

## compose

The *compose* method allows to execute a function before the actual function (the function of which *compose* is called) is executed. This allows us, to modify the object handed over to the function, before the function is applied. In mathematics, we describe this with a function f: T -> R and a function g: A -> T, whereas function g is the function which should be called before function f. This yields to a function h: f(g(x)), which maps A -> R. The following example demonstrates this:

```java
Function<Integer, Integer> increment = i -> i + 1;
Function<Integer, Integer> multiplyByTwo = i -> i * 2;

Function<Integer, Integer> incrementAndMultiplyByTwo = multiplyByTwo.compose(increment);

assertEquals("(5 + 1) * 2 must be 12", 12, incrementAndMultiplyByTwo.apply(5))
```

Moreover, if the function represents a primitive one, which means either getting or returning a primitive type, it provides primitive handling versions of the *compose* method for each known primitive type. These methods are named for example *composeFromInt* or *composeFromLong*. The main purpose for these methods is to provide a mapping of either an `Object` or a primitive type to the primitive type handled by the actual primitive function. The following examples gives an insight into these methods by using the *composeFromInt* method:

```java
ShortToIntFunction increment = s -> s + 1
IntToLongFunction2 multiplyByTwo = i -> i * 2;

ShortToLongFunction incrementAndMultiplyByTwo = multiplyByTwo.composeFromShort(increment);

assertEquals("(5 + 1) * 2 must be 12", 12, incrementAndMultiplyByTwo.apply(5))
assertEquals("result must be of type long.class", long.class, incrementAndMultiplyByTwo.apply(5).getClass())
```
