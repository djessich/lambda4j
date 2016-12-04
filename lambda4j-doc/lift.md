# Lift

Lifting allows you to transform a function which is not defined for each input argument, into a corresponding function which is defined for each input argument using *Optional* wrapper as return value. Each function provides such a *lift* method, which may be used to ensure non-null results if a function is applied. The following example demonstrates this:

```java
// Create some function, which is not defined for all input arguments
Function2<String, String> function = Function2.identity();

// Lift the function to ensure we have a result
Function2<String, Optional<String>> lifted = Function2.lift(function);

// Apply the function
assertNotNull(lifted.apply(null));
assertEquals(Optional.empty(), lifted.apply(null));
```
