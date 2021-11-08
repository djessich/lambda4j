package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiDoubleFunction<String, String, Exception> function =
                ThrowableObjBiDoubleFunction.of((t, value1, value2) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiDoubleFunction<String, String, Exception> function = ThrowableObjBiDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
