# Lambda4j: Improved Java 8 functional interfaces

[![Build Status](https://travis-ci.org/djessich/lambda4j.svg?branch=master)](https://travis-ci.org/djessich/lambda4j)
[![License](https://img.shields.io/badge/license-Apache_2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Maven Central](https://img.shields.io/maven-central/v/org.lambda4j/lambda4j.svg)](https://maven-badges.herokuapp.com/maven-central/org.lambda4j/lambda4j)

**This project was moved from Gridtec organization and is currently under heavy development. Please use old release unless a new one with all changes applied is present.**

The Lambda4j project focuses on the implementation of non-throwable and throwable Java&trade; 8 functional interfaces (lambdas). It supports the following features:

* new non-throwable functional interfaces
* new throwable functional interfaces
* JDK functional interfaces support/compatibility
* JDK functional interfaces methods (f.e. composition)
* additional functional interfaces methods (f.e. currying, memoization, ...)
* additional throwable functional interfaces methods (f.e. nesting, sneaky throwing, ...)
* functional interfaces until arity 3
* serialization support

The new functional interface types support every object type and all primitive types (boolean, byte, char, double, float, int, long, short).

## Requirements

The project requires Maven 3.0.3 and Java&trade; 8 or above to run.

## Include as dependency

To add Lambda4j as dependency using Maven, use the following:

```xml
<dependency>
  <groupId>org.lambda4j</groupId>
  <artifactId>lambda4j</artifactId>
  <version>1.0.0</version>
</dependency>
```

Using Gradle, use the following:

```
dependencies {
  compile 'org.lambda4j:lambda4j:1.0.0'
}
```

## Modules

There are a number of modules in Lambda4j, here is a quick overview:

### lambda4j

The main library providing additional Java&trade; 8 functional interfaces. This includes non-throwable and throwable functional interfaces.

### lambda4j-core

The core library of Lambda4j for sharing code between different modules. At this point, this module seems to be unnecessary, but it is required for further features of Lambda4j.

### lambda4j-doc

This module includes all the documentation with exampes for Lambda4j. This is currently in progress.

### lambda4j-generator

This module includes the generator for Lambda4j. It is used to generate all non-throwable and throwable functional interfaces. This is just a helper, as the amount of functional interfaces are that high, as writing by hand would be a big cost of time. If a new feature needs to be applied to all functional interfaces of Lambda4j, the generator is changed and run to apply the changes.

## Build Profiles

This project includes different useful profiles. Refer below for more info.

### Generator

To enable the *lambda4j-generator* module during reactor build, Lambda4j provides
two different profiles. The `include-generator` profile is only used for adding
the generator module to reactor build - nothing more.

The `run-generator` is used for running the generator during reactor build. This
profile binds to *process-sources* phase of reactor build. It deletes all previously
generated files using generator module.

Both profiles are by default disabled. The recommended way to use both are
`mvn clean package -P include-generator` or `mvn clean package -P run-generator`.

If both profiles are enabled at the same time, the `run-generator` profile overwrites
the `include-generator` profile. This means that the `run-generator` adds the generator
module to reactor build (same as `include-generator` profile) and then runs the
generator during reactor build.

### Source and Javadoc jars

To enable generation of source and javadoc jars during reactor build, the `source`
or `javadoc` profile needs to be enabled. This profiles are disabled by default,
for faster building. The recommended way to use this profiles are `mvn <goal> -P source`
or `mvn <goal> -P javadoc` respectively. Both profiles bind to *package* phase of
reactor build.

### Show compiler warnings

To show compiler warnings during reactor build, the `show-compiler-warnings` profile
needs to be enabled. This profile is by default disabled. The recommended way to
use this profile is `mvn <goal> -P show-compiler-warnings`. The profile binds to
*compile* phase of reactor build.

### Disable tests

To disable all tests during reactor build, the `disable-tests` profile needs to
be enabled. This profile is by default disabled. The recommended way to use this
profile is `mvn <goal> -P disable-tests`. The profile binds to *test* phase of
reactor build.

## Dependencies

The Lambda4j project has the following dependencies:

 * [Apacha Commons Lang](https://commons.apache.org/proper/commons-lang/)
 * [javax.annotation as per JSR-305](http://findbugs.sourceforge.net/)

If you like to use Lambda4j in your project without Dependency Management suach as Maven, then the dependencies from above must be on your classpath.

## Contribute

Please see [CONTRIBUTING.md](#CONTRIBUTING.md).

## License & Authors

* Author: Dominik Jessich ([dominik.jessich@gmail.com](mailto:dominik.jessich@gmail.com))
* Copyright: 2015-2021, The lambda4j authors

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
