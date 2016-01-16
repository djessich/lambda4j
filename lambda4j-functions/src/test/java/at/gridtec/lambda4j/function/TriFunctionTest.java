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
package at.gridtec.lambda4j.function;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@SuppressWarnings({ "unchecked", "Duplicates", "ConstantConditions" })
@Test(groups = { "unit", "function", "tri" })
public final class TriFunctionTest<T, U, V, R> {

    private R testValue = (R) "test";

    @Test(dataProvider = "callArgs")
    public void testCall(T arg1, U arg2, V arg3, R ret) {
        final TriFunction<T, U, V, R> function = (t, u, v) -> testValue;
        final R result = TriFunction.call(function, arg1, arg2, arg3);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, ret);
    }

    @Test(expectedExceptions = { NullPointerException.class })
    public void testCallNull() {
        TriFunction.call(null, new Object(), new Object(), new Object());
    }

    @Test(dataProvider = "onlyArgs")
    public void testOnlyFirst(T arg1, U arg2, V arg3) {
        final Object result = TriFunction.onlyFirst(Function.identity()).apply(arg1, arg2, arg3);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, arg1);
    }

    @Test(expectedExceptions = { NullPointerException.class })
    public void testOnlyFirstNull() {
        TriFunction.onlyFirst(null).apply(new Object(), new Object(), new Object());
    }

    @Test(dataProvider = "onlyArgs")
    public void testOnlySecond(T arg1, U arg2, V arg3) {
        final Object result = TriFunction.onlySecond(Function.identity()).apply(arg1, arg2, arg3);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, arg2);
    }

    @Test(expectedExceptions = { NullPointerException.class })
    public void testOnlySecondNull() {
        TriFunction.onlySecond(null).apply(new Object(), new Object(), new Object());
    }

    @Test(dataProvider = "onlyArgs")
    public void testOnlyThird(T arg1, U arg2, V arg3) {
        final Object result = TriFunction.onlyThird(Function.identity()).apply(arg1, arg2, arg3);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, arg3);
    }

    @Test(expectedExceptions = { NullPointerException.class })
    public void testOnlyThirdNull() {
        TriFunction.onlyThird(null).apply(new Object(), new Object(), new Object());
    }

    @Test
    public void testConstant() {
        final R result = TriFunction.constant(testValue).apply(new Object(), new Object(), new Object());
        Assert.assertNotNull(result);
        Assert.assertEquals(result, testValue);
    }

    public void testConstantNull() {
        final Object result = TriFunction.constant(null).apply(new Object(), new Object(), new Object());
        Assert.assertNull(result);
    }

    @Test
    public void testApplyTuple() {

    }

    @Test
    public void testArity() {
        final TriFunction<T, U, V, R> function = (t, u, v) -> testValue;
        int value = function.arity();
        Assert.assertEquals(value, 3);
    }

    @Test
    public void testCompose() {

    }

    @Test
    public void testAndThen() {

    }

    @Test
    public void testConsume() {

    }

    @Test
    public void testPartial() {

    }

    @Test
    public void testPartial1() {

    }

    @Test
    public void testPartial2() {

    }

    @Test
    public void testCurried() {

    }

    @Test
    public void testTupled() {

    }

    @Test
    public void testReversed() {

    }

    @Test
    public void testNonNull() {

    }

    @DataProvider(name = "callArgs")
    private Iterator<Object[]> callArgs() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[] { "test1", "test2", "test3", testValue });
        list.add(new Object[] { "test1", "test2", "test3", testValue });
        list.add(new Object[] { "test1", "test2", "test3", testValue });
        return list.iterator();
    }

    @DataProvider(name = "onlyArgs")
    private Iterator<Object[]> onlyArgs() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[] { "test1", "test2", "test3" });
        list.add(new Object[] { "test1", "test2", "test3" });
        list.add(new Object[] { "test1", "test2", "test3" });
        return list.iterator();
    }
}