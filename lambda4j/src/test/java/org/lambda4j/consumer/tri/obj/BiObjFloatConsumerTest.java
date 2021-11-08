package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjFloatConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjFloatConsumer<String, String> consumer = BiObjFloatConsumer.of((t, u, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(u);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjFloatConsumer<String, String> consumer = BiObjFloatConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
