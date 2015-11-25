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
package at.gridtec.lambda4j.meta;

/**
 * This interface allows an implementing class to return itself. Obviously, this is just a meta interface, as every
 * class would be able to implement the {@link #getThis()} method of this interface on its own.
 *
 * @param <S> The type of the implementing type
 */
@SuppressWarnings("unused")
public interface Selfable<S extends Selfable<S>> {

    /**
     * Returns a reference to itself (this).
     *
     * @return A reference to itself (this).
     */
    @SuppressWarnings("unchecked")
    default S getThis() {
        return (S) this;
    }
}
