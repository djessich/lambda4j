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
package org.lambda4j.generator.entities;

import java.io.Serializable;

/**
 * The annotation entity defining all annotations to be used in Velocity templates.
 */
public class AnnotationEntity implements Serializable {

    /**
     * The {@link javax.annotation.Nonnull} annotation.
     */
    private static final String nonnull = "@Nonnull";

    /**
     * The {@link javax.annotation.Nullable} annotation.
     */
    private static final String nullable = "@Nullable";

    /**
     * The {@link javax.annotation.Nonnegative} annotation.
     */
    private static final String nonnegative = "@Nonnegative";

    /**
     * Returns the {@link javax.annotation.Nonnull} annotation for use in templates.
     *
     * @return The {@link javax.annotation.Nonnull} annotation for use in templates.
     */
    public String getNonnull() {
        return nonnull;
    }

    /**
     * Returns the {@link javax.annotation.Nullable} annotation for use in templates.
     *
     * @return The {@link javax.annotation.Nullable} annotation for use in templates.
     */
    public String getNullable() {
        return nullable;
    }

    /**
     * Returns the {@link javax.annotation.Nonnegative} annotation for use in templates.
     *
     * @return The {@link javax.annotation.Nonnegative} annotation for use in templates.
     */
    public String getNonnegative() {
        return nonnegative;
    }
}
