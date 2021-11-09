package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriShortFunction<String, Throwable> function =
                ThrowableTriShortFunction.of((value1, value2, value3) -> Short.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriShortFunction<String, Throwable> function =
                ThrowableTriShortFunction.of((ThrowableTriShortFunction<String, Throwable>) null);
        Assertions.assertNull(function);
    }
}
