package org.lambda4j.consumer.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriConsumer<String, String, String, Exception> consumer = ThrowableTriConsumer.of((t, u, v) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(u);
            Assertions.assertNotNull(v);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriConsumer<String, String, String, Exception> consumer = ThrowableTriConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
