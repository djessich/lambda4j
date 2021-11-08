package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBooleanToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBooleanToCharFunction<String> function = ObjBooleanToCharFunction.of((t, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBooleanToCharFunction<String> function = ObjBooleanToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
