package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjLongConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjLongConsumer<String, String> consumer = BiObjLongConsumer.of((t, u, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(u);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjLongConsumer<String, String> consumer = BiObjLongConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
