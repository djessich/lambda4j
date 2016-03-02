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

import at.gridtec.lambda4j.generator.cache.LambdaCache;
import at.gridtec.lambda4j.generator.entities.LambdaEntity;
import at.gridtec.lambda4j.generator.processors.Processor;
import at.gridtec.lambda4j.generator.util.LambdaUtils;

import org.apache.commons.lang3.ClassUtils;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Function;

/**
 * Represents a {@link Processor} which sets the lambdas JDK flag using {@link LambdaEntity#setFromJDK(boolean)}, which
 * defines if the lambda can also be found in the JDK. The lambda is handed over to next {@code Processor} to do further
 * processing. The result from next step is returned by this step.
 * <p>
 * There are no requirements by this step.
 */
public class JdkProcessor extends Processor {

    /**
     * Defines the {@link java.util.function} package name.
     */
    private static final String JAVA_UTIL_FUNCTION_PACKAGE = ClassUtils.getPackageName(Function.class);

    @Override
    protected boolean processable(@Nonnull final LambdaEntity lambda) {
        return true;
    }

    @Nonnull
    @Override
    protected List<LambdaEntity> process(@Nonnull final LambdaEntity lambda) {
        // Check if lambda is also available in the JDK
        if (isLambdaFromJdk(lambda)) {
            lambda.setFromJDK(true);
            // Found jdk lambda so add it to list
            LambdaCache.getInstance().getJdkLambdas().add(LambdaUtils.copy(lambda));
        } else {
            lambda.setFromJDK(false);
        }
        return next(lambda);
    }

    /**
     * Searches if the given lambda can be found in the {@link java.util.function} package.
     *
     * @param lambda The lambda to be searched for
     * @return {@code true}, if and only if, lambda has been found in the jdk, {@code false} otherwise
     */
    private boolean isLambdaFromJdk(final LambdaEntity lambda) {
        try {
            Class.forName(JAVA_UTIL_FUNCTION_PACKAGE + "." + lambda.getName());
        } catch (ClassNotFoundException ignored) {
            return false;
        }
        return true;
    }
}
