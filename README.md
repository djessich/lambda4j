Lambda4j: Improved Java 8 functional interfaces (lambdas)
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

Lambda4j can be obtained using maven by adding the following dependency to your project.

```xml
<dependency>
  <groupId>at.gridtec.lambda4j</groupId>
  <artifactId>lambda4j</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Dependencies

The Lambda4j projects has the following dependencies:

 * [Apacha Commons Lang](https://commons.apache.org/proper/commons-lang/)
 * [javax.annotation as per JSR-305](http://findbugs.sourceforge.net/)

If you like to use Lambda4j in your project without Dependency Management suach as Maven, then the dependencies from above must be on your classpath.

## Documentation

The documentation is coming soon on the projects website.

## Other

If you would like to contribute, please get in contact with us. We would appreciate any further help. If you have found any bugs or want a new feature, please let us know through raising a ticket.

## Links
* [Project Page](http://www.gridtec.at/category/projects/lambda4j)
* [API Doc](http://www.gridtec.at/doc/lambda4j/latest)
* [Github Project](https://github.com/gridtec/lambda4j)
* [Issue Tracker](https://github.com/gridtec/lambda4j/issues)
