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
import at.gridtec.lambda4j.generator.entities.TypeEntity;
import at.gridtec.lambda4j.generator.processors.Processor;
import at.gridtec.lambda4j.generator.util.LambdaUtils;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a {@link Processor} which creates copies of the given lambda to set all possible lambda input types for
 * the {@code first} argument to the lambda. These copies are handed over to next {@code Processor} to do further
 * processing. The result from next step is returned by this step.
 * <p>
 * Requirements by this step is the lambdas type ({@link Lambda#getType()}) only.
 */
public final class InputTypeOneProcessor extends Processor {

    @Override
    protected boolean processable(@Nonnull final Lambda lambda) {
        return lambda.getType() != null;
    }

    @Override
    @Nonnull
    protected List<Lambda> process(@Nonnull final Lambda lambda) {
        final List<Lambda> lambdas = new LinkedList<>();

        // Check if it has arity 1; otherwise end call stack
        if (lambda.getArity() >= 1) {

            // Special Rule: Comparator is only generic
            if (LambdaUtils.isOfTypeComparator(lambda)) {
                final Lambda copy = LambdaUtils.copy(lambda);
                TypeEntity type = new TypeEntity(Object.class, "T", "t", 1);
                copy.setFirstInputType(type);
                lambdas.addAll(next(copy));
            }

            // All other lambdas have normal input 1
            else {
                // Create new list to generate variants for the given lambda
                final List<Lambda> genLambdas = new LinkedList<>();

                // Apply generical input for arg 1
                final Lambda generical = LambdaUtils.copy(lambda);
                TypeEntity type = new TypeEntity(Object.class, "T", "t", 1);
                generical.setFirstInputType(type);
                genLambdas.add(generical);

                // Apply primitive input for arg 1
                for (final Class<?> typeClass : PRIMITIVES) {
                    final Lambda primitive = LambdaUtils.copy(lambda);
                    type = new TypeEntity(typeClass, typeClass.getSimpleName(), "value", 1);
                    primitive.setFirstInputType(type);
                    genLambdas.add(primitive);
                }

                // For each generated given lambda variant
                for (Lambda genLambda : genLambdas) {
                    lambdas.addAll(next(genLambda));
                }
            }
        } else {
            final Lambda copy = LambdaUtils.copy(lambda);
            lambdas.addAll(next(copy));
        }

        return lambdas;
    }
}
