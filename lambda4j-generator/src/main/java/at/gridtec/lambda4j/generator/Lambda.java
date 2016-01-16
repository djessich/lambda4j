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

import java.io.Serializable;

public class Lambda implements Serializable {

    private String name;
    private LambdaTypeEnum type;
    private int arity;
    private boolean primitive;
    private boolean throwable;
    private String inputOneType;
    private String inputTwoType;
    private String inputThreeType;
    private String returnType;

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

    public boolean isPrimitive() {
        return primitive;
    }

    public void setPrimitive(boolean primitive) {
        this.primitive = primitive;
    }

    public boolean isThrowable() {
        return throwable;
    }

    public void setThrowable(boolean throwable) {
        this.throwable = throwable;
    }

    public String getInputOneType() {
        return inputOneType;
    }

    public void setInputOneType(String inputOneType) {
        this.inputOneType = inputOneType;
    }

    public String getInputTwoType() {
        return inputTwoType;
    }

    public void setInputTwoType(String inputTwoType) {
        this.inputTwoType = inputTwoType;
    }

    public String getInputThreeType() {
        return inputThreeType;
    }

    public void setInputThreeType(String inputThreeType) {
        this.inputThreeType = inputThreeType;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
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
        if (primitive != lambda.primitive) {
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
        if (!inputOneType.equals(lambda.inputOneType)) {
            return false;
        }
        if (!inputTwoType.equals(lambda.inputTwoType)) {
            return false;
        }
        if (!inputThreeType.equals(lambda.inputThreeType)) {
            return false;
        }
        return returnType.equals(lambda.returnType);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + arity;
        result = 31 * result + (primitive ? 1 : 0);
        result = 31 * result + (throwable ? 1 : 0);
        result = 31 * result + inputOneType.hashCode();
        result = 31 * result + inputTwoType.hashCode();
        result = 31 * result + inputThreeType.hashCode();
        result = 31 * result + returnType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Lambda{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", arity=" + arity +
                ", primitive=" + primitive +
                ", throwable=" + throwable +
                ", inputOneType='" + inputOneType + '\'' +
                ", inputTwoType='" + inputTwoType + '\'' +
                ", inputThreeType='" + inputThreeType + '\'' +
                ", returnType='" + returnType + '\'' +
                '}';
    }
}
