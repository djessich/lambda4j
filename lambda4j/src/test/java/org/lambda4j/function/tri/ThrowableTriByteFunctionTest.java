package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriByteFunction<String, Throwable> function =
                ThrowableTriByteFunction.of((value1, value2, value3) -> Byte.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriByteFunction<String, Throwable> function =
                ThrowableTriByteFunction.of((ThrowableTriByteFunction<String, Throwable>) null);
        Assertions.assertNull(function);
    }
}
