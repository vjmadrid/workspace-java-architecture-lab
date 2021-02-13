# architecture-testing-spring

This project represents a **architecture library (dependency)** related with **testing** (when depends of Spring Framework) to develop the different parts in a homogeneus way

This library stands out for:

* Provides **utility classes** to facilitaty testing with certain elements : exceptions, constant classes, etc. 
* Define **unit testing framework** and their versioning
* Define **mocking testing framework** and their versioning
* Define **matcher testing framework** and their versioning
* Define **architecture testing framework** and their versioning
* Provides a set of **architecture rules** for Spring Framework  with different scopes of action and/or conventions (name, implementations, etc.)
* Provides others **support/help frameworks** by Spring (JSONassert, JsonPath, etc.)
* It includes certain technologies in order to provide support at the architectural rules level.



Build / deployment conditions :

* Deployment as a library





## Technological Stack

* Java 8
* [Maven 3](https://maven.apache.org/) - Dependency Management

Dependencies with architecture projects

N/A

Third Party Dependencies

* **spring-boot-starter-test** [2.3.4.RELEASE] : Spring Framework Test Starter 
* **assertj-core** [3.16.1] : Matching / asserts framework
* **mockito** [3.3.3] : Mocking framework
* **junit-platform-runner** [1.6.2] : Platform unit test framework v5 (Add JUnit Platform + TestEngine API) -> Basic execute test
* **junit-jupiter-engine** [5.6.2] : Unit test framework v5 (Add JUnit Platform + API) Use write test (include : annotations, etc.) (Version 5)
* **archunit-junit5-api** [0.14.1] : assert architecture rules framework (support junit5)
* ....





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

Apply





## Deploy

Custom Library





## Use

Custom Library




## Versioning

**Note :** [SemVer](http://semver.org/) is used for the versioning. 
To see the available versions access the repository tags





## Authors

* **Víctor Madrid**

