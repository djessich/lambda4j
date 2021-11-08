package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjBooleanConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjBooleanConsumer<String, String> consumer = BiObjBooleanConsumer.of((t, u, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(u);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjBooleanConsumer<String, String> consumer = BiObjBooleanConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
