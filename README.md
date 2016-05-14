Lambda4j: Improved Java 8 functional interfaces
=========================================================

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

## Maven Dependency

To add Lambda4j as dependency using Maven, use the following:

```xml
<dependency>
  <groupId>at.gridtec.lambda4j</groupId>
  <artifactId>lambda4j</artifactId>
  <version>1.0.0</version>
</dependency>
```

Using Gradle, use the following:

```
dependencies {
  compile 'at.gridtec.lambda4j:lambda4j:1.0.0'
}
```

## Maven Modules

There are a number of modules in Lambda4j, here is a quick overview:

### lambda4j

The main library providing additional Java&trade; 8 functional interfaces. This
includes non-throwable and throwable functional interfaces.

### lambda4j-core

The core library of Lambda4j for sharing code between different modules. At this point,
this module seems to be unnecessary, but it is required for further features of Lambda4j.

### lambda4j-generator

This module includes the generator for Lambda4j. It is used to generate all
non-throwable and throwable functional interfaces. This is just a helper, as the
amount of functional interfaces are that high, as writing by hand would be a big
cost of time. If a new feature needs to be applied to all functional interfaces
of Lambda4j, the generator is changed and run to apply the changes.

## Maven Build Profiles

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

## Documentation

The documentation with examples is coming soon on the projects website. However, API Documentation is already published (see below under *Links* section).

## Other

If you would like to contribute, please get in contact with us. We would appreciate any further help. If you have found any bugs or want a new feature, please let us know through raising a ticket.

## Links
* [Project Page](http://www.gridtec.at/category/projects/lambda4j)
* [API Doc](http://doc.gridtec.at/lambda4j/latest)
* [Github Project](https://github.com/gridtec/lambda4j)
* [Issue Tracker](https://github.com/gridtec/lambda4j/issues)
