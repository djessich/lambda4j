package org.lambda4j.consumer.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiIntConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiIntConsumer<Throwable> consumer = ThrowableBiIntConsumer.of((value1, value2) -> {
            Assertions.assertNotNull(value1);
            Assertions.assertNotNull(value2);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiIntConsumer<Throwable> consumer = ThrowableBiIntConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
