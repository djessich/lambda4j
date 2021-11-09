package org.lambda4j.consumer.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiCharConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiCharConsumer<Throwable> consumer = ThrowableBiCharConsumer.of((value1, value2) -> {
            Assertions.assertNotNull(value1);
            Assertions.assertNotNull(value2);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiCharConsumer<Throwable> consumer = ThrowableBiCharConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
