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
import at.gridtec.lambda4j.generator.processors.Processor;
import at.gridtec.lambda4j.generator.util.LambdaUtils;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a {@link Processor} which creates copies of the given lambda to set all possible lambda input types for
 * the {@code third} argument to the lambda. These copies are handed over to next {@code Processor} to do further
 * processing. The result from next step is returned by this step.
 */
public final class InputTypeTwoProcessor extends Processor {

    @Override
    protected boolean processable(@Nonnull final Lambda lambda) {
        boolean processable = lambda.getType() != null;
        if (lambda.getArity() >= 1) {
            processable = processable && lambda.getInputOneType() != null;
        }
        return processable;
    }

    @Override
    @Nonnull
    protected List<Lambda> process(@Nonnull final Lambda lambda) {
        final List<Lambda> lambdas = new LinkedList<>();

        // Check if it has arity 2; otherwise end call stack
        if (lambda.getArity() >= 2) {

            // Special Rule: Comparator is only generic
            if (LambdaUtils.isOfTypeComparator(lambda)) {
                final Lambda copy = LambdaUtils.copy(lambda);
                copy.setInputTwoType("U");
                lambdas.addAll(next(copy));
            }

            // All other lambdas have normal input 2
            else {

                // Create new list to generate variants for the given lambda
                final List<Lambda> genLambdas = new LinkedList<>();

                // Lambda has generical arg 1 so apply further generical and primitive input for arg 2
                if (!PRIMITIVES.contains(lambda.getInputOneType())) {

                    // Apply generical input for arg 2
                    final Lambda generical = LambdaUtils.copy(lambda);
                    generical.setInputTwoType("U");
                    genLambdas.add(generical);

                    // Apply primitive input for arg 2
                    for (String type : PRIMITIVES) {
                        final Lambda primitive = LambdaUtils.copy(lambda);
                        primitive.setInputTwoType(type);
                        genLambdas.add(primitive);
                    }
                }

                // Lambda has primitive arg 1 so apply same primitive type to arg 2
                else {
                    final Lambda primitive = LambdaUtils.copy(lambda);
                    primitive.setInputTwoType(primitive.getInputOneType());
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
