# Operacion Fuego de Quasar

## Estructura de la solucion

La solucion cuenta con dos servicios conectados:

- **centralinteligencia**: servicio core (puerto 9090) expuesto (solucion Docker) para recibir peticiones.
- **satelites**: servicio (puerto 9191) utlizado para gestionar la informacion de los satelites.

El archivo *application.properties* del **centralinteligencia** contiene la url del servicio de satelites. Ese valor se espera por parametro. El default es `http://localhost:9191/api/satelites`

## Documentacion de la API

API docs http:// \{DOMINIO\} | \{IP:PUERTO\} /api/api-docs

## Build de la aplicacion

Antes de correr la aplicacion es necesario compilar cada servicio. Para esto, estando en la raiz del proyecto, ejecutar desde una terminal:
1. `cd centralinteligencia`
2. `mvn clean install`
3. `cd ..`
4. `cd satelites`
5. `mvn clean install`

## Correr la aplicacion

Para correr la app de forma local, clonar el repositorio GIT `https://github.com/ezequiroga/melioperfuego.git`

Para este paso es necesario haber compilado los servicios

### Con contenedores - Docker

Estando en la raiz del proyecto, ejecutar desde una terminal `docker-compose up --build -d`

### Sin conetenedores - Java sobre host

Estando en la raiz del proyecto:
1. Ejecutar `java -jar satelites/target/satelites-0.0.1-SNAPSHOT.jar`
2. Ejecutar `java -jar centralinteligencia/target/operfuego-0.0.1-SNAPSHOT.jar`

## TODO para version productiva
- Seguridad en la API mediante JWT
- Manejo de excepciones en todas las comunicaciones entre los servicios.