/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.gridtec.lambda4j.util;

import org.testng.annotations.Test;

import java.io.IOError;
import java.io.IOException;

/**
 * Tests {@link ThrowableUtils}.
 */
public class ThrowableUtilsTest {

    @Test(expectedExceptions = { Exception.class })
    public void testSneakyThrowCheckedException() throws Exception {
        throw ThrowableUtils.sneakyThrow(new Exception());
    }

    @Test(expectedExceptions = { IOException.class })
    public void testSneakyThrowCheckedException2() throws IOException {
        throw ThrowableUtils.sneakyThrow(new IOException());
    }

    @Test(expectedExceptions = { ClassNotFoundException.class })
    public void testSneakyThrowCheckedException3() throws ClassNotFoundException {
        throw ThrowableUtils.sneakyThrow(new ClassNotFoundException());
    }

    @Test(expectedExceptions = { NumberFormatException.class })
    public void testSneakyThrowCheckedException4() throws NumberFormatException {
        throw ThrowableUtils.sneakyThrow(new NumberFormatException());
    }

    @Test(expectedExceptions = { Exception.class })
    public void testSneakyThrowCheckedExceptionUndeclared() {
        throw ThrowableUtils.sneakyThrow(new Exception());
    }

    @Test(expectedExceptions = { IOException.class })
    public void testSneakyThrowCheckedExceptionUndeclared2() {
        throw ThrowableUtils.sneakyThrow(new IOException());
    }

    @Test(expectedExceptions = { ClassNotFoundException.class })
    public void testSneakyThrowCheckedExceptionUndeclared3() {
        throw ThrowableUtils.sneakyThrow(new ClassNotFoundException());
    }

    @Test(expectedExceptions = { NumberFormatException.class })
    public void testSneakyThrowCheckedExceptionUndeclared4() {
        throw ThrowableUtils.sneakyThrow(new NumberFormatException());
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void testSneakyThrowUncheckedException() {
        throw ThrowableUtils.sneakyThrow(new RuntimeException());
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testSneakyThrowUncheckedException2() {
        throw ThrowableUtils.sneakyThrow(new IllegalArgumentException());
    }

    @Test(expectedExceptions = { NullPointerException.class })
    public void testSneakyThrowUncheckedException3() {
        throw ThrowableUtils.sneakyThrow(new NullPointerException());
    }

    @Test(expectedExceptions = { IllegalStateException.class })
    public void testSneakyThrowUncheckedException4() {
        throw ThrowableUtils.sneakyThrow(new IllegalStateException());
    }

    @Test(expectedExceptions = { Error.class })
    public void testSneakyThrowUncheckedError() {
        throw ThrowableUtils.sneakyThrow(new Error());
    }

    @Test(expectedExceptions = { IOError.class })
    public void testSneakyThrowUncheckedError2() {
        throw ThrowableUtils.sneakyThrow(new IOError(new IOException()));
    }

    @Test(expectedExceptions = { NullPointerException.class })
    public void testSneakyThrowUncheckedWithNullValue() {
        throw ThrowableUtils.sneakyThrow(null);
    }
}