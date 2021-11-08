package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriIntFunction<String, Exception> function =
                ThrowableTriIntFunction.of((value1, value2, value3) -> Integer.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriIntFunction<String, Exception> function =
                ThrowableTriIntFunction.of((ThrowableTriIntFunction<String, Exception>) null);
        Assertions.assertNull(function);
    }
}
