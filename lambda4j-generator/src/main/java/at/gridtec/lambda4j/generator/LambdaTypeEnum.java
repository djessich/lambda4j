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
package at.gridtec.lambda4j.generator;

/**
 * Created by Dominik Jessich on 29.11.2015.
 */
// TODO Javadoc
// TODO Move to correct pacakge
public enum LambdaTypeEnum {

    COMPARATOR(1, "comparator"),
    CONSUMER(2, "consumer"),
    FUNCTION(3, "function"),
    OPERATOR(4, "operator"),
    PREDICATE(5, "predicate"),
    RUNNABLE(6, "runnable"),
    SUPPLIER(7, "supplier");

    private final int id;
    private final String simpleName;

    /**
     * Constructs this enum name.
     *
     * @param id The id for the constant
     */
    private LambdaTypeEnum(int id, final String simpleName) {
        this.id = id;
        this.simpleName = simpleName;
    }

    /**
     * Returns the id for the enum constant.
     *
     * @return The id for the enum constant.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name for the enum constant.
     *
     * @return The name for the enum constant.
     */
    public String getSimpleName() {
        return simpleName;
    }

    /**
     * Returns the name of this enum constant, as contained in the declaration.  This method may be overridden, though
     * it typically isn't necessary or desirable.  An enum name should override this method when a more
     * "programmer-friendly" string form exists.
     *
     * @return The name of this enum constant
     */
    @Override
    public String toString() {
        return this.simpleName;
    }
}
