package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBooleanFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBooleanFunction<String, String, Exception> function =
                ThrowableObjBooleanFunction.of((t, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBooleanFunction<String, String, Exception> function = ThrowableObjBooleanFunction.of(null);
        Assertions.assertNull(function);
    }
}
