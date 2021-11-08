package org.lambda4j.consumer.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiIntConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiIntConsumer consumer = BiIntConsumer.of((value1, value2) -> {
            Assertions.assertNotNull(value1);
            Assertions.assertNotNull(value2);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiIntConsumer consumer = BiIntConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
