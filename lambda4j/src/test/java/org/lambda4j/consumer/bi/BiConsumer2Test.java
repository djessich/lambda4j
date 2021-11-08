package org.lambda4j.consumer.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiConsumer2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiConsumer2<String, String> consumer = BiConsumer2.of((t, u) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(u);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiConsumer2 consumer = BiConsumer2.of(null);
        Assertions.assertNull(consumer);
    }
}
