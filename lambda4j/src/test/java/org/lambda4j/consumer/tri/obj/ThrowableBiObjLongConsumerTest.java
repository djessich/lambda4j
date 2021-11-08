package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjLongConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjLongConsumer<String, String, Exception> consumer =
                ThrowableBiObjLongConsumer.of((t, u, value) -> {
                    Assertions.assertNotNull(t);
                    Assertions.assertNotNull(u);
                    Assertions.assertNotNull(value);
                });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjLongConsumer<String, String, Exception> consumer = ThrowableBiObjLongConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
