package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjShortConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjShortConsumer<String, String> consumer = BiObjShortConsumer.of((t, u, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(u);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjShortConsumer<String, String> consumer = BiObjShortConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
