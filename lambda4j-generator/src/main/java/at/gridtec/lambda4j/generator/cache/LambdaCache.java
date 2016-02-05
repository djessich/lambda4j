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
package at.gridtec.lambda4j.generator.cache;

import at.gridtec.lambda4j.generator.Lambda;

import java.util.LinkedList;
import java.util.List;

/**
 * A cache for all generated {@link Lambda}s.
 */
// TODO Javadoc for this class
// TODO move to another package
public final class LambdaCache {

    private static LambdaCache instance = null;

    private List<Lambda> lambdas = new LinkedList<>();

    /**
     * Private constructor to prevent instantiation.
     */
    private LambdaCache() {

    }

    /**
     * Returns the only singleton instance for this class.
     *
     * @return The only singleton instance for this class.
     */
    public static LambdaCache getInstance() {
        if (LambdaCache.instance == null) {
            synchronized (LambdaCache.class) {
                if (LambdaCache.instance == null) {
                    LambdaCache.instance = new LambdaCache();
                }
            }
        }
        return LambdaCache.instance;
    }

    /**
     * Returns a list of all generated {@link Lambda}s.
     *
     * @return A list of all generated {@link Lambda}s.
     */
    public List<Lambda> getLambdas() {
        return lambdas;
    }

    /**
     * Sets the generated list of {@link Lambda}s.
     *
     * @param lambdas The generated {@code Lambda}s to be set
     */
    public void setLambdas(List<Lambda> lambdas) {
        this.lambdas = lambdas;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singletons are not cloneable");
    }
}
