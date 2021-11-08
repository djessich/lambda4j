package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjByteConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjByteConsumer<String, String> consumer = BiObjByteConsumer.of((t, u, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(u);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjByteConsumer<String, String> consumer = BiObjByteConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
