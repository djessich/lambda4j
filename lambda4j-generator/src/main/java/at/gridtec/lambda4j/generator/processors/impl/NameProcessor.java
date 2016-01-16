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
package at.gridtec.lambda4j.generator.processors.impl;

import at.gridtec.lambda4j.generator.Lambda;
import at.gridtec.lambda4j.generator.LambdaTypeEnum;
import at.gridtec.lambda4j.generator.processors.Processor;
import at.gridtec.lambda4j.generator.util.LambdaUtils;

import org.apache.commons.lang.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by domin on 24.12.2015.
 */
public final class NameProcessor extends Processor {

    /**
     * The identifier which names throwable lambdas.
     */
    private static final String THROWABLE_IDENTIFIER = "Throwable";

    /**
     * The identifier which names all lambdas with arity 2.
     */
    private static final String ARITY_TWO_IDENTIFIER = "Bi";

    /**
     * The identifier which names all lambdas with arity 3.
     */
    private static final String ARITY_THREE_IDENTIFIER = "Tri";

    /**
     * The identifier which names all operators with arity 1.
     */
    private static final String OPERATOR_ARITY_ONE_IDENTIFIER = "Unary";

    /**
     * The identifier which names all operators with arity 2.
     */
    private static final String OPERATOR_ARITY_TWO_IDENTIFIER = "Binary";

    /**
     * The identifier which names all operators with arity 3.
     */
    private static final String OPERATOR_ARITY_THREE_IDENTIFIER = "Ternary";

    /**
     * The identifier which names all lambdas having return type of primitive type.
     */
    private static final String TO_IDENTIFIER = "To";

    /**
     * The identifier for all lambdas which are primitive but get generic input also.
     */
    private static final String OBJ_IDENTIFIER = "Obj";

    @Override
    protected boolean processable(@Nonnull final Lambda lambda) {
        boolean processable = lambda.getType() != null && lambda.getReturnType() != null;
        if (lambda.getArity() >= 1) {
            processable = processable && lambda.getInputOneType() != null;
        }
        if (lambda.getArity() >= 2) {
            processable = processable && lambda.getInputTwoType() != null;
        }
        if (lambda.getArity() >= 3) {
            processable = processable && lambda.getInputThreeType() != null;
        }
        return processable;
    }

    @Override
    @Nonnull
    protected List<Lambda> process(@Nonnull final Lambda lambda) {
        final List<Lambda> lambdas = new LinkedList<Lambda>();
        final Lambda copy = LambdaUtils.copy(lambda);
        StringBuilder nameBuilder = new StringBuilder();

        // Lambdas which are throwable will have throwable identifier
        if (lambda.isThrowable()) {
            nameBuilder.append(THROWABLE_IDENTIFIER);
        }

        // Special Rule: Operators will have special arity identifiers
        if (LambdaUtils.isOfType(lambda, LambdaTypeEnum.OPERATOR)) {
            if (LambdaUtils.isPrimitiveType(lambda.getReturnType())) {
                nameBuilder.append(StringUtils.capitalize(lambda.getReturnType()));
            }
            if (lambda.getArity() == 1) {
                nameBuilder.append(OPERATOR_ARITY_ONE_IDENTIFIER);
            } else if (lambda.getArity() == 2) {
                nameBuilder.append(OPERATOR_ARITY_TWO_IDENTIFIER);
            } else if (lambda.getArity() == 3) {
                nameBuilder.append(OPERATOR_ARITY_THREE_IDENTIFIER);
            }
        }

        // Special Rule: Lambda is of type supplier and has primitive return, append primitive identifier
        else if (LambdaUtils.isOfTypeSupplier(lambda) && LambdaUtils.isPrimitiveType(lambda.getReturnType())) {
            nameBuilder.append(StringUtils.capitalize(lambda.getReturnType()));
        }

        // All other types will be named using normal schema, unless Comparator or Runnable
        else if (!LambdaUtils.isOfTypeComparator(lambda) && !LambdaUtils.isOfTypeRunnable(lambda)) {

            // Lambda with arity 1
            if (lambda.getArity() == 1) {

                // Special Rule: Lambda is of type function and input one type is not primitive, but return type is it,
                // then append 'To' identifier at start (needed as some lambda require arity after 'To' identifier)
                notToIdentifier(nameBuilder, lambda, lambda.getInputOneType());

                // Lambda input one is primitive, so append primitive type name
                if (LambdaUtils.isPrimitiveType(lambda.getInputOneType())) {
                    nameBuilder.append(StringUtils.capitalize(lambda.getInputOneType()));
                }

                // Special Rule: Lambda is of type function which has primitive input one and return type, so append 'To'
                // identifier at end (normal for lambda with primitive return)
                toIdentifier(nameBuilder, lambda, lambda.getInputOneType());
            }

            // Lambda with arity 2
            else if (lambda.getArity() == 2) {

                // Special Rule: Lambda is of type function and input two type is not primitive, but return type is it,
                // then append 'To' identifier at start (needed as some lambda require arity after 'To' identifier)
                notToIdentifier(nameBuilder, lambda, lambda.getInputTwoType());

                // Lambda input one type is generic and input two type is primitive, so append 'Obj' identifier;
                // otherwise just append normal 'Bi' identifier
                if (!LambdaUtils.isPrimitiveType(lambda.getInputOneType()) && LambdaUtils.isPrimitiveType(
                        lambda.getInputTwoType())) {
                    objIdentifier(nameBuilder, null, null);
                } else {
                    nameBuilder.append(ARITY_TWO_IDENTIFIER);
                }

                // Lambda input two is primitive, so append primitive type name
                if (LambdaUtils.isPrimitiveType(lambda.getInputTwoType())) {
                    nameBuilder.append(StringUtils.capitalize(lambda.getInputTwoType()));
                }

                // Special Rule: Lambda is of type function which has primitive input two and return type, so append 'To'
                // identifier at end (normal for lambda with primitive return)
                toIdentifier(nameBuilder, lambda, lambda.getInputTwoType());
            }

            // Lambda with arity 3
            else if (lambda.getArity() == 3) {

                // Special Rule: Lambda is of type function and input three type is not primitive, but return type is it,
                // then append 'To' identifier at start (needed as some lambda require arity after 'To' identifier)
                notToIdentifier(nameBuilder, lambda, lambda.getInputThreeType());

                // Lambda input one and two types are generic and input three type is primitive, so append 'BiObj' identifier
                if (!LambdaUtils.isPrimitiveType(lambda.getInputOneType()) && !LambdaUtils.isPrimitiveType(
                        lambda.getInputTwoType()) && LambdaUtils.isPrimitiveType(lambda.getInputThreeType())) {
                    objIdentifier(nameBuilder, ARITY_TWO_IDENTIFIER, null);
                }
                // Lambda input one is generic and input two and three types are primitive, so append 'ObjBi' identifier
                else if (!LambdaUtils.isPrimitiveType(lambda.getInputOneType()) && LambdaUtils.isPrimitiveType(
                        lambda.getInputTwoType()) && LambdaUtils.isPrimitiveType(lambda.getInputThreeType())) {
                    objIdentifier(nameBuilder, null, ARITY_TWO_IDENTIFIER);
                } else {
                    nameBuilder.append(ARITY_THREE_IDENTIFIER);
                }

                // Lambda input three is primitive, so append primitive type name
                if (LambdaUtils.isPrimitiveType(lambda.getInputThreeType())) {
                    nameBuilder.append(StringUtils.capitalize(lambda.getInputThreeType()));
                }

                // Special Rule: Lambda is of type function which has primitive input three and return type, so append 'To'
                // identifier at end (normal for lambda with primitive return)
                toIdentifier(nameBuilder, lambda, lambda.getInputThreeType());
            }
        }

        // Append lambda type simple name in name
        nameBuilder.append(StringUtils.capitalize(lambda.getType().getSimpleName()));

        // Apply builder name to name field in lambda copy
        copy.setName(nameBuilder.toString());
        lambdas.addAll(next(copy));
        return lambdas;
    }

    /**
     * A helper method to append the {@link #TO_IDENTIFIER} to given {@link StringBuilder}. This happens only, if the
     * given lambda represents a function and the given {@code inputType} <b>is</b> primitive. This method uses the
     * operation {@code lambdaType && isPrimitive(lambdaInputType) && isPrimitive(lambdaReturnType)}.
     *
     * @param builder The {@code StringBuilder} to which to append the identifier and the primitive lambda return type
     * @param lambda The lambda to be used for checking
     * @param lambdaInputType The input type to be checked for primitiveness
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implSpec The implementation requires the input and return types to be primitive, and only if this occasion is
     * met, the identifier will be appended.
     * @see #notToIdentifier(StringBuilder, Lambda, String)
     */
    private void toIdentifier(@Nonnull final StringBuilder builder, @Nonnull final Lambda lambda,
            @Nonnull final String lambdaInputType) {
        Objects.requireNonNull(builder);
        Objects.requireNonNull(lambda);
        Objects.requireNonNull(lambdaInputType);
        final String lambdaReturnType = lambda.getReturnType();
        if (LambdaUtils.isOfTypeFunction(lambda)
                && LambdaUtils.isPrimitiveType(lambdaInputType)
                && LambdaUtils.isPrimitiveType(lambdaReturnType)) {
            builder.append(TO_IDENTIFIER);
            builder.append(StringUtils.capitalize(lambdaReturnType));
        }
    }

    /**
     * A helper method to append the {@link #TO_IDENTIFIER} to given {@link StringBuilder}. This happens only, if the
     * given lambda represents a function and the given {@code inputType} is <b>not</b> primitive. This method uses the
     * operation {@code lambdaType && !isPrimitive(lambdaInputType) && isPrimitive(lambdaReturnType)}.
     *
     * @param builder The {@code StringBuilder} to which to append the identifier and the primitive lambda return type
     * @param lambda The lambda to be used for checking
     * @param lambdaInputType The input type to be checked for primitiveness
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implSpec The implementation requires the input type to be <b>not</b> primitive and return type to be primitive.
     * Only if this occasion is met, the identifier will be appended.
     * @see #toIdentifier(StringBuilder, Lambda, String)
     */
    private void notToIdentifier(@Nonnull final StringBuilder builder, @Nonnull final Lambda lambda,
            @Nonnull final String lambdaInputType) {
        Objects.requireNonNull(builder);
        Objects.requireNonNull(lambda);
        Objects.requireNonNull(lambdaInputType);
        final String lambdaReturnType = lambda.getReturnType();
        if (LambdaUtils.isOfTypeFunction(lambda)
                && !LambdaUtils.isPrimitiveType(lambdaInputType)
                && LambdaUtils.isPrimitiveType(lambdaReturnType)) {
            builder.append(TO_IDENTIFIER);
            builder.append(StringUtils.capitalize(lambdaReturnType));
        }
    }

    /**
     * A helper method to append identifiers before or after the {@link #OBJ_IDENTIFIER} to given {@link
     * StringBuilder}.
     *
     * @param builder The {@code StringBuilder} to which to append the given {@code appendices}
     * @param beforeObj The identifier to be added before {@link #OBJ_IDENTIFIER}
     * @param afterObj The identifier to be added after {@link #OBJ_IDENTIFIER}
     */
    private void objIdentifier(@Nonnull final StringBuilder builder, @Nullable final String beforeObj,
            @Nullable final String afterObj) {
        builder.append(beforeObj == null ? "" : beforeObj);
        builder.append(OBJ_IDENTIFIER);
        builder.append(afterObj == null ? "" : afterObj);
    }
}
