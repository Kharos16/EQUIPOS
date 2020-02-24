### Repositorio Spring/Karate 
Este repositorio esta dado para demostrar la capacidad del framework
Karate (https://github.com/intuit/karate) para la automatizacion de pruebas
para APIs REST/SOAP.

El repositorio contiene una pequeña aplicacion hecha con
Spring Boot 2.2.4 con la cual se disponibilizan los Endpoint
a utilizar para las pruebas.

Tambien se disponibiliza un Dockerfile con el cual se puede 
inicializar la base de datos para generar persistencia para
la aplicacion Spring Boot.

Para inicializar el contenedor debes tener Docker instalado
puede ser en su version de Windows o la de Linux.

Las credenciales para la base de datos que es una BDD Postgres son:

 - Usuario: postgres
 - Password: Welcome

Una vez en el directorio donde se encuentra el Dockerfile

 - "docker build -t postgreskarate ."
 - "docker run -d --name "nombre del contenedor" -p 5432:5432
 postgreskarate"
 
Para detenerlo

 - docker stop "nombre del contenedor"