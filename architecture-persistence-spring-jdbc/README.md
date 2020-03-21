# architecture-persistence-spring-jdbc

This project represents a **architecture library (dependency)** related with **persistence with Spring JDBC** to develop the different parts in a homogeneous way

This library stands out for:

* Provides a proposal for **implementation** of the **generic interface** for **Spring JDBC**.
* Define the **Hibernate framework** and their versioning





## Technological Stack

* Java 8
* [Maven 3](https://maven.apache.org/) - Dependency Management

Dependencies with architecture projects

* **architecture-testing** [0.0.1-SNAPSHOT] : Architecture library for testing used in the test environment
* **architecture-persistence** [0.0.1-SNAPSHOT] : Architectural library for persistence

Third Party Dependencies

* **spring-jdbc** [5.2.0.RELEASE] : Implementation of Spring for JDBC (Imposes transitivity libraries)
* **commons-dbcp2** [2.7.0] : Framework to get connection Pooling
* **c3p0** [0.9.5.5] : Framework to get connection Pooling and Statement cache





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

N/A





## Deploy

Custom Library





## Use

Custom Library





## Versioning

**Note :** [SemVer](http://semver.org/) is used for the versioning. 
To see the available versions access the repository tags





## Authors

* **Víctor Madrid**
