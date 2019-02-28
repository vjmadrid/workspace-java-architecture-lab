# architecture-persistence-hibernate

Este proyecto representa a una **librería de arquitectura** relacionada con la **persistencia con MyBatis** para desarrollar las diferentes partes de forma homogenea

Esta librería destaca por :

* Definir los **frameworks de persistencia** y su versionado


## Stack Tecnológico

* Java 8
* [Maven 3](https://maven.apache.org/) - Gestión de dependencias

Dependencias con proyectos de arquitectura

* **architecture-testing** [1.0.0-SNAPSHOT] : Librería de arquitectura para testing utilizada en el ámbito de test
* **architecture-persistence** [1.0.0-SNAPSHOT] : Librería de arquitectura para persistencia

Dependencias terceros

* **mybatis** [3.5.0] : Framework de persistencia
* **mybatis-spring** [1.3.2] : Integrador MyBatis con Spring


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
