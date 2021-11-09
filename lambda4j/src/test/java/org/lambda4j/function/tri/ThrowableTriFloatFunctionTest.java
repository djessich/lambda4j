package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriFloatFunction<String, Throwable> function =
                ThrowableTriFloatFunction.of((value1, value2, value3) -> Float.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriFloatFunction<String, Throwable> function =
                ThrowableTriFloatFunction.of((ThrowableTriFloatFunction<String, Throwable>) null);
        Assertions.assertNull(function);
    }
}
