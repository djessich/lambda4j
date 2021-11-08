package org.lambda4j.consumer.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjFloatConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjFloatConsumer<String> consumer = ObjFloatConsumer.of((t, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjFloatConsumer<String> consumer = ObjFloatConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
