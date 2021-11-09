package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriBooleanToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriBooleanToShortFunction<Throwable> function =
                ThrowableTriBooleanToShortFunction.of((value1, value2, value3) -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriBooleanToShortFunction<Throwable> function = ThrowableTriBooleanToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
