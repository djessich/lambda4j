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

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Represents a {@link Processor} which sets the primitive flag if the output or one of the input arguments is a
 * primitive. The changed input argument is handed over to next {@code Processor} to do further processing. The result
 * from next step is returned by this step.
 * <p>
 * There are no requirements by this step.
 */
public final class PrimitiveProcessor extends Processor {

    @Override
    protected boolean processable(@Nonnull final Lambda lambda) {
        return true;
    }

    @Override
    @Nonnull
    protected List<Lambda> process(@Nonnull final Lambda lambda) {
        // If return type or one of the input types is primitive, set primitive flag; otherwise return lambda as-is
        if (PRIMITIVES.contains(lambda.getReturnType())) {
            lambda.setPrimitive(true);
        } else if (PRIMITIVES.contains(lambda.getInputOneType())) {
            lambda.setPrimitive(true);
        } else if (PRIMITIVES.contains(lambda.getInputTwoType())) {
            lambda.setPrimitive(true);
        } else if (PRIMITIVES.contains(lambda.getInputThreeType())) {
            lambda.setPrimitive(true);
        }
        return next(lambda);
    }
}
