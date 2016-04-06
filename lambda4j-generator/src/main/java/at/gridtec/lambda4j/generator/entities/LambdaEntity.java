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
package at.gridtec.lambda4j.generator.entities;

import at.gridtec.lambda4j.generator.LambdaTypeEnum;

import java.io.Serializable;

/**
 * Represents an entity which describes a lambda. This includes the lambdas package, its name, its type, its arity, its
 * public abstract method and its return and input types. Also this entity saves if the lambda is throwable or
 * represents a lambda which is already defined in the {@code JDK}.
 */
public class LambdaEntity implements Serializable {

    /**
     * The lambdas package name.
     */
    private String packageName;

    /**
     * The lambdas name.
     */
    private String name;

    /**
     * The lambdas type represented {@link LambdaTypeEnum}.
     */
    private LambdaTypeEnum type;

    /**
     * The lambdas method name.
     */
    private String method;

    /**
     * The lambdas arity. This can either be {@code 0} until {@code 3}.
     */
    private int arity;

    /**
     * The lambdas return type represented by {@link TypeEntity} instance.
     */
    private TypeEntity returnType;

    /**
     * The lambdas first input type represented by {@link TypeEntity} instance.
     */
    private TypeEntity firstInputType;

    /**
     * The lambdas second input type represented by {@link TypeEntity} instance.
     */
    private TypeEntity secondInputType;

    /**
     * The lambdas third input type represented by {@link TypeEntity} instance.
     */
    private TypeEntity thirdInputType;

    /**
     * The lambdas throwable type which is only set if the {@link #throwable} flag is set to {@code true}.
     */
    private TypeEntity throwableType;

    /**
     * A flag indicating if the lambda is throwable.
     */
    private boolean throwable;

    /**
     * A flag indicating if the lambda is already defined in the {@code JDK}.
     */
    private boolean fromJDK;

    /**
     * Public default constructor for reflection purposes.
     */
    public LambdaEntity() {

    }

    /**
     * Returns the lambdas package name.
     *
     * @return The lambdas package name.
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Sets the lambdas package name.
     *
     * @param packageName The lambdas package name to be set.
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Returns the lambdas name.
     *
     * @return The lambdas name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the lambdas name.
     *
     * @param name The lambdas name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the lambdas type.
     *
     * @return The lambdas type.
     */
    public LambdaTypeEnum getType() {
        return type;
    }

    /**
     * Sets the lambdas type.
     *
     * @param type The lambdas type to be set.
     */
    public void setType(LambdaTypeEnum type) {
        this.type = type;
    }

    /**
     * Returns the lambdas method.
     *
     * @return The lambdas method.
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets the lambdas method.
     *
     * @param method The lambdas method to be set.
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Returns the lambdas arity.
     *
     * @return The lambdas arity.
     */
    public int getArity() {
        return arity;
    }

    /**
     * Sets the lambdas arity.
     *
     * @param arity The lambdas arity to be set.
     */
    public void setArity(int arity) {
        this.arity = arity;
    }

    /**
     * Returns the lambdas return type.
     *
     * @return The lambdas return type.
     */
    public TypeEntity getReturnType() {
        return returnType;
    }

    /**
     * Sets the lambdas return type.
     *
     * @param returnType The lambdas return type to be set.
     */
    public void setReturnType(TypeEntity returnType) {
        this.returnType = returnType;
    }

    /**
     * Returns the lambdas first input type.
     *
     * @return The lambdas first input type.
     */
    public TypeEntity getFirstInputType() {
        return firstInputType;
    }

    /**
     * Sets the lambdas first input type.
     *
     * @param firstInputType The lambdas first input type to be set.
     */
    public void setFirstInputType(TypeEntity firstInputType) {
        this.firstInputType = firstInputType;
    }

    /**
     * Returns the lambdas second input type.
     *
     * @return The lambdas second input type.
     */
    public TypeEntity getSecondInputType() {
        return secondInputType;
    }

    /**
     * Sets the lambdas second input type.
     *
     * @param secondInputType The lambdas second input type to be set.
     */
    public void setSecondInputType(TypeEntity secondInputType) {
        this.secondInputType = secondInputType;
    }

    /**
     * Returns the lambdas third input type.
     *
     * @return The lambdas third input type.
     */
    public TypeEntity getThirdInputType() {
        return thirdInputType;
    }

    /**
     * Sets the lambdas third input type.
     *
     * @param thirdInputType The lambdas third input type to be set.
     */
    public void setThirdInputType(TypeEntity thirdInputType) {
        this.thirdInputType = thirdInputType;
    }

    public TypeEntity getThrowableType() {
        return throwableType;
    }

    public void setThrowableType(TypeEntity throwableType) {
        this.throwableType = throwableType;
    }

    /**
     * Returns the flag indicating if the lambda is throwable.
     *
     * @return The flag indicating if the lambda is throwable.
     */
    public boolean isThrowable() {
        return throwable;
    }

    /**
     * Sets the lambdas throwable flag.
     *
     * @param throwable The lambdas throwable flag to be set.
     */
    public void setThrowable(boolean throwable) {
        this.throwable = throwable;
    }

    /**
     * Returns the flag indicating if the lambda is already defined in the {@code JDK}.
     *
     * @return The flag indicating if the lambda is already defined in the {@code JDK}.
     */
    public boolean isFromJDK() {
        return fromJDK;
    }

    /**
     * Sets the lambdas flag indicating if the lambda is already defined in the {@code JDK}.
     *
     * @param fromJDK The lambdas flag indicating if the lambda is already defined in the {@code JDK}.
     */
    public void setFromJDK(boolean fromJDK) {
        this.fromJDK = fromJDK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LambdaEntity lambda = (LambdaEntity) o;

        if (arity != lambda.arity) {
            return false;
        }
        if (throwable != lambda.throwable) {
            return false;
        }
        if (fromJDK != lambda.fromJDK) {
            return false;
        }
        if (!packageName.equals(lambda.packageName)) {
            return false;
        }
        if (!name.equals(lambda.name)) {
            return false;
        }
        if (type != lambda.type) {
            return false;
        }
        if (!method.equals(lambda.method)) {
            return false;
        }
        if (!returnType.equals(lambda.returnType)) {
            return false;
        }
        if (!firstInputType.equals(lambda.firstInputType)) {
            return false;
        }
        if (!secondInputType.equals(lambda.secondInputType)) {
            return false;
        }
        return thirdInputType.equals(lambda.thirdInputType);

    }

    @Override
    public int hashCode() {
        int result = packageName.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + method.hashCode();
        result = 31 * result + arity;
        result = 31 * result + returnType.hashCode();
        result = 31 * result + firstInputType.hashCode();
        result = 31 * result + secondInputType.hashCode();
        result = 31 * result + thirdInputType.hashCode();
        result = 31 * result + (throwable ? 1 : 0);
        result = 31 * result + (fromJDK ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LambdaEntity{" +
                "packageName='" + packageName + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", method='" + method + '\'' +
                ", arity=" + arity +
                ", returnType=" + returnType +
                ", firstInputType=" + firstInputType +
                ", secondInputType=" + secondInputType +
                ", thirdInputType=" + thirdInputType +
                ", throwable=" + throwable +
                ", fromJDK=" + fromJDK +
                '}';
    }
}
