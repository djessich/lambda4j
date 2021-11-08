package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBooleanToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBooleanToLongFunction<String, Exception> function =
                ThrowableObjBooleanToLongFunction.of((t, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBooleanToLongFunction<String, Exception> function = ThrowableObjBooleanToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
