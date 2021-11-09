package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiBooleanFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiBooleanFunction<String, Throwable> function =
                ThrowableBiBooleanFunction.of((value1, value2) -> Boolean.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiBooleanFunction<String, Throwable> function =
                ThrowableBiBooleanFunction.of((ThrowableBiBooleanFunction<String, Throwable>) null);
        Assertions.assertNull(function);
    }
}
