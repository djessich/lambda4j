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
import java.util.stream.Collectors;

/**
 * Represents a {@link Processor} which creates copies of the given lambda to set all possible lambda return types to
 * the lambda. These copies are handed over to next {@code Processor} to do further processing. The result from next
 * step is returned by this step.
 */
public final class ReturnTypeProcessor extends Processor {

    @Override
    protected boolean processable(@Nonnull final Lambda lambda) {
        return lambda.getType() != null;
    }

    @Override
    @Nonnull
    protected List<Lambda> process(@Nonnull final Lambda lambda) {
        final List<Lambda> lambdas = new LinkedList<>();

        // Special Rule: Lambda is a Comparator it must return int
        if (LambdaUtils.isOfTypeComparator(lambda)) {
            final Lambda copy = LambdaUtils.copy(lambda);
            copy.setReturnType(int.class.getName());
            lambdas.addAll(next(copy));
        }

        // Special Rule: Lambda is a Consumer it must return nothing (void)
        else if (LambdaUtils.isOfTypeConsumer(lambda)) {
            final Lambda copy = LambdaUtils.copy(lambda);
            copy.setReturnType(void.class.getName());
            lambdas.addAll(next(copy));
        }

        // Special Rule: Lambda is a Predicate it must return boolean
        else if (LambdaUtils.isOfTypePredicate(lambda)) {
            final Lambda copy = LambdaUtils.copy(lambda);
            copy.setReturnType(boolean.class.getSimpleName());
            lambdas.addAll(next(copy));
        }

        // Special Rule: Lambda is a Runnable it must return nothing (void) and does not allow input -> end call stack
        else if (LambdaUtils.isOfTypeRunnable(lambda)) {
            final Lambda copy = LambdaUtils.copy(lambda);
            copy.setReturnType(void.class.getName());
            lambdas.addAll(next(copy));
        }

        // All other types will return normally, so evaluate them
        else {
            // Loop over left primitives without boolean
            final List<String> primitivesWithoutBoolean = PRIMITIVES.stream()
                    .filter(str -> !str.equals(boolean.class.getSimpleName()))
                    .collect(Collectors.toList());
            for (String type : primitivesWithoutBoolean) {
                final Lambda primitive = LambdaUtils.copy(lambda);
                primitive.setReturnType(type);
                lambdas.addAll(next(primitive));
            }

            // Lambda returns generic; Special Rule: rename generic if lambda type is Supplier or a Operator
            final Lambda generical = LambdaUtils.copy(lambda);
            if (LambdaUtils.isOfTypeSupplier(lambda) && LambdaUtils.isOfTypeOperator(lambda)) {
                generical.setReturnType("T");
            } else {
                generical.setReturnType("R");
            }
            lambdas.addAll(next(generical));
        }

        return lambdas;
    }
}
