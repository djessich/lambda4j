package org.lambda4j.predicate.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBooleanPredicateTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBooleanPredicate<String, Exception> predicate =
                ThrowableObjBooleanPredicate.of((t, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBooleanPredicate<String, Exception> predicate = ThrowableObjBooleanPredicate.of(null);
        Assertions.assertNull(predicate);
    }
}
