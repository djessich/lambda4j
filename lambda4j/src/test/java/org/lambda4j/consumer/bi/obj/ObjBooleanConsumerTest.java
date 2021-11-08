package org.lambda4j.consumer.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBooleanConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBooleanConsumer<String> consumer = ObjBooleanConsumer.of((t, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBooleanConsumer<String> consumer = ObjBooleanConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
