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

import at.gridtec.lambda4j.generator.entities.TypeEntity;

import java.io.Serializable;

// TODO Javadoc
public class Lambda implements Serializable {

    private String name;
    private LambdaTypeEnum type;
    private int arity;
    private boolean throwable;
    private TypeEntity firstInputType;
    private TypeEntity secondInputType;
    private TypeEntity thirdInputType;
    private TypeEntity returnType;

    public Lambda() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LambdaTypeEnum getType() {
        return type;
    }

    public void setType(LambdaTypeEnum type) {
        this.type = type;
    }

    public int getArity() {
        return arity;
    }

    public void setArity(int arity) {
        this.arity = arity;
    }

    public boolean isThrowable() {
        return throwable;
    }

    public void setThrowable(boolean throwable) {
        this.throwable = throwable;
    }

    public TypeEntity getFirstInputType() {
        return firstInputType;
    }

    public void setFirstInputType(TypeEntity firstInputType) {
        this.firstInputType = firstInputType;
    }

    public TypeEntity getSecondInputType() {
        return secondInputType;
    }

    public void setSecondInputType(TypeEntity secondInputType) {
        this.secondInputType = secondInputType;
    }

    public TypeEntity getThirdInputType() {
        return thirdInputType;
    }

    public void setThirdInputType(TypeEntity thirdInputType) {
        this.thirdInputType = thirdInputType;
    }

    public TypeEntity getReturnType() {
        return returnType;
    }

    public void setReturnType(TypeEntity returnType) {
        this.returnType = returnType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Lambda lambda = (Lambda) o;

        if (arity != lambda.arity) {
            return false;
        }
        if (throwable != lambda.throwable) {
            return false;
        }
        if (!name.equals(lambda.name)) {
            return false;
        }
        if (type != lambda.type) {
            return false;
        }
        if (!firstInputType.equals(lambda.firstInputType)) {
            return false;
        }
        if (!secondInputType.equals(lambda.secondInputType)) {
            return false;
        }
        if (!thirdInputType.equals(lambda.thirdInputType)) {
            return false;
        }
        return returnType.equals(lambda.returnType);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + arity;
        result = 31 * result + (throwable ? 1 : 0);
        result = 31 * result + firstInputType.hashCode();
        result = 31 * result + secondInputType.hashCode();
        result = 31 * result + thirdInputType.hashCode();
        result = 31 * result + returnType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Lambda{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", arity=" + arity +
                ", throwable=" + throwable +
                ", firstInputType=" + firstInputType +
                ", secondInputType=" + secondInputType +
                ", thirdInputType=" + thirdInputType +
                ", returnType=" + returnType +
                '}';
    }
}
