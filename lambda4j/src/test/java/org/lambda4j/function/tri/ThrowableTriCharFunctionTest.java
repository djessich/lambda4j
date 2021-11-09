package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriCharFunction<String, Throwable> function =
                ThrowableTriCharFunction.of((value1, value2, value3) -> Character.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriCharFunction<String, Throwable> function =
                ThrowableTriCharFunction.of((ThrowableTriCharFunction<String, Throwable>) null);
        Assertions.assertNull(function);
    }
}
