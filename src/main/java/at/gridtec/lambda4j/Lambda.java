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
package at.gridtec.lambda4j;

import javax.annotation.Nonnegative;
import java.io.Serializable;

/**
 * This is a general definition of lambdas of unknown parameters and return value. Also it does not matter if the
 * inheriting lambda is able to throw or not.
 */
public interface Lambda extends Serializable {

    /**
     * The <a href="https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html">serialVersionUID</a>.
     */
    long serialVersionUID = 1L;

    /**
     * Returns the number of arguments for this operation.
     *
     * @return The number of arguments for this operation.
     */
    @Nonnegative
    int arity();
}
