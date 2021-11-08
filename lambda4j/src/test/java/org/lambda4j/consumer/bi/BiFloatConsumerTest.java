package org.lambda4j.consumer.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiFloatConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiFloatConsumer consumer = BiFloatConsumer.of((value1, value2) -> {
            Assertions.assertNotNull(value1);
            Assertions.assertNotNull(value2);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiFloatConsumer consumer = BiFloatConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
