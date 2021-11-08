package org.lambda4j.consumer.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjLongConsumer2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjLongConsumer2<String> consumer = ObjLongConsumer2.of((t, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjLongConsumer2<String> consumer = ObjLongConsumer2.of(null);
        Assertions.assertNull(consumer);
    }
}
