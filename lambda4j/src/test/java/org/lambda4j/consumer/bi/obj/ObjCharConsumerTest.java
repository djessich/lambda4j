package org.lambda4j.consumer.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjCharConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjCharConsumer<String> consumer = ObjCharConsumer.of((t, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjCharConsumer<String> consumer = ObjCharConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
