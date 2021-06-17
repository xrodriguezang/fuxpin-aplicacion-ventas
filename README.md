# fuxpin-aplicacion-ventas

Application secured with Spring Security to Keycloak.

Once the credentials are validated, the authentication is successful. Keycloak then provides the user attributes and Spring Security puts them in the Principal Object.

Roles are defined in the Keycloak console administration. They are also finally sent to the app via Spring Security. Just before that, Spring Security connects to the Eureka Netflix server to get the roles of the legacy application through a microservice.

Eureka Netflix Server implements a Black Box Modernization using a layer of microservices. This layer provides any interaction with the old application.

# Related projects
+ [fuxpin-eureka-server](https://github.com/xrodriguezang/fuxpin-eureka-server) &#8594; Microservices Layer
+ [fuxpin-legacy-ventas-rol-microservic](https://github.com/xrodriguezang/fuxpin-legacy-ventas-rol-microservice) &#8594; Microservice that provides legacy roles
+ [fuxpin-cloud-config-server](https://github.com/xrodriguezang/fuxpin-cloud-config-server) &#8594; Provides application settings

# This project Uses

|Tecnology|Tecnology|
|---|---|
|<img src="https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg" width="200"></br>  |  <img src="https://upload.wikimedia.org/wikipedia/commons/2/29/Postgresql_elephant.svg" width="60"></br> |
|&#8594; Spring Boot</br>&#8594; Spring Cloud Config Server</br>&#8594; Spring Eureka Netflix Server</br>&#8594; Spring Data - JPA</br>&#8594; Spring Security|&#8594; *with* PostgreSQL|
| <img src="https://cdn.worldvectorlogo.com/logos/bootstrap-5-1.svg" width="70"> |  <img src="https://www.keycloak.org/resources/images/keycloak_logo_480x108.png" width="180"></br> |
|&#8594; Boostrap 5|&#8594; Integrated with Keycloak|
|<img src="https://www.thymeleaf.org/images/thymeleaf.png" width="70">|<img src="https://cdn.icon-icons.com/icons2/2107/PNG/512/file_type_light_gradle_icon_130462.png" width="70">
|&#8594; Thymeleaf|&#8594; builded with gradle|

# Environment variables

In Spring Boot task &#8594;  ***VM options*** define:
````
-Dspring.profiles.active=production|localhost 
-Deureka.user=user
-Deureka.password=password 
-Dkeystore.password=password 
-Dcloud.config.rest.user=user 
-Dcloud.config.rest.password=password 
-Dpostgresql.user=user 
-Dpostgresql.password=password
````


VM Options &#8594; *-Dspring.profiles.active=production|localhost*


# More info:

https://zetcode.com/springboot/postgresql/
https://gist.github.com/thomasdarimont/860a8a8420762c14d57766425b036c13