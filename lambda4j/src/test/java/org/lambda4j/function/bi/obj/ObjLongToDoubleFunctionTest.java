package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjLongToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjLongToDoubleFunction<String> function = ObjLongToDoubleFunction.of((t, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjLongToDoubleFunction<String> function = ObjLongToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
