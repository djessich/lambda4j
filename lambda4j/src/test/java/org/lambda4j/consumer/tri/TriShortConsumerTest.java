package org.lambda4j.consumer.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriShortConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriShortConsumer consumer = TriShortConsumer.of((value1, value2, value3) -> {
            Assertions.assertNotNull(value1);
            Assertions.assertNotNull(value2);
            Assertions.assertNotNull(value3);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriShortConsumer consumer = TriShortConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
