package org.lambda4j.consumer.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiByteConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiByteConsumer consumer = BiByteConsumer.of((value1, value2) -> {
            Assertions.assertNotNull(value1);
            Assertions.assertNotNull(value2);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiByteConsumer consumer = BiByteConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}