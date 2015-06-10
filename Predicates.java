/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * This program is proprietary software; all information contained herein is, and
 * remains, the property of Gridtec and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Gridtec and its suppliers
 * and may be covered by Austrian and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained from Gridtec company.
 *
 * This software consists of voluntary contributions made by individuals on behalf
 * of Gridtec. For more information on Gridtec, please refer to www.gridtec.at homepage.
 */
package at.gridtec.internals.lang.function;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * A utility class of predefined {@link Predicate}s used in conjunction with Stream API.
 *
 * @see java.util.function.Predicate
 * @since 1.0.0
 */
public final class Predicates {
    /**
     * Private constructor to prevent instantiation.
     */
    private Predicates() {

    }

    /**
     * Returns a {@link Predicate} which is always {@code true}.
     *
     * @param <T> The type of argument for the function
     * @return A {@code Predicate} representing the conversion.
     */
    public static <T> Predicate<T> alwaysTrue() {
        return p -> true;
    }

    /**
     * Returns a {@link Predicate} which is always {@code false}.
     *
     * @param <T> The type of argument for the function
     * @return A {@code Predicate} representing the conversion.
     */
    public static <T> Predicate<T> alwaysFalse() {
        return p -> false;
    }

    /**
     * Returns a {@link Predicate} which checks its argument for being {@code null}. It evaluates to {@code true}
     * if so, {@code false} otherwise.
     *
     * @param <T> The type of argument for the function
     * @return A {@code Predicate} representing the conversion.
     * @see #notNull()
     */
    public static <T> Predicate<T> isNull() {
        return p -> p == null;
    }

    /**
     * Returns a {@link Predicate} which checks its argument for not being {@code null}. It evaluates to {@code true}
     * if so, {@code false} otherwise.
     *
     * @param <T> The type of argument for the function
     * @return A {@code Predicate} representing the conversion.
     * @see #isNull()
     */
    public static <T> Predicate<T> notNull() {
        return p -> p != null;
    }

    /**
     * Returns a {@link Predicate} of {@link Class} which is assignable from its argument. This is done by using {@link
     * Class#isAssignableFrom} method. The {@code Predicate} evaluates to {@code true}, if the given class is either
     * the same as, or is a superclass or superinterface of, the class or interface represented by the specified {@code
     * Class} argument. If the given {@code Class} represents a primitive type, this {@code Predicate} evaluates also
     * to {@code true}. In any other case, it evaluates to {@code false}.
     *
     * @return A {@code Predicate} representing the conversion.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #isNull()
     */
    public static Predicate<Class<?>> assignableFrom(Class<?> clazz) {
        Objects.requireNonNull(clazz);
        return clazz::isAssignableFrom;
    }

    /**
     * Returns a {@link Predicate} of {@link String} which evaluates to {@code true}, if the {@code String} is of given
     * length. In any other case, it evaluates to {@code false}.
     *
     * @param length The length a string should be checked for
     * @return A {@code Predicate} representing the conversion.
     * @throws IllegalArgumentException If given argument is &lt; 0
     */
    public static Predicate<String> isStringOfLength(int length) throws IllegalArgumentException {
        if (length < 0) {
            throw new IllegalArgumentException("Given length must not be less than 0");
        }
        return p -> p.length() == length;
    }

    /**
     * Returns a {@link Predicate} checking if a {@link File} is equal to a {@link Path}. If so, it evaluates to {@code
     * true}, {@code false} otherwise.
     *
     * @param file The {@code File} to be checked if equal to a {@code Path}
     * @return A {@code Predicate} representing the conversion.
     * @throws NullPointerException If given argument was {@code null}.
     */
    public static Predicate<Path> isPathEqualFile(File file) {
        Objects.requireNonNull(file);
        return p -> p.getFileName().toString().equals(file.getName());
    }

    /**
     * Returns a {@link Predicate} checking if a {@link Path} is equal to a {@link File}. If so, it evaluates to {@code
     * true}, {@code false} otherwise.
     *
     * @param path The {@code Path} to be checked if equal to a {@code File}
     * @return A predicate representing the conversion.
     * @throws NullPointerException If given argument was {@code null}.
     */
    public static Predicate<File> isFileEqualPath(Path path) {
        Objects.requireNonNull(path);
        return p -> p.getName().equals(path.getFileName().toString());
    }
}
