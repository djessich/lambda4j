package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriLongFunction<String, Throwable> function =
                ThrowableTriLongFunction.of((value1, value2, value3) -> Long.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriLongFunction<String, Throwable> function =
                ThrowableTriLongFunction.of((ThrowableTriLongFunction<String, Throwable>) null);
        Assertions.assertNull(function);
    }
}
