package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjDoubleConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjDoubleConsumer<String, String> consumer = BiObjDoubleConsumer.of((t, u, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(u);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjDoubleConsumer<String, String> consumer = BiObjDoubleConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
