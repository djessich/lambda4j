/*
 * Copyright (c) 2021 The lambda4j authors.
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
package org.lambda4j;

import java.io.Serializable;

/**
 * A general interface which each functional interface declared in this library is inherited from. It is used for
 * general things which all functional interfaces have in common.
 */
public interface Lambda extends Serializable {

    /**
     * The <a href="https://docs.oracle.com/javase/8/docs/api/index.html">serial version uid</a>.
     */
    long serialVersionUID = 1L;

    /**
     * Checks if this functional interface is memoizing (= caching) computed values.
     *
     * @return {@code true} if this functional interface is memoizing, {@code false} otherwise.
     */
    default boolean isMemoized() {
        return this instanceof Memoized;
    }

    /**
     * Zero Abstract Method (ZAM) interface for marking lambdas as memoized lambdas. This is done by using a cast with
     * this interface as additional bound (intersection type).
     */
    interface Memoized {

    }
}
