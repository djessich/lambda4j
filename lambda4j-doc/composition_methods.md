# Composition Methods

With Lambda4j you can compose functional interfaces together, which is called composition. Lambda4j thereby provides either *compose* or *andThen* method in each functional interface. In addition to this, if the functional interface is a primitive one, there are also primitive mapping composition methods available. Below you will find some more information on composition methods proided by Lambda4j.

## compose

The *compose* method allows to execute a function **before** the actual function (the function of which *compose* is called) is executed. This allows us, to modify the object handed over to the function, before the function is applied. In mathematics, we describe this with a function `f: T -> R` and a function `g: A -> T`, whereas function `g` represents the function which should be called before function `f`. This yields to a function `h: f(g(x))`, which maps `A -> R`. The following example demonstrates this:

```java
Function2<Integer, Integer> increment = i -> i + 1;
Function2<Integer, Integer> multiplyByTwo = i -> i * 2;

Function2<Integer, Integer> incrementAndMultiplyByTwo = multiplyByTwo.compose(increment);

assertEquals("(5 + 1) * 2 must be 12", 12, incrementAndMultiplyByTwo.apply(5));
```

Moreover, if the function represents a primitive one, which gets a primitive type, it provides additional primitive handling versions of the *compose* method for each known primitive type. These methods are named *composeFrom* plus the primitive type such as *composeFromInt*,  *composeFromLong* and *composeFromShort*.

## andThen

The *andThen* method allows to execute a function **after** the actual function (the function of which *andThen* is called) is executed. Therefore *andThen* represents the opposite method of *compose*. The method allows us, to modify the object returned by the actual function after it is applied. In mathematics, we describe this with a function `f: T -> R` and a function `g: R -> S`, whereas function `g` represents the function which should be called after function `f`. This yields to a function `h: g(f(x))`, which maps `T -> S`. The following example demonstrates this:

```java
Function2<Integer, Integer> increment = i -> i + 1;
Function2<Integer, Integer> multiplyByTwo = i -> i * 2;

Function2<Integer, Integer> incrementAndMultiplyByTwo = increment.andThen(multiplyByTwo);

assertEquals("(5 + 1) * 2 must be 12", 12, incrementAndMultiplyByTwo.apply(5));
```

Moreover, if the function represents a primitive one, which returns a primitive type, it provides additional primitive handling versions of the *andThen* method for each known primitive type. These methods are named *andThenTo* plus the primitive type such as *andThenToInt*, *andThenToLong* and *andThenToShort*.
