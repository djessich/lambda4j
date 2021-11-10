package org.lambda4j.supplier;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

class Supplier2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        Supplier2<String> supplier = Supplier2.of(() -> "");
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        Supplier2<String> supplier = Supplier2.of(null);
        Assertions.assertNull(supplier);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void lift_givenExpression_returnsFunctionalInterface(String ret) {
        Supplier2<Optional<String>> supplier = Supplier2.lift(() -> ret);
        Assertions.assertNotNull(supplier);
        Optional<String> optional = Assertions.assertDoesNotThrow(supplier::get);
        Assertions.assertNotNull(optional);
        if (ret == null) {
            Assertions.assertFalse(optional.isPresent());
            Assertions.assertThrows(NoSuchElementException.class, optional::get);
        } else {
            Assertions.assertTrue(optional.isPresent());
            Assertions.assertEquals(ret, optional.get());
        }
    }

    @Test
    void lift_givenNull_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> Supplier2.lift(null));
    }
}
