package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriDoubleFunction<String, Throwable> function =
                ThrowableTriDoubleFunction.of((value1, value2, value3) -> Double.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriDoubleFunction<String, Throwable> function =
                ThrowableTriDoubleFunction.of((ThrowableTriDoubleFunction<String, Throwable>) null);
        Assertions.assertNull(function);
    }
}
