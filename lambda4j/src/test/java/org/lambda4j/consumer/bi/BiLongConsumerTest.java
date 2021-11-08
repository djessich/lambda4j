package org.lambda4j.consumer.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiLongConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiLongConsumer consumer = BiLongConsumer.of((value1, value2) -> {
            Assertions.assertNotNull(value1);
            Assertions.assertNotNull(value2);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiLongConsumer consumer = BiLongConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
