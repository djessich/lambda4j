package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjLongToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjLongToCharFunction<String> function = ObjLongToCharFunction.of((t, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjLongToCharFunction<String> function = ObjLongToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
