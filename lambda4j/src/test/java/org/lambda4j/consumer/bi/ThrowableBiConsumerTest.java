package org.lambda4j.consumer.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiConsumer<String, String, Throwable> consumer = ThrowableBiConsumer.of((t, u) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(u);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiConsumer<String, String, Throwable> consumer = ThrowableBiConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
