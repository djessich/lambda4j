package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriByteToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriByteToDoubleFunction<Throwable> function =
                ThrowableTriByteToDoubleFunction.of((value1, value2, value3) -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriByteToDoubleFunction<Throwable> function = ThrowableTriByteToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
