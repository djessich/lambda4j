/*
 * Copyright (c) 2016 Gridtec. All rights reserved.
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
package at.gridtec.lambda4j.generator.util;

import at.gridtec.lambda4j.generator.LambdaTypeEnum;
import at.gridtec.lambda4j.generator.cache.LambdaCache;
import at.gridtec.lambda4j.generator.entities.LambdaEntity;
import at.gridtec.lambda4j.generator.entities.TypeEntity;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.SerializationUtils;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

/**
 * LambdaEntity descriptor utils class.
 */
@SuppressWarnings("unused")
public final class LambdaUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private LambdaUtils() {

    }

    /**
     * Returns a deep copy of the given lambda.
     *
     * @param lambda The lambda to be copied
     * @return A deep copy of the given lambda.
     * @throws NullPointerException If given lambda is {@code null}
     */
    public static LambdaEntity copy(@Nonnull final LambdaEntity lambda) {
        Objects.requireNonNull(lambda);
        return SerializationUtils.clone(lambda);
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda type, lambda arity and throwable flag. If the lambda
     * exists, it will be returned as is, otherwise {@code null} is returned.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda type, lambda arity and throwable flag, or {@code null} if no such lambda
     * exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity search(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity, boolean isThrowable,
            boolean preferFromJdk) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda arity, lambda return type and throwable flag. If the
     * lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the return type may be
     * {@code null}.
     *
     * @param arity The lambdas arity
     * @param returnType The lambdas return type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda arity, lambda return type and throwable flag, or {@code null} if no such
     * lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchByReturnType(@Nonnegative int arity, @Nullable final TypeEntity returnType,
            boolean isThrowable, boolean preferFromJdk) {
        // Check arguments
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda type, lambda arity, lambda return type and throwable flag.
     * If the lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the return type may
     * be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param returnType The lambdas return type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda type, lambda arity, lambda return type and throwable flag, or {@code null}
     * if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchByReturnType(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity,
            @Nullable final TypeEntity returnType, boolean isThrowable, boolean preferFromJdk) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            // Find lambda and return it if such or null
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda arity, lambda first input type and throwable flag. If the
     * lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the first input type may be
     * {@code null}.
     *
     * @param arity The lambdas arity
     * @param firstInputType The lambdas first input type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda arity, lambda first input type and throwable flag, or {@code null} if no
     * such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchByFirstInputType(@Nonnegative int arity, @Nullable final TypeEntity firstInputType,
            boolean isThrowable, boolean preferFromJdk) {
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda type, lambda arity, lambda first input type and throwable
     * flag. If the lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the first
     * input type may be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param firstInputType The lambdas first input type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda type, lambda arity, lambda first input type and throwable flag, or {@code
     * null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchByFirstInputType(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity,
            @Nullable final TypeEntity firstInputType, boolean isThrowable, boolean preferFromJdk) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda arity, lambda first input type, lambda return type and
     * throwable flag. If the lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the
     * first input or return type may be {@code null}.
     *
     * @param arity The lambdas arity
     * @param firstInputType The lambdas first input type
     * @param returnType The lambdas return type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda arity, lambda first input type, lambda return type and throwable flag, or
     * {@code null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchByFirstInputAndReturnType(@Nonnegative int arity,
            @Nullable final TypeEntity firstInputType, @Nullable final TypeEntity returnType, boolean isThrowable,
            boolean preferFromJdk) {
        // Check arguments
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda type, lambda arity, lambda first input type, lambda return
     * type and throwable flag. If the lambda exists, it will be returned as is, otherwise {@code null} is returned.
     * Thereby the first input or return type may be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param firstInputType The lambdas first input type
     * @param returnType The lambdas return type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda type, lambda arity, lambda first input type, lambda return type and
     * throwable flag, or {@code null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchByFirstInputAndReturnType(@Nonnull final LambdaTypeEnum type,
            @Nonnegative int arity, @Nullable final TypeEntity firstInputType, @Nullable final TypeEntity returnType,
            boolean isThrowable, boolean preferFromJdk) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda arity, lambda second input type and throwable flag. If the
     * lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the second input type may
     * be {@code null}.
     *
     * @param arity The lambdas arity
     * @param secondInputType The lambdas second input type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda arity, lambda second input type and throwable flag, or {@code null} if no
     * such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchBySecondInputType(@Nonnegative int arity,
            @Nullable final TypeEntity secondInputType, boolean isThrowable, boolean preferFromJdk) {
        // Check arguments
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda type, lambda arity, lambda second input type and throwable
     * flag. If the lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the second
     * input type may be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param secondInputType The lambdas second input type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda type, lambda arity, lambda second input type and throwable flag, or {@code
     * null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchBySecondInputType(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity,
            @Nullable final TypeEntity secondInputType, boolean isThrowable, boolean preferFromJdk) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda arity, lambda second input type, lambda return type and
     * throwable flag. If the lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the
     * second input or return type may be {@code null}.
     *
     * @param arity The lambdas arity
     * @param secondInputType The lambdas second input type
     * @param returnType The lambdas return type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda arity, lambda second input type, lambda return type and throwable flag, or
     * {@code null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchBySecondInputAndReturnType(@Nonnegative int arity,
            @Nullable final TypeEntity secondInputType, @Nullable final TypeEntity returnType, boolean isThrowable,
            boolean preferFromJdk) {
        // Check arguments
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda type, lambda arity, lambda second input type, lambda
     * return type and throwable flag. If the lambda exists, it will be returned as is, otherwise {@code null} is
     * returned. Thereby the second input or return type may be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param secondInputType The lambdas second input type
     * @param returnType The lambdas return type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda type, lambda arity, lambda second input type, lambda return type and
     * throwable flag, or {@code null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchBySecondInputAndReturnType(@Nonnull final LambdaTypeEnum type,
            @Nonnegative int arity, @Nullable final TypeEntity secondInputType, @Nullable final TypeEntity returnType,
            boolean isThrowable, boolean preferFromJdk) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda arity, lambda third input type and throwable flag. If the
     * lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the third input type may be
     * {@code null}.
     *
     * @param arity The lambdas arity
     * @param thirdInputType The lambdas third input type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda arity, lambda third  input type and throwable flag, or {@code null} if no
     * such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchByThirdInputType(@Nonnegative int arity, @Nullable final TypeEntity thirdInputType,
            boolean isThrowable, boolean preferFromJdk) {
        // Check arguments
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda type, lambda arity, lambda third input type and throwable
     * flag. If the lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the third
     * input type may be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param thirdInputType The lambdas third input type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda type, lambda arity, lambda third  input type and throwable flag, or {@code
     * null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchByThirdInputType(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity,
            @Nullable final TypeEntity thirdInputType, boolean isThrowable, boolean preferFromJdk) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda arity, lambda third input type, lambda return type and
     * throwable flag. If the lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the
     * third input or return type may be {@code null}.
     *
     * @param arity The lambdas arity
     * @param thirdInputType The lambdas third input type
     * @param returnType The lambdas return type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda arity, lambda third  input type, lambda return type and throwable flag, or
     * {@code null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchByThirdInputAndReturnType(@Nonnegative int arity,
            @Nullable final TypeEntity thirdInputType, @Nullable final TypeEntity returnType, boolean isThrowable,
            boolean preferFromJdk) {
        // Check arguments
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda type, lambda arity, lambda third input type, lambda return
     * type and throwable flag. If the lambda exists, it will be returned as is, otherwise {@code null} is returned.
     * Thereby the third input or return type may be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param thirdInputType The lambdas third input type
     * @param returnType The lambdas return type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda type, lambda arity, lambda third  input type, lambda return type and
     * throwable flag, or {@code null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchByThirdInputAndReturnType(@Nonnull final LambdaTypeEnum type,
            @Nonnegative int arity, @Nullable final TypeEntity thirdInputType, @Nullable final TypeEntity returnType,
            boolean isThrowable, boolean preferFromJdk) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given input types and throwable flag. If the lambda exists, it will be
     * returned as is, otherwise {@code null} is returned. Thereby the input types may be {@code null}.
     *
     * @param arity The lambdas arity
     * @param firstInputType The lambdas first input type
     * @param secondInputType The lambdas second input type
     * @param thirdInputType The lambdas third input type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given input types and throwable flag, or {@code null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchByInputTypes(@Nonnegative int arity, @Nullable final TypeEntity firstInputType,
            @Nullable final TypeEntity secondInputType, @Nullable final TypeEntity thirdInputType, boolean isThrowable,
            boolean preferFromJdk) {
        // Check arguments
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda type, input types and throwable flag. If the lambda
     * exists, it will be returned as is, otherwise {@code null} is returned. Thereby the input types may be {@code
     * null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param firstInputType The lambdas first input type
     * @param secondInputType The lambdas second input type
     * @param thirdInputType The lambdas third input type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda type, input types and throwable flag, or {@code null} if no such lambda
     * exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchByInputTypes(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity,
            @Nullable final TypeEntity firstInputType, @Nullable final TypeEntity secondInputType,
            @Nullable final TypeEntity thirdInputType, boolean isThrowable, boolean preferFromJdk) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given input types, return type and throwable flag. If the lambda
     * exists, it will be returned as is, otherwise {@code null} is returned. Thereby the input types or return type may
     * be {@code null}.
     *
     * @param arity The lambdas arity
     * @param firstInputType The lambdas first input type
     * @param secondInputType The lambdas second input type
     * @param thirdInputType The lambdas third input type
     * @param returnType The lambdas return type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given input types and throwable flag, or {@code null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchByInputTypesAndReturnType(@Nonnegative int arity,
            @Nullable final TypeEntity firstInputType, @Nullable final TypeEntity secondInputType,
            @Nullable final TypeEntity thirdInputType, @Nullable TypeEntity returnType, boolean isThrowable,
            boolean preferFromJdk) {
        // Check arguments
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Searches for a {@link LambdaEntity} using given lambda type, input types, return type and throwable flag. If the
     * lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the input types or return
     * type may be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param firstInputType The lambdas first input type
     * @param secondInputType The lambdas second input type
     * @param thirdInputType The lambdas third input type
     * @param returnType The lambdas return type
     * @param isThrowable The flag indicating if lambda is throwable
     * @param preferFromJdk A flag if we also search for {@code JDK} lambdas
     * @return The lambda from given lambda type, input types and throwable flag, or {@code null} if no such lambda
     * exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     * @implNote This implementation search for the lambda, which match the given arguments. If the {@code
     * #preferFromJdk} flag is set to {@code true}, then {@code JDK} lambdas are preferred over those from this library.
     * In either case, if no appropriate match could be found, {@code null} is returned.
     */
    public static LambdaEntity searchByInputTypesAndReturnType(@Nonnull final LambdaTypeEnum type,
            @Nonnegative int arity, @Nullable final TypeEntity firstInputType,
            @Nullable final TypeEntity secondInputType, @Nullable final TypeEntity thirdInputType,
            @Nullable TypeEntity returnType, boolean isThrowable, boolean preferFromJdk) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Default variable declaration
        Optional<LambdaEntity> optionalLambda = Optional.empty();

        // If jdk flag set, then find jdk lambda if such
        if (preferFromJdk) {
            optionalLambda = LambdaCache.getInstance()
                    .getJdkLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst();
        }

        // If jdk lambda not present find other non-jdk one and return if found or return null; if present return jdk lambda
        if (!optionalLambda.isPresent()) {
            return LambdaCache.getInstance()
                    .getLambdas()
                    .stream()
                    .filter(l -> l.getType().equals(type))
                    .filter(l -> l.getArity() == arity)
                    .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                    .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                    .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                    .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                    .filter(l -> l.isThrowable() == isThrowable)
                    .findFirst()
                    .orElse(null);
        } else {
            return optionalLambda.get();
        }
    }

    /**
     * Returns the {@link LambdaTypeEnum#COMPARATOR} lambda type.
     *
     * @return The {@code LambdaTypeEnum#COMPARATOR} lambda type.
     */
    public static LambdaTypeEnum getComparatorType() {
        return LambdaTypeEnum.COMPARATOR;
    }

    /**
     * Returns the {@link LambdaTypeEnum#CONSUMER} lambda type.
     *
     * @return The {@code LambdaTypeEnum#CONSUMER} lambda type.
     */
    public static LambdaTypeEnum getConsumerType() {
        return LambdaTypeEnum.CONSUMER;
    }

    /**
     * Returns the {@link LambdaTypeEnum#FUNCTION} lambda type.
     *
     * @return The {@code LambdaTypeEnum#FUNCTION} lambda type.
     */
    public static LambdaTypeEnum getFunctionType() {
        return LambdaTypeEnum.FUNCTION;
    }

    /**
     * Returns the {@link LambdaTypeEnum#OPERATOR} lambda type.
     *
     * @return The {@code LambdaTypeEnum#OPERATOR} lambda type.
     */
    public static LambdaTypeEnum getOperatorType() {
        return LambdaTypeEnum.OPERATOR;
    }

    /**
     * Returns the {@link LambdaTypeEnum#PREDICATE} lambda type.
     *
     * @return The {@code LambdaTypeEnum#PREDICATE} lambda type.
     */
    public static LambdaTypeEnum getPredicateType() {
        return LambdaTypeEnum.PREDICATE;
    }

    /**
     * Returns the {@link LambdaTypeEnum#RUNNABLE} lambda type.
     *
     * @return The {@code LambdaTypeEnum#RUNNABLE} lambda type.
     */
    public static LambdaTypeEnum getRunnableType() {
        return LambdaTypeEnum.RUNNABLE;
    }

    /**
     * Returns the {@link LambdaTypeEnum#SUPPLIER} lambda type.
     *
     * @return The {@code LambdaTypeEnum#SUPPLIER} lambda type.
     */
    public static LambdaTypeEnum getSupplierType() {
        return LambdaTypeEnum.SUPPLIER;
    }

    /**
     * Checks if the given {@link LambdaEntity} is of the given type.
     *
     * @param lambda The {@code LambdaEntity} to be checked
     * @param type The type to check if given lambda is of
     * @return {@code true} if given lambda is of given type, {@code false} otherwise.
     * @throws NullPointerException If given {@code LambdaEntity} is {@code null}
     */
    public static boolean isOfType(@Nonnull final LambdaEntity lambda, @Nullable final LambdaTypeEnum type) {
        Objects.requireNonNull(lambda);
        return lambda.getType().equals(type);
    }

    /**
     * Checks if the given {@link LambdaEntity} is of type {@link LambdaTypeEnum#COMPARATOR}.
     *
     * @param lambda The {@code LambdaEntity} to be checked
     * @return {@code true} if given lambda is of type {@code LambdaTypeEnum#COMPARATOR}, {@code false} otherwise.
     * @throws NullPointerException If given argument is {@code null}
     * @see #isOfType(LambdaEntity, LambdaTypeEnum)
     */
    public static boolean isOfTypeComparator(@Nonnull final LambdaEntity lambda) {
        Objects.requireNonNull(lambda);
        return isOfType(lambda, LambdaTypeEnum.COMPARATOR);
    }

    /**
     * Checks if the given {@link LambdaEntity} is of type {@link LambdaTypeEnum#CONSUMER}.
     *
     * @param lambda The {@code LambdaEntity} to be checked
     * @return {@code true} if given lambda is of type {@code LambdaTypeEnum#CONSUMER}, {@code false} otherwise.
     * @throws NullPointerException If given argument is {@code null}
     * @see #isOfType(LambdaEntity, LambdaTypeEnum)
     */
    public static boolean isOfTypeConsumer(@Nonnull final LambdaEntity lambda) {
        Objects.requireNonNull(lambda);
        return isOfType(lambda, LambdaTypeEnum.CONSUMER);
    }

    /**
     * Checks if the given {@link LambdaEntity} is of type {@link LambdaTypeEnum#FUNCTION}.
     *
     * @param lambda The {@code LambdaEntity} to be checked
     * @return {@code true} if given lambda is of type {@code LambdaTypeEnum#FUNCTION}, {@code false} otherwise.
     * @throws NullPointerException If given argument is {@code null}
     * @see #isOfType(LambdaEntity, LambdaTypeEnum)
     */
    public static boolean isOfTypeFunction(@Nonnull final LambdaEntity lambda) {
        Objects.requireNonNull(lambda);
        return isOfType(lambda, LambdaTypeEnum.FUNCTION);
    }

    /**
     * Checks if the given {@link LambdaEntity} is of type {@link LambdaTypeEnum#OPERATOR}.
     *
     * @param lambda The {@code LambdaEntity} to be checked
     * @return {@code true} if given lambda is of type {@link LambdaTypeEnum#OPERATOR}, {@code false} otherwise.
     * @throws NullPointerException If given argument is {@code null}
     * @see #isOfType(LambdaEntity, LambdaTypeEnum)
     */
    public static boolean isOfTypeOperator(@Nonnull final LambdaEntity lambda) {
        Objects.requireNonNull(lambda);
        return isOfType(lambda, LambdaTypeEnum.OPERATOR);
    }

    /**
     * Checks if the given {@link LambdaEntity} is of type {@link LambdaTypeEnum#PREDICATE}.
     *
     * @param lambda The {@code LambdaEntity} to be checked
     * @return {@code true} if given lambda is of type {@code LambdaTypeEnum#PREDICATE}, {@code false} otherwise.
     * @throws NullPointerException If given argument is {@code null}
     * @see #isOfType(LambdaEntity, LambdaTypeEnum)
     */
    public static boolean isOfTypePredicate(@Nonnull final LambdaEntity lambda) {
        Objects.requireNonNull(lambda);
        return isOfType(lambda, LambdaTypeEnum.PREDICATE);
    }

    /**
     * Checks if the given {@link LambdaEntity} is of type {@link LambdaTypeEnum#RUNNABLE}.
     *
     * @param lambda The {@code LambdaEntity} to be checked
     * @return {@code true} if given lambda is of type {@link LambdaTypeEnum#RUNNABLE}, {@code false} otherwise.
     * @throws NullPointerException If given argument is {@code null}
     * @see #isOfType(LambdaEntity, LambdaTypeEnum)
     */
    public static boolean isOfTypeRunnable(@Nonnull final LambdaEntity lambda) {
        Objects.requireNonNull(lambda);
        return isOfType(lambda, LambdaTypeEnum.RUNNABLE);
    }

    /**
     * Checks if the given {@link LambdaEntity} is of type {@link LambdaTypeEnum#SUPPLIER}.
     *
     * @param lambda The {@code LambdaEntity} to be checked
     * @return {@code true} if given lambda is of type {@code LambdaTypeEnum#SUPPLIER}, {@code false} otherwise.
     * @throws NullPointerException If given argument is {@code null}
     * @see #isOfType(LambdaEntity, LambdaTypeEnum)
     */
    public static boolean isOfTypeSupplier(@Nonnull final LambdaEntity lambda) {
        Objects.requireNonNull(lambda);
        return isOfType(lambda, LambdaTypeEnum.SUPPLIER);
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code Object} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code Object} type.
     */
    public static TypeEntity getNewTypeEntity() {
        return new TypeEntity();
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code Object} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code Object} type.
     */
    public static TypeEntity getObjectTypeEntity() {
        return new TypeEntity(Object.class, "Object", "");
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code boolean} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code boolean} type.
     */
    public static TypeEntity getBooleanTypeEntity() {
        return new TypeEntity(boolean.class, boolean.class.getSimpleName(), "value");
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code byte} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code byte} type.
     */
    public static TypeEntity getByteTypeEntity() {
        return new TypeEntity(byte.class, byte.class.getSimpleName(), "value");
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code char} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code char} type.
     */
    public static TypeEntity getCharTypeEntity() {
        return new TypeEntity(char.class, char.class.getSimpleName(), "value");
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code double} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code double} type.
     */
    public static TypeEntity getDoubleTypeEntity() {
        return new TypeEntity(double.class, double.class.getSimpleName(), "value");
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code float} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code float} type.
     */
    public static TypeEntity getFloatTypeEntity() {
        return new TypeEntity(float.class, float.class.getSimpleName(), "value");
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code int} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code int} type.
     */
    public static TypeEntity getIntTypeEntity() {
        return new TypeEntity(int.class, int.class.getSimpleName(), "value");
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code long} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code long} type.
     */
    public static TypeEntity getLongTypeEntity() {
        return new TypeEntity(long.class, long.class.getSimpleName(), "value");
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code short} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code short} type.
     */
    public static TypeEntity getShortTypeEntity() {
        return new TypeEntity(short.class, short.class.getSimpleName(), "value");
    }

    /**
     * Returns a {@link TypeEntity} referencing {@code void}.
     *
     * @return A a {@code TypeEntity} referencing {@code void}.
     */
    public static TypeEntity getVoidTypeEntity() {
        return new TypeEntity(void.class, void.class.getSimpleName(), "value");
    }

    /**
     * Checks if the given string represents a primitive type.
     *
     * @param type The type in string representation
     * @return {@code true} if, and only if, given string represents a primitive type, {@code false} otherwise.
     */
    public static boolean isPrimitiveType(@Nullable final TypeEntity type) {
        return type != null && ClassUtils.isPrimitiveOrWrapper(type.getTypeClass());
    }
}
