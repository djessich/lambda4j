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

/**
 * This package provides additional {@link java.util.function.Function}s, which are {@code FunctionalInterface}s that
 * take at least an argument and always return a type. Thereby lots of different combinations are possible, for example
 * a function of {@code T -> R}, {@code T,U -> R} or {@code T,U,V -> R}. This package also includes additional primitive
 * specializations of functions.
 * <p>
 * This library implements functions from arity {@code 1} to {@code 3}.
 */
package at.gridtec.lambda4j.function;