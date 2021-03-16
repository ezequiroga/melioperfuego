# Operacion Fuego de Quasar

## Estructura de la solucion

La solucion cuenta con dos servicios conectados mediante el *docker-compose.yaml*

- **centralinteligencia**: servicio expuesto para recibir peticiones.
- **satelites**: servicio utlizado para gestionar la informacion de los satelites

## Documentacion de la API

API docs http://DOMINIO/api/api-docs

## Correr la aplicacion

Para correr la app de forma local:

1. Clonar el repositorio GIT https://github.com/ezequiroga/melioperfuego.git
2. Levantar la aplicacion corriendo `docker-compose up --build -d`
