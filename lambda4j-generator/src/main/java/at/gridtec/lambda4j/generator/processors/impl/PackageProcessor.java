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
package at.gridtec.lambda4j.generator.processors.impl;

import at.gridtec.lambda4j.generator.Lambda;
import at.gridtec.lambda4j.generator.LambdaTypeEnum;
import at.gridtec.lambda4j.generator.processors.Processor;
import at.gridtec.lambda4j.generator.util.LambdaUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a {@link Processor} which creates copies of the given lambda to set the lambdas package name. These copies
 * are handed over to next {@code Processor} to do further processing. The result from next step is returned by this
 * step.
 * <p>
 * Requirements by this step is the lambdas <b>correct</b> type ({@link Lambda#getType()}) only.
 */
public class PackageProcessor extends Processor {

    /**
     * Defines the default package prefix, representing the projects group id.
     */
    private static final String PACKAGE_DEFAULT_PREFIX = "at.gridtec.lambda4j";

    /**
     * Defines the identifier for lambdas with arity 2.
     */
    private static final String PACKAGE_ARITY_TWO_IDENTIFIER = "bi";

    /**
     * Defines the identifier for lambdas with arity 3.
     */
    private static final String PACKAGE_ARITY_THREE_IDENTIFIER = "tri";

    /**
     * Defines the identifier for lambdas which get object as input parameters and returns primitive references.
     */
    private static final String PACKAGE_TO_IDENTIFIER = "to";

    /**
     * Defines the identifier for lambdas which get object and primitive references as input parameters.
     */
    private static final String PACKAGE_OBJ_IDENTIFIER = "obj";

    /**
     * Defines the identifier for lambdas which get primitive references as input parameters and returns primitive
     * parameters.
     */
    private static final String PACKAGE_CONVERSION_IDENTIFIER = "conversion";

    /**
     * Defines the identifier for {@link LambdaTypeEnum#OPERATOR}´with arity 1.
     */
    private static final String PACKAGE_OPERATOR_ARITY_ONE = "unary";

    /**
     * Defines the identifier for {@link LambdaTypeEnum#OPERATOR}´with arity 2.
     */
    private static final String PACKAGE_OPERATOR_ARITY_TWO = "binary";

    /**
     * Defines the identifier for {@link LambdaTypeEnum#OPERATOR}´with arity 3.
     */
    private static final String PACKAGE_OPERATOR_ARITY_THREE = "ternary";

    @Override
    protected boolean processable(@Nonnull Lambda lambda) {
        return lambda.getType() != null;
    }

    @Nonnull
    @Override
    protected List<Lambda> process(@Nonnull Lambda lambda) {
        final List<Lambda> lambdas = new LinkedList<>();
        final Lambda copy = LambdaUtils.copy(lambda);
        final PackageBuilder packageBuilder = new PackageBuilder(PACKAGE_DEFAULT_PREFIX);

        // Add lambda type simple name to type for lambda grouping (actual package ".function"
        packageBuilder.append(lambda.getType().getSimpleName());

        // Special Case: Lambda is of type operator, so append special package identifiers (actual package ".operator.binary"
        if (LambdaUtils.isOfTypeOperator(lambda)) {
            if (lambda.getArity() == 1) {
                packageBuilder.append(PACKAGE_OPERATOR_ARITY_ONE);
            } else if (lambda.getArity() == 2) {
                packageBuilder.append(PACKAGE_OPERATOR_ARITY_TWO);
            } else if (lambda.getArity() == 3) {
                packageBuilder.append(PACKAGE_OPERATOR_ARITY_THREE);
            }
        }

        // All other lambdas will go here and have normal scheme
        else {
            // Lambda arity is one, so add no identifier (actual package ".function"
            if (lambda.getArity() == 1) {
                // If lambda of type function and has primitive return type check if input is primitive
                if (LambdaUtils.isOfTypeFunction(lambda) && lambda.getReturnType().isTypePrimitive()) {
                    // If first lambda parameter is primitive, add "conversion" identifier ".function.conversion"
                    if (lambda.getFirstInputType().isTypePrimitive()) {
                        packageBuilder.append(PACKAGE_CONVERSION_IDENTIFIER);
                    }
                    // If first lambda parameter is an object, add "to" identifier ".function.to"
                    else {
                        packageBuilder.append(PACKAGE_TO_IDENTIFIER);
                    }
                }
            }

            // Lambda arity is two, so add correct arity two identifiers (actual package ".function.bi"
            else if (lambda.getArity() == 2) {
                packageBuilder.append(PACKAGE_ARITY_TWO_IDENTIFIER);

                // If lambda gets object and primitive type then append obj identifier ".function.bi.obj"
                if (lambda.getFirstInputType().equals(LambdaUtils.getObjectTypeEntity()) && lambda.getSecondInputType()
                        .isTypePrimitive()) {
                    packageBuilder.append(PACKAGE_OBJ_IDENTIFIER);
                }

                // If lambda of type function and has primitive return type check if input is primitive
                else if (LambdaUtils.isOfTypeFunction(lambda) && lambda.getReturnType().isTypePrimitive()) {
                    // If first and second lambda parameters are primitive, add "conversion" identifier ".function.bi.conversion"
                    if (lambda.getFirstInputType().isTypePrimitive() && lambda.getSecondInputType().isTypePrimitive()) {
                        packageBuilder.append(PACKAGE_CONVERSION_IDENTIFIER);
                    }
                    // If first and second lambda parameters are objects, add "to" identifier ".function.bi.to"
                    else {
                        packageBuilder.append(PACKAGE_TO_IDENTIFIER);
                    }
                }
            }

            // Lambda arity is three, so add correct arity three identifiers (actual package ".function.tri"
            else if (lambda.getArity() == 3) {
                packageBuilder.append(PACKAGE_ARITY_THREE_IDENTIFIER);

                // If lambda gets object, object and primitive type then append obj identifier ".function.tri.obj"
                if (lambda.getFirstInputType().equals(LambdaUtils.getObjectTypeEntity()) && lambda.getThirdInputType()
                        .isTypePrimitive()) {
                    packageBuilder.append(PACKAGE_OBJ_IDENTIFIER);

                }

                // If lambda of type function and has primitive return type check if input is primitive
                else if (LambdaUtils.isOfTypeFunction(lambda) && lambda.getReturnType().isTypePrimitive()) {
                    // If all lambda input parameters are primitive, add "conversion" identifier ".function.tri.conversion"
                    if (lambda.getFirstInputType().isTypePrimitive()
                            && lambda.getSecondInputType().isTypePrimitive()
                            && lambda.getThirdInputType().isTypePrimitive()) {
                        packageBuilder.append(PACKAGE_CONVERSION_IDENTIFIER);
                    }
                    // If all lambda input parameters are objects, add "to" identifier ".function.tri.to"
                    else {
                        packageBuilder.append(PACKAGE_TO_IDENTIFIER);
                    }
                }
            }
        }

        // Set generated package string, call next for further processing and return the result from it
        copy.setPackageName(packageBuilder.toString());
        return next(copy);
    }

    /**
     * Represents a class for building up a {@link Lambda}s package name. Internally a {@link StringBuilder} is used to
     * create the string representing the package name.
     */
    private final class PackageBuilder {

        /**
         * The internal {@link StringBuilder} for creating the package name.
         */
        private final StringBuilder packageBuilder;

        /**
         * Constructs this builder from internal package root.
         *
         * @param packageRoot The package root
         * @throws NullPointerException If given argument is {@code null}
         */
        public PackageBuilder(@Nonnull final String packageRoot) {
            Objects.requireNonNull(packageRoot);
            this.packageBuilder = new StringBuilder(packageRoot);
        }

        /**
         * Appends the package separator to this builder.
         *
         * @return A reference to this builder.
         */
        public PackageBuilder appendSeparator() {
            this.packageBuilder.append(".");
            return this;
        }

        /**
         * Appends the given {@link String} to this builder. When calling this method the package separator is appended
         * automatically.
         *
         * @param value The string to be appended to this builder.
         * @return A reference to this builder.
         * @see #appendSeparator()
         */
        @Nonnull
        public PackageBuilder append(@Nullable final String value) {
            appendSeparator();
            this.packageBuilder.append(value);
            return this;
        }

        /**
         * Builds this builder and returns the created package name as its result.
         *
         * @return A string representing the package name result.
         * @see #toString()
         */
        public String build() {
            return this.toString();
        }

        /**
         * Builds this builder and returns the created package name as its result.
         *
         * @return A string representing the package name result.
         * @see #build()
         */
        @Override
        public String toString() {
            return this.packageBuilder.toString();
        }
    }
}
