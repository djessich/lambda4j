package org.lambda4j.consumer.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjShortConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjShortConsumer<String> consumer = ObjShortConsumer.of((t, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjShortConsumer<String> consumer = ObjShortConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
