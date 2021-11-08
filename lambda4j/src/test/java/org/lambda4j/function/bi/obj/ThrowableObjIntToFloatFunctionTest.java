package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjIntToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjIntToFloatFunction<String, Exception> function =
                ThrowableObjIntToFloatFunction.of((t, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjIntToFloatFunction<String, Exception> function = ThrowableObjIntToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
