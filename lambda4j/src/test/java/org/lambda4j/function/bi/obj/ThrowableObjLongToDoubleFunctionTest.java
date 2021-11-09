package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjLongToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjLongToDoubleFunction<String, Throwable> function =
                ThrowableObjLongToDoubleFunction.of((t, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjLongToDoubleFunction<String, Throwable> function = ThrowableObjLongToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
