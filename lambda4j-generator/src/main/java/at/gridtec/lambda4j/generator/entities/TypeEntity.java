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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents am entity for representing a lambda type.
 */
@SuppressWarnings("unused")
public class TypeEntity implements Serializable {

    /**
     * The lambda types class.
     */
    private Class<?> typeClass;

    /**
     * The lambda types type name.
     */
    private String typeName;

    /**
     * The lambda types name.
     */
    private String name;

    /**
     * Which argument is this type in a lambda? This should be {@code 1-3} for primitive types and {@code 0} for return
     * types.
     */
    private int count;

    /**
     * The lambda types simple name, which is the same as {@link Class#getSimpleName()}.
     */
    private String typeSimpleName;

    /**
     * Whether this type is primitive or not.
     */
    private boolean primitive;

    /**
     * Default constructor for reflection purposes.
     */
    public TypeEntity() {

    }

    /**
     * Constructs this {@link TypeEntity} from given type class, type name and name. Thereby the primitive flag is set
     * using {@link Class#isPrimitive()}.
     *
     * @param typeClass The types class
     * @param typeName The types type name
     * @param name The type name
     * @throws NullPointerException If one of the given arguments is {@code null}
     */
    public TypeEntity(@Nonnull final Class<?> typeClass, @Nonnull final String typeName, @Nonnull final String name) {
        Objects.requireNonNull(typeClass);
        Objects.requireNonNull(typeName);
        Objects.requireNonNull(name);
        this.typeClass = typeClass;
        this.typeName = typeName;
        this.name = name;
        this.typeSimpleName = typeClass.getSimpleName();
        this.primitive = typeClass.isPrimitive();
    }

    /**
     * Constructs this {@link TypeEntity} from given type class, type name, name and argument count. Thereby the
     * primitive flag is set using {@link Class#isPrimitive()}.
     *
     * @param typeClass The types class
     * @param typeName The types type name
     * @param name The type name
     * @param count The types count
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @throws IllegalArgumentException If given count is < 0
     */
    public TypeEntity(@Nonnull final Class<?> typeClass, @Nonnull final String typeName, @Nonnull final String name,
            @Nonnegative int count) {
        Objects.requireNonNull(typeClass);
        Objects.requireNonNull(typeName);
        Objects.requireNonNull(name);
        if (count < 0) {
            throw new IllegalArgumentException();
        }
        this.typeClass = typeClass;
        this.typeName = typeName;
        this.name = name;
        this.count = count;
        this.typeSimpleName = typeClass.getSimpleName();
        this.primitive = typeClass.isPrimitive();
    }

    /**
     * Returns the types class.
     *
     * @return The types class.
     */
    public Class<?> getTypeClass() {
        return typeClass;
    }

    /**
     * Sets the types class.
     *
     * @param typeClass The types class to be set.
     */
    public void setTypeClass(Class<?> typeClass) {
        this.typeClass = typeClass;
    }

    /**
     * Returns the types type name.
     *
     * @return The types type name.
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Sets the types type name.
     *
     * @param typeName The types type name to be set.
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Returns the types name.
     *
     * @return The types name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the types name.
     *
     * @param name The types name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the types count.
     *
     * @return The types count.
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the types count.
     *
     * @param count The types count to be set.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Returns the types type simple name.
     *
     * @return The types type simple name.
     */
    public String getTypeSimpleName() {
        return typeSimpleName;
    }

    /**
     * Sets the types type simple name.
     *
     * @param typeSimpleName The types type simple name to be set.
     */
    public void setTypeSimpleName(String typeSimpleName) {
        this.typeSimpleName = typeSimpleName;
    }

    /**
     * Returns the types primitive flag.
     *
     * @return The types primitive flag.
     */
    public boolean isPrimitive() {
        return primitive;
    }

    /**
     * Sets the types primitive flag.
     *
     * @param primitive The types primitive flag to be set.
     */
    public void setPrimitive(boolean primitive) {
        this.primitive = primitive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TypeEntity entity = (TypeEntity) o;

        return typeClass.equals(entity.typeClass);

    }

    @Override
    public int hashCode() {
        return typeClass.hashCode();
    }

    @Override
    public String toString() {
        return this.typeName;
    }
}
