package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjDoubleConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjDoubleConsumer<String, String, Throwable> consumer =
                ThrowableBiObjDoubleConsumer.of((t, u, value) -> {
                    Assertions.assertNotNull(t);
                    Assertions.assertNotNull(u);
                    Assertions.assertNotNull(value);
                });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjDoubleConsumer<String, String, Throwable> consumer = ThrowableBiObjDoubleConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
