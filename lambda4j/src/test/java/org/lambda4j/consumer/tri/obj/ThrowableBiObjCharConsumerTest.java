package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjCharConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjCharConsumer<String, String, Exception> consumer =
                ThrowableBiObjCharConsumer.of((t, u, value) -> {
                    Assertions.assertNotNull(t);
                    Assertions.assertNotNull(u);
                    Assertions.assertNotNull(value);
                });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjCharConsumer<String, String, Exception> consumer = ThrowableBiObjCharConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
