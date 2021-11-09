package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjByteToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjByteToCharFunction<String, Throwable> function =
                ThrowableObjByteToCharFunction.of((t, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjByteToCharFunction<String, Throwable> function = ThrowableObjByteToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
