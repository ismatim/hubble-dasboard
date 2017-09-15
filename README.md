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


## Configuraciones

### Docker

Para ejecutar el docker-compose file se escribe en la terminal de preferencia.

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
