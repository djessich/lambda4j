package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjIntToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjIntToCharFunction<String> function = ObjIntToCharFunction.of((t, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjIntToCharFunction<String> function = ObjIntToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
