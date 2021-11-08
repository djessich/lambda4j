package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiDoubleToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiDoubleToLongFunction<String, Exception> function =
                ThrowableObjBiDoubleToLongFunction.of((t, value1, value2) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiDoubleToLongFunction<String, Exception> function = ThrowableObjBiDoubleToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
