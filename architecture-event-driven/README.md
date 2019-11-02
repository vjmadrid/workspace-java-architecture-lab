# architecture-event-driven

This project represents a **architecture library (dependency)** related with the creation of a **event management model** (event driven) to develop the different parts in a homogeneous way

This library stands out for:

* Provides **constants classes**
* Provides a **generic event model**
* Provides a **generic event typology**
* Provides **utility classes** : factory, validators, converters, etc.
* Provides a **generic event exception** "EventDrivenException" to have a differentiating exception type in the project (the rest of exceptions should inherit from it).

Note : This exception inherits the global exception of architecture.


## Technological Stack

* Java 8
* [Maven 3](https://maven.apache.org/) - Dependency Management

Dependencies with architecture projects

* **architecture-testing** [0.0.1-SNAPSHOT] : Architecture library for testing used in the test environment
* **architecture-common** [0.0.1-SNAPSHOT] : Architecture library to provide global elements to projects

Third Party Dependencies

* Those provided by the architecture libraries


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

This project has tests : Unit 

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
