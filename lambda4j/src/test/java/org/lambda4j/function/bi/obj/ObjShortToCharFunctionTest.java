package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjShortToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjShortToCharFunction<String> function = ObjShortToCharFunction.of((t, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjShortToCharFunction<String> function = ObjShortToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
