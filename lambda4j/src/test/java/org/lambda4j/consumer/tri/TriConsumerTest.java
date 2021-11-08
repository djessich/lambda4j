package org.lambda4j.consumer.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriConsumer<String, String, String> consumer = TriConsumer.of((t, u, v) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(u);
            Assertions.assertNotNull(v);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriConsumer<String, String, String> consumer = TriConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
