# architecture-persistence

This project represents a **architecture library (dependency)** related with **persistence** to develop the different parts in a homogeneous way

This library stands out for:

* Provides  a proposal of generic interface** to implement **repositories**
* Provides a proposal for **implementation** of the **generic interface** for **in-memory use** (List and Map)
* Provides **utility classes** to facilitate developments on persistence
* Define the **persistence frameworks** and their versioning


## Technological Stack

* Java 8
* [Maven 3](https://maven.apache.org/) - Dependency Management

Dependencies with architecture projects

* **architecture-testing** [0.0.1-SNAPSHOT] : Architecture library for testing used in the test environment

Third Party Dependencies

* **h2** [1.4.196] : In-memory database framework
* **liquibase** [3.8.0] : Database change management framework


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
