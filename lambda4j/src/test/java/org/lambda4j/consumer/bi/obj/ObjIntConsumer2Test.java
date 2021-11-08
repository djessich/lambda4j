package org.lambda4j.consumer.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjIntConsumer2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjIntConsumer2<String> consumer = ObjIntConsumer2.of((t, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjIntConsumer2<String> consumer = ObjIntConsumer2.of(null);
        Assertions.assertNull(consumer);
    }
}
