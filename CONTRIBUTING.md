# Contributing to Lambda4j Project

We're glad that you want to contribute to the Lambda4j! Lambda4j is licensed under the Apache 2.0 License. If you would like to contribute something, or simply want to hack on the code this document should help you get started.

## Quick Contributing Steps

1. Create an account on [GitHub](https://github.com).
2. Create an issue using the [Issue Tracker](https://github.com/gridtec/lambda4j/issues)
3. Create a pull request for your change on [GitHub](https://github.com).
4. Link to your patch as a rebased git branch (against the current master) and create a pull request from the ticket

We regularly review contributions and will get back to you if we have any suggestions or concerns. If you are reporting a bug, please help to speed up problem diagnosis by providing as much information as possible in either the issue or the pull request.

### Branches and Commits

You should submit your patch as a git branch named after the Github issue, such as GH-XXX (where *XXX* is the issue number). This is called a *topic branch* and allows users to associate a branch of code with the ticket.

It is a best practice to have your commit message have a *summary line* that includes the ticket number, followed by a brief description of the commit. This also helps other contributors understand the purpose of changes to the code.

When writing a commit message, it should explanatory and if you are fixing an existing issue please add *fixes GH-XXX* at the end of the commit message (where *XXX* is the issue number).

### Github and Pull Requests

We don't require you to use Github, and we will even take patch diffs attached to tickets on the issue tracker. However Github has a lot of convenient features, such as being able to see a diff of changes between a pull request and the main repository quickly without downloading the branch.

## IDE

We use recent IDE versions to develop Lambda4j. IntelliJ IDEA is preferred over Eclipse. If you do not have a valid license for IntelliJ IDEA Ultimate, you can also use IntelliJ IDEA Community Edition. If you do not like IntelliJ IDEA and you therefore use Eclipse, please include that into your pull request, that we can test if everything works in IntelliJ IDEA.

## Coding Conventions

Just a few notes here. In general it is good to look at existing code to get a clear picture.

### Javadoc

* Public and private API need Javadoc
* Javadocs need to be explanatory and should describe the code as good as possible
* A package, which is part of the public API, contains a *package-info.java*.
* Unit tests contain no javadoc at all (because they introduce no new API and contain no business logic).
* All classes start with the Apache 2.0 copyright notice (see [LICENSE](#LICENSE) for more info):

```java
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
```

### Packages

* There is only one first-level package: `at.gridtec.lambda4j`.
* Package names are denoted in singular.
* Packages are sliced by domain (e.g. consumer, function, predicate).
* Package private classes are used in order to hide non-public API.
* If possible, inner classes are preferred over package private classes in case of one-to-one dependencies.

### File structure

We organize our classes and interfaces in the following way:

1. Apache 2.0 license header.
2. Package declaration
3. Full import list, which means no use of imports with wildcards. Imports need to have the following order:
   * Static imports
   * Lambda4j imports (at.gridtec.lambda4j)
   * Every other domain, except Java (org, net, etc.)
   * Java imports (java/javax)
4. The Javadoc of the type contains an overview of the API declared in the actual type.
5. The type itself, seperated in static and non-static API, whereas each part is ordered by the following visibility: public, package private, protected, private.

For further information, see the source code, as source code is the best documentation!

### Tests

* Public API is tested.
* High-level functionality is tested in first place.
* Corner cases are tested.
* Trivial methods are not directly tested, e.g. getters, setters.
* The test method name documents the test, i.e. `shouldThrowNullPointerExceptionWhenArgumentIsNull`.

## Generator

If you made chages to the Generator, please run a full reactor build during development by using `mvn clean package -P include-generator`. If everything works as expected, please include the Generator into your pull request.

## Contribution Don't's

Please do **not** modify the version number in the `VERSION` file or change the version section of `pom.xml`, a maintainer will select the appropriate version regarding your change.

Please do **not** modify the following files of this project, which are `.gitignore`, `travis.yml`, `LICENSE`.
