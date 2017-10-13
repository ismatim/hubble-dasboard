# HUBBLE

### Descripción

Integra datos estadísticos provinientes de aplicaciones de Ciclo de Vida, como por ejemplo: BSM, AppPulse, PPM, ALM, RUM, ServiceNow.

---

## Índice

1. [Ambientes](#ambientes)
2. [Proyectos](#proyectos)
3. [Code Coverage](#code-coverage)


### Ambientes

Configuración de ambientes DEV para pruebas locales y luego explicación de ejecución de pruebas en ambientes de TEST.

#### DEV (Instalación)

#### Configuraciones

##### Docker

Para ejecutar la aplicación desde contenedores se requiere declarar las siguientes variables de ambiente:

1. HUBBLE_FRONTEND_NAME: Nombre de la imágen.
2. HUBBLE_FRONTEND: Nombre del contenedor.
3. HUBBLE_FRONTEND_PORT: Número de puerto por ejemplo *"80:8080"*.
4. HUBBLE_MONGODB: Nombre del contenedor de mongo. Por convención es *mongodb-nombre-rama*
5. HUBBLE_MONGODB_VOL: Dirección de dónde se mapean los datos. Por ejemplo: */data/mongodb-nombre-rama/:/data/db/*
6. HUBBLE_TASKRUNNER: Nombre del contenedor.
7. HUBBLE_TASKRUNNER_NAME: Nombre de la imágen.


Los archivos Dockerfile se encuentran en:

1. /hubble/hubble.backend.tasksrunner/src/main/resources/docker/Dockerfile
2. /hubble/hubble.frontend.web/src/main/resources/docker/Dockerfile

Desde la ubicación del Dockerfile ejecutar:
1. `/hubble/hubble.backend.tasksrunner/src/main/resources/docker$ docker build . `

2. `/hubble/hubble.frontend.web/src/main/resources/docker$ docker build . `

Un ejemplo customizado de docker-compose.yml:

```yml
version: '3.0'

services:
   hubble-app:
       image: hubble/hubble.frontend.web
       container_name: hubble
       ports:
           - 8080
       hostname: hubble
       links:
           - mongodb:mongo

   mongodb:
       image: mongo
       container_name: mongodb
       ports:
           - 27017
       hostname: mongodb
       volumes:
           - /data/db/

   hubble-taskrunner:
       image: hubble/hubble.backend.tasksrunner
       container_name: hubble-taskrunner
       hostname: hubble-taskrunner
       stdin_open: true
       tty: true
       links:
           - mongodb:mongo
```


##### Linux
`$ export HUBBLE_FRONTEND_NAME="localhost:5000/hubble/hubble.frontend.web"`

Por último, se ejecuta el _docker-compose_ que armoniza los contenedores en línea.

`$ docker-compose up -d`

Para ver el log, de algún contenedor simplemente escribir el siguiente comando en la terminal.

`$ docker logs <Nombre de Contenedor>`

Para detener los contenedores se escribe en la terminal el comando

`$ docker-compose down`

##### MongoDB

Los datos obtenidos por los proveedores se guardan en la base de datos de Mongo. El nombre de la base de datos actualmente se llama *hubble-test*. No se necesitan datos para correr la aplicación, desde que se inicia tomara los datos para las siguientes aplicaciones:

1. applicationStorage
2. availabilityStorage
3. issueStorage
4. workItemStorage

```
$ docker run --name=mongo -v /data/mongodb:/data/db --detach -p 27017:27017 mongo
# para acceder al shell
$ sudo docker exec -it -u root mongo /bin/bash
```

La base de datos también se puede correr de manera local mientras esté conectado al puerto: 27017.

##### Ejecución de Backend.Tasksrunner

Alternativamente, se puede ejecutar el TasksRunner de maner de poder ir consumiendo datos en la base datos de MongoDB a medida de desarrollo en forma local.

```bash
$ cd hubble.backend.tasksrunner\target
$ mvn exec:java -Dexec.mainClass="hubble.backend.tasksrunner.application.TasksRunnerApplication" -Dmaven.test.skip=true
```
#### TEST
Para crear el ambiente de TEST de una rama específica se utiliza el Job de Jenkins: *Hubble-MultiBranch-TEST*

El Job se encarga de ejecutar un proceso en el que primero baja las fuentes de la rama, compila sin las pruebas, crea las imagenes de docker de la aplicación y luego las levanta. El Front-End va a usar un puerto aleatorio que esté disponible. A excepción de la rama *dev* que sí va a usar el puerto 80.
La imagen de mongo que se utiliza es la misma, es decir, sí no está creada con el nombre: _mongodb-branch-name_, éste proceso la crea. Apuntando al volúmen de datos: `/data/mongodb-nombre-rama/`

El número de versionado para la rama TEST: *NUMERO_BUILD.NOMBRE_DE_LA_RAMA*

## Proyectos

#### Convenciones Generales

* No permitir espacios en blanco al final de cada línea.
* No dejar comentarios innecesarios.
* El nombre de los métodos tiene que tener TOTAL significado y concordancia con lo que hace.
* Respetar los principios de desarrollo [SOLID](https://es.wikipedia.org/wiki/SOLID)
* Para el lenguaje JAVA respetar las siguientes convenciones: http://www.literateprogramming.com/javaconv.pdf
* Verificar que las pruebas corran en todos los proyectos.
* Pruebas de integración deben contemplar casos diversos de complejidad ciclomática.[Link](https://es.wikipedia.org/wiki/Complejidad_ciclom%C3%A1tica)
* Buena actitud de equipo.

### Backend

1. TasksRunner
2. Providers
3. Services
4. Storage


#### Proyecto Backend Storage 
-----------------------------

##### Convenciones
Usamos el prefijo `find` para los métodos que devuelven y obtienen datos. Para los que guardan datos usamos el prefijo `save`.

#### Proyecto Backend Services
-----------------------------

##### Convenciones
Usamos el prefijo `get` para obtener datos, ya que es el standard para las comunicaciones HTTP. Y usamos `set` para guardar los datos.

### Front-End

1. Business
2. Web

#### Business

El proyecto *frontend.business* contiene los modelos que conforman el negocio llamado *business*. El paquete de Business contiene la lógica de negocio que crea la vista del usuario, o sea, hace de la parte controladora de la vista. El paquete Views guarda las estructuras de los datos con los cuales se rellena la vista (html) que está en el proyecto *frontend.web*.

---

## Code Coverage
El servidor de integración continua, Jenkins, corre el programa Jacoco (http://www.eclemma.org/jacoco/) para evaluar la cobertura del desarrollo de Hubble. Se requiere una mínima cobertura del 20% para que el _maven_ permita compilarlo con satisfacción.

