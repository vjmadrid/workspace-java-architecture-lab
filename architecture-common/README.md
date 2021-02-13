# architecture-common

This project represents a **architecture library (dependency)** related with **elements common to any project** to develop the different parts in a homogeneous way

This library stands out for:

* Provides **global contant classes** para : environment management, project structure, global values, DTO values, Entity values, web element configuration, validation patterns, resource file loader for Dummy support, etc.
* Provides **utility classes** to facilitaty testing with certain elements : converters, transformers, serialize, list, date, ....
* Provide **Conversion Classes (utility variant)** to perform conversions between formats, types, etc.
* Provides a **generic exception** "AcmeException" (Main Business Exception) to have a differentiating type of exception in the project (the rest of exceptions should inherit from it)
* Provides  an **abstract component for generating entities or models** 
* Define **common frameworks** and their versioning (Help to define an architecture)



Build / Deployment conditions :

* Deploy as a library





## Technological Stack

* Java 8
* [Maven 3](https://maven.apache.org/) - Dependency Management

Dependencies with architecture projects

* **architecture-testing** [0.0.1-SNAPSHOT] : Architecture library for testing used in the test environment

Third Party Dependencies

* **commons-lang3** [3.9] : Class utilities
* **commons-collections4** [4.4] : Collection utilities
* **commons-io** [2.6] : Class utilities for : file, streams, file comparator, file filters, ...

* **lombok** [1.18.12] : Java Tool for automatic generation of getters, setters, equals, hashCode , toString, more
* **mapstruct** [1.3.1.Final] : Tool for mapping classes

* **jackson-databind** [2.10.0] : Utilities to work with Jackson
* **jackson-datatype-jsr310** [2.10.0] : Library to support JSR-310 (Java 8 Date & Time API) data types
* **jackson-dataformat-yaml** [2.10.0] : Framework for work with YAML format

* **slf4j-api** [1.7.25] : Framework for loggin definition
* **log4j-over-slf4j** [1.7.25] : Implementation of log4j
* **logback** [1.2.3] : Logging Framework

* **hibernate-validator** [6.1.5.Final] :  Framework for Bean Validation




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
