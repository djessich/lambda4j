package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjByteConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjByteConsumer<String, String, Exception> consumer =
                ThrowableBiObjByteConsumer.of((t, u, value) -> {
                    Assertions.assertNotNull(t);
                    Assertions.assertNotNull(u);
                    Assertions.assertNotNull(value);
                });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjByteConsumer<String, String, Exception> consumer = ThrowableBiObjByteConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
