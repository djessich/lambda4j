package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriDoubleFunction<String, Exception> function =
                ThrowableTriDoubleFunction.of((value1, value2, value3) -> Double.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriDoubleFunction<String, Exception> function =
                ThrowableTriDoubleFunction.of((ThrowableTriDoubleFunction<String, Exception>) null);
        Assertions.assertNull(function);
    }
}
