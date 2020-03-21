# architecture-spring-core

This project represents a **architecture library (dependency)** related with **elements core to any project (With Spring Framework)** to develop the different parts in a homogeneous way

This library stands out for:

* Define **Spring Core Base Framework** and its versioning (Help define an architecture): Spring, Spring Boot, etc.
* Define **Spring compatibility common Frameworks ** and its versioning (Help to define an architecture)
* Provide ** AOP support ** and ** generic mechanism ** to display logging messages in method execution of projects that use it
* Provide ** General Scope Handler ** (@ControllerAdvice) to handle the vast majority of caught exceptions -> Using @ExceptionHandler
* Provide ** Swagger ** and ** generic mechanism ** for property injection 
* Provide a ** Generic Exception ** "AcmeInvokeRuntimeException" (at Runtime level)
* Provide a ** generic mechanism ** for package scanning in Spring 
* Provide a generic mechanism for package scanning within architecture: "PackageConfig" approach
* Provide a ** generic mechanism ** for pagination control at the Response level
* Provide ** utility classes ** for treatment of: work with pagination, utilities for the swagger generation at project test time, facilitate the work of locating spring components , etc.


IMPORTANT: Be careful with the versions of the project dependencies as well as the dependencies used transitively





## Technological Stack

* Java 8
* [Maven 3](https://maven.apache.org/) - Dependency Management

Dependencies with architecture projects

* **architecture-testing** [0.0.1-SNAPSHOT] : Architecture library for testing used in the test environment
* **architecture-common** [0.0.1-SNAPSHOT] : Architecture library that will be used to provide tools, utilities and dependencies common to all projects


Third Party Dependencies

* **spring-boot** [2.2.0.RELEASE] : Spring boot framework
* **swagger** [2.9.2] : Swagger framework for REST service documentation management
* **springfox-swagger** [2.9.2] : Swagger framework for API documentation
* **spring-aspects** [3.2.0] : Framework for using skins with Spring


Various spring framework starters will be used





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

Each project must implement in its properties application- {environment} .yml the following configuration to generate the required Swagger.

```bash
swagger:
   info:
      tittle: CRUD Example API
      description: Example API Description
      produce-consume: application/json
      version: 1.0
      terms: urn:tos
      contact:
         name: acme
         url: https://www.acme.es
         email: global@acme.corp.com
      license: Apache 2.0
      license-url: http://www.apache.org/licenses/LICENSE-2.0
```





## Versioning

**Note :** [SemVer](http://semver.org/) is used for the versioning. 
To see the available versions access the repository tags





## Authors

* **Víctor Madrid**
