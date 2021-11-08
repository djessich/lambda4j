package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiBooleanFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiBooleanFunction<String, Exception> function =
                ThrowableBiBooleanFunction.of((value1, value2) -> Boolean.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiBooleanFunction<String, Exception> function =
                ThrowableBiBooleanFunction.of((ThrowableBiBooleanFunction<String, Exception>) null);
        Assertions.assertNull(function);
    }
}
