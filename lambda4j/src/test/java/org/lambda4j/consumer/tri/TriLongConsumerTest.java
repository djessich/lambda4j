package org.lambda4j.consumer.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriLongConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriLongConsumer consumer = TriLongConsumer.of((value1, value2, value3) -> {
            Assertions.assertNotNull(value1);
            Assertions.assertNotNull(value2);
            Assertions.assertNotNull(value3);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriLongConsumer consumer = TriLongConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
