# architecture-event-driven

Este proyecto representa a una **librería de arquitectura** relacionada con la creación de un **modelo de gestión de eventos** para desarrollar las diferentes partes de forma homogenea

Esta librería destaca por :

* Proporcionar **clases de constantes**
* Proporcionar una **clase genérica de evento**
* Proporcionar una **tipología genérica de evento** 
* Proporcionar **clases de utilidad** : factory, validadores, etc. 
* Proporcionar una **excepción generica** "EventDrivenException" para disponer de un tipo de excepción diferenciadora en el proyecto (el resto de excepciones deberían de heredar de ella)
Nota : Esta excepción hereda de la excepción global de arquitectura

## Stack Tecnológico

* Java 8
* [Maven 3](https://maven.apache.org/) - Gestión de dependencias

Dependencias con proyectos de arquitectura

* **architecture-testing** [1.0.0-SNAPSHOT] : Librería de arquitectura para testing utilizada en el ámbito de test
* **architecture-common** [1.0.0-SNAPSHOT] : Librería de arquitectura para proporcionar los elementos globales a los proyectos



Dependencias terceros

Las proporcionadas por las librerías de arquitectura


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
