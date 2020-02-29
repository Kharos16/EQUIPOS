###Contenedor PostgresSQL
Una vez en el directorio donde se encuentra el Dockerfile

 -      docker build -t postgreskarate .
        docker run -d --name "nombre del contenedor" -p 5432:5432 postgreskarate
 
Ahora con nuestro contenedor en marcha debemos entrar a su linea de comandos(CLI) con:

 -      docker exec -it "nombre del contenedor" bash
    
Si estas en windows es probable que tengas que usar winpty 
para conectarte con:

 -      winpty docker exec -it "nombre del contenedor" bash 

Una vez conectado a nuestro contenedor que es enrealidad un
linux alpine con postgres instalado debemos usar la linea de
comandos de postgres para crear la base de datos y luego llenarla
de informacion, esto se hace con los siguientes comandos:


 -      psql -U postgres
        CREATE DATABASE anfp;
        exit
        psql -U postgres -d anfp -f CREATE_TABLE_equipos.sql
        psql -U postgres -d anfp -f INSERT_INTO_equipos.sql

Para validar que los datos han sido ingresados correctamente
debemos conectarnos de nuevo al contenedor y abrir la linea 
de comandos de postgres conectarnos a la base de datos y validar
los datos, podemos hacer esto de la siguiente manera:

 -      psql -U postgres
        \connect anfp
        \dt
        SELECT * FROM equipos;
 
 Deberiamos con esto ver una lista con los datos previamente ingresados.

Para detener nuestro contenedor que tiene la base de datos:

 -     docker stop "nombre del contenedor"