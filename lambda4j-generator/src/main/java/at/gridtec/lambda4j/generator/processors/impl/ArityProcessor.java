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
package org.lambda4j.generator.processors.impl;

import org.lambda4j.generator.entities.LambdaEntity;
import org.lambda4j.generator.processors.Processor;
import org.lambda4j.generator.util.LambdaUtils;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a {@link Processor} which creates copies of the given lambda to set all possible lambda arities to the
 * lambda. These copies are handed over to next {@code Processor} to do further processing. The result from next step is
 * returned by this step.
 * <p>
 * Requirements by this step is the lambdas type ({@link LambdaEntity#getType()}) only.
 */
public final class ArityProcessor extends Processor {

    /**
     * Defines the maximum arity to create.
     */
    private static final int ARITY_MAX = 3;

    @Override
    protected boolean processable(@Nonnull final LambdaEntity lambda) {
        return lambda.getType() != null;
    }

    @Override
    @Nonnull
    protected List<LambdaEntity> process(@Nonnull final LambdaEntity lambda) {
        final List<LambdaEntity> lambdas = new LinkedList<>();

        // Special Rule: Lambda is of type Comparator it must have arity 2
        if (LambdaUtils.isOfTypeComparator(lambda)) {
            final LambdaEntity copy = LambdaUtils.copy(lambda);
            copy.setArity(2);
            lambdas.addAll(next(copy));
        }

        // Special Rule: Lambda is of type Runnable it must have arity 0
        else if (LambdaUtils.isOfTypeRunnable(lambda)) {
            final LambdaEntity copy = LambdaUtils.copy(lambda);
            copy.setArity(0);
            lambdas.addAll(next(copy));
        }

        // Special Rule: Lambda is of type Supplier it must have arity 0
        else if (LambdaUtils.isOfTypeSupplier(lambda)) {
            final LambdaEntity copy = LambdaUtils.copy(lambda);
            copy.setArity(0);
            lambdas.addAll(next(copy));
        }

        // All other lambdas can have each arity
        else {
            for (int arity = 1; arity <= ARITY_MAX; arity++) {
                final LambdaEntity copy = LambdaUtils.copy(lambda);
                copy.setArity(arity);
                lambdas.addAll(next(copy));
            }
        }
        return lambdas;
    }
}
