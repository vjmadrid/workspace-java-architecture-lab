# architecture-web-jsf

Este proyecto representa a una **librería de arquitectura** relacionada con la impementación de la capa web en base a **JSF** para desarrollar las diferentes partes de forma homogenea

Esta librería destaca por :

* Proporcionar **clases de constantes globales** para el ámbito web
* Proporcionar **clases de utilidades** que facilitarán el trabajo con diferentes elementos relacionados con el ámbito web
* Proporciona una propuesta de **implementación** de la **controller genérico**
* Definir los **frameworks web / jsf** y su versionado


## Stack Tecnológico

* Java 8
* [Maven 3](https://maven.apache.org/) - Gestión de dependencias

Dependencias con proyectos de arquitectura

* **architecture-testing** [1.0.0-SNAPSHOT] : Librería de arquitectura para testing utilizada en el ámbito de test

Dependencias terceros

* **javax.servlet-api** [3.1.0] : Facilita el uso de Servlets
* **jsp-api** [2.2] : Facilita el uso de JSPs
* **jstl** [1.2] : Facilita el uso de JSTL
* **taglibs** [1.1.2] : Facilita el uso de JSTL
* **jsf-api** [2.2.15] : Facilita el uso de JSF
* **primefaces** [6.1] : Framework impementación de JSF
* **primefaces-extensions** [6.1] : Extensiones para el framework Primefaces


## Prerrequisitos

Se definen que elementos se necesitan para instalar el software

* Tener instalado Java 8 (Se requiere versión 1.5+)
* Tener instalado Maven (Se aconseja que sea 3+)


### Instalación

Pasos a seguir 

* Arrancar la consola
* Situarse en el PATH de instalación (el lugar donde se encuentra el proyecto)
* Verificar que se encuentra disponible el fichero "pom.xml"

Ejecutar el siguiente comando

```bash
mvn clean install
```

El resultado será la generación de un artefacto en tu repositorio maven


## Testing

Este proyecto dispone de test

Nota : utilizar todos los comandos de maven


## Despliegue

No aplica


## Versionado

**Nota :** Se utiliza [SemVer](http://semver.org/) para el versionado. 
Para ver las versiones disponibles acceder a los tags del repositorio

## Autores

* **Víctor Madrid**
