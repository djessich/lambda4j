package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjShortConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjShortConsumer<String, String, Throwable> consumer =
                ThrowableBiObjShortConsumer.of((t, u, value) -> {
                    Assertions.assertNotNull(t);
                    Assertions.assertNotNull(u);
                    Assertions.assertNotNull(value);
                });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjShortConsumer<String, String, Throwable> consumer = ThrowableBiObjShortConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
