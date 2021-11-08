package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriFloatFunction<String, Exception> function =
                ThrowableTriFloatFunction.of((value1, value2, value3) -> Float.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriFloatFunction<String, Exception> function =
                ThrowableTriFloatFunction.of((ThrowableTriFloatFunction<String, Exception>) null);
        Assertions.assertNull(function);
    }
}
