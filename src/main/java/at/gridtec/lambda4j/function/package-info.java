/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * This program is proprietary software; all information contained herein is, and
 * remains, the property of Gridtec and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Gridtec and its suppliers
 * and may be covered by Austrian and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained from Gridtec company.
 *
 * This software consists of voluntary contributions made by individuals on behalf
 * of Gridtec. For more information on Gridtec, please refer to www.gridtec.at homepage.
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