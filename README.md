# HUBBLE


## Descripción

Integra datos estadísticos de diferentes aplicaciones Ciclo de Vida.

## Indice

## Convenciones

### Proyecto Backend Storage 
-----------------------------

Usamos el prefijo `find` para los métodos que devuelven y obtienen datos. Para los que guardan datos usamos el prefijo `save`.

### Proyecto Backend Services
-----------------------------

Usamos el prefijo `get` para obtener datos, ya que es el standard para las comunicaciones HTTP. Y usamos `set` para guardar los datos.


## Ambientes

### DEV (DEFAULT)


### TEST
Para crear el ambiente de TEST de una rama específica se utiliza el Job de Jenkins: *Hubble-MultiBranch-TEST*

El Job se encarga de ejecutar un proceso en el que primero baja las fuentes de la rama, compila sin las pruebas, crea las imagenes de docker de la aplicación y luego las levanta. El Front-End va a usar un puerto aleatorio que esté disponible. A excepción de la rama *dev* que sí va a usar el puerto 80.
La imagen de mongo que se utiliza es la misma, es decir, sí no está creada con el nombre: _mongodb-branch-name_, éste proceso la crea. Apuntando al volúmen de datos: `/data/mongodb-nombre-rama/`

## Configuraciones

### Docker

Para ejecutar el docker-compose file se escribe en la terminal de preferencia. Para esto se requiere declarar las siguientes variables de ambiente:

1. HUBBLE_FRONTEND_NAME: Nombre de la imágen.
2. HUBBLE_FRONTEND: Nombre del contenedor.
3. HUBBLE_FRONTEND_PORT: Número de puerto por ejemplo *"80:8080"*.
4. HUBBLE_MONGODB: Nombre del contenedor de mongo. Por convención es *mongodb-nombre-rama*
5. HUBBLE_MONGODB_VOL: Dirección de dónde se mapean los datos. Por ejemplo: */data/mongodb-nombre-rama/:/data/db/*
6. HUBBLE_TASKRUNNER: Nombre del contenedor.
7. HUBBLE_TASKRUNNER_NAME: Nombre de la imágen.

`$ docker-compose up -d`

Para ver el log, de algún contenedor simplemente escribir el siguiente comando en la terminal.

`$ docker logs <Nombre de Contenedor>`

Para detener los contenedores se escribe en la terminal el comando

`$ docker-compose down`

### MongoDB

### Jenkins


## Proyectos

### Backend

1. TasksRunner
2. Providers
3. Services


### Front-End

1. Managers
2. Web

#### Managers

En el proyecto *frontend.managers* contiene dos paquetes principales los *managers* y las vistas *views*. El paquete de Managers contiene la lógica de negocio que crea la vista del usuario, o sea, hace de la parte controladora de la vista. El paquete Views guarda las estructuras de los datos con los cuales se rellena la vista (html) que está en el proyecto *frontend.web*.



## Code Coverage
TODO
