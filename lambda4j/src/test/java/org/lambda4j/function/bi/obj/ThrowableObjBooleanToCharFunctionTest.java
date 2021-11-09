package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBooleanToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBooleanToCharFunction<String, Throwable> function =
                ThrowableObjBooleanToCharFunction.of((t, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBooleanToCharFunction<String, Throwable> function = ThrowableObjBooleanToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
