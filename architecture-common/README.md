# architecture-common

This project represents a **architecture library (dependency)** related with **elements common to any project** to develop the different parts in a homogeneous way

This library stands out for:

* Provides **global contant classes**
* Provides **utility classes** to facilitaty testing with certain elements : converters, transformers, etc.
* Provides a **generic exception** "AcmeException" (Main Business Exception) to have a differentiating type of exception in the project (the rest of exceptions should inherit from it)
* Provides  an **abstract component for generating entities or models** 
* Define **common frameworks** and their versioning (Help to define an architecture)


## Technological Stack

* Java 8
* [Maven 3](https://maven.apache.org/) - Dependency Management

Dependencies with architecture projects

* **architecture-testing** [0.0.1-SNAPSHOT] : Architecture library for testing used in the test environment

Third Party Dependencies

* **commons-lang3** [3.9] : Utilities framework for classes
* **commons-collections4** [4.4] : Framework of utilities to work with collections
* **jackson-databind** [2.10.0] : Bookstore to work with Jackson
* **jackson-datatype-jsr310** [2.10.0] : Library to support JSR-310 (Java 8 Date & Time API) data types
* **slf4j-api** [1.7.25] : Framework for loggin definition
* **log4j-over-slf4j** [1.7.25] : Implementation of log4j
* **logback** [1.2.3] : Logging Framework


## Prerequisites

Define what elements are needed to install the software

* Java 8 installed (1.5+ version required)
* Maven installed  (3+)


## Installation

Steps to follow

* Start a terminal
* To be located in the PATH of installation (the place where the project is located)
* Verify that the file "pom.xml" is available

Execute the following command

```bash
mvn clean install
```

The result will be the generation of an artifact in your Maven repository (local)


## Testing

This project has tests : Unit + Integration

Execute with IDE or Maven


## Deploy

Custom Library


## Use

Custom Library


## Versioning

**Note :** [SemVer](http://semver.org/) is used for the versioning. 
To see the available versions access the repository tags


## Authors

* **Víctor Madrid**
