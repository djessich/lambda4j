package org.lambda4j.function.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiByteFunction<String, Exception> function =
                ThrowableBiByteFunction.of((value1, value2) -> Byte.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiByteFunction<String, Exception> function =
                ThrowableBiByteFunction.of((ThrowableBiByteFunction<String, Exception>) null);
        Assertions.assertNull(function);
    }
}
