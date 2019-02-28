# architecture-testing

Este proyecto representa a una **librería de arquitectura** relacionada con **testing** para desarrollar las diferentes partes de forma homogenea

Esta librería destaca por :

* Proporciona **clases de utilidades** para facilitar las pruebas con ciertos elementos : excepciones, clases con constantes, etc. 
* Definir los **frameworks de testing** y su versionado


## Stack Tecnológico

* Java 8
* [Maven 3](https://maven.apache.org/) - Gestión de dependencias

Dependencias 

* **hamcrest** [1.3] : Framework para matching / asserts
* **assertj-core** [3.12.0] : Framework para matching / asserts
* **mockito** [1.10.19] : Framework para mocking
* **junit** [4.12] : Framework para test unitarios


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

Este proyecto NO dispone de test


## Despliegue

No aplica


## Versionado

**Nota :** Se utiliza [SemVer](http://semver.org/) para el versionado. 
Para ver las versiones disponibles acceder a los tags del repositorio

## Autores

* **Víctor Madrid**
