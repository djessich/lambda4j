package org.lambda4j.consumer.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjDoubleConsumer2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjDoubleConsumer2<String> consumer = ObjDoubleConsumer2.of((t, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjDoubleConsumer2<String> consumer = ObjDoubleConsumer2.of(null);
        Assertions.assertNull(consumer);
    }
}
