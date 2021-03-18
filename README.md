# Operacion Fuego de Quasar

## 1. Estructura de la solucion

La solucion cuenta con dos servicios conectados:

- **centralinteligencia**: servicio core (puerto 9090) expuesto (solucion Docker) para recibir peticiones.
- **satelites**: servicio utlizado para gestionar la informacion de los satelites (puerto 9191). Este servicio utiliza una base en memoria (a lo fines del ejercicio) para gestionar la informacion de los satelites. En el archivo *import.sql* se encuentran los script que cargan los datos de las posiciones iniciales de los satelites.

El archivo *application.properties* del **centralinteligencia** contiene la url del servicio de satelites. Ese valor se espera por parametro. El default es `http://localhost:9191/api/satelites`

## 2. AWS

La api esta disponible en AWS: 

[http://ec2-15-228-28-170.sa-east-1.compute.amazonaws.com:9090/api/api-docs](http://ec2-15-228-28-170.sa-east-1.compute.amazonaws.com:9090/api/api-docs)

## 3. Correr la aplicacion

### 3a. Clonar repo GIT

Para correr la app de forma local, clonar el repositorio GIT `https://github.com/ezequiroga/melioperfuego.git`

### 3b. Build de la aplicacion

Antes de correr la aplicacion es necesario compilar cada servicio. Para esto, estando en la raiz del proyecto, ejecutar desde una terminal:
1. `cd centralinteligencia`
2. `mvn clean install`
3. `cd ..`
4. `cd satelites`
5. `mvn clean install`

### 3c. Con contenedores - Docker

Estando en la raiz del proyecto, ejecutar desde una terminal `docker-compose up --build -d`

Para este paso es necesario haber compilado los servicios.

### 3d. Sin conetenedores - Java sobre host

Estando en la raiz del proyecto:
1. Ejecutar `java -jar satelites/target/satelites-0.0.1-SNAPSHOT.jar`
2. Ejecutar `java -jar -Dsatelites=http://localhost:9191/api/satelites centralinteligencia/target/operfuego-0.0.1-SNAPSHOT.jar` (NOTA: si el parametro no se pasa, el valor por defecto es *http://localhost:9191/api/satelites*)

Para este paso es necesario haber compilado los servicios.

## 4. Documentacion de la API

API docs http:// \{DOMINIO\} | \{IP:PUERTO\} /api/api-docs

## TODO para version productiva
- Seguridad en la API mediante JWT.
- Manejo de excepciones en todas las comunicaciones entre los servicios.
- Completar los test en *centralinteligencia* para todas las comunicaciones.
- Utilizar una base de datos externa.
- Modificar la documentacion de la API.