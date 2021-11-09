package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriIntToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriIntToDoubleFunction<Throwable> function =
                ThrowableTriIntToDoubleFunction.of((value1, value2, value3) -> 0.0d);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriIntToDoubleFunction<Throwable> function = ThrowableTriIntToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
