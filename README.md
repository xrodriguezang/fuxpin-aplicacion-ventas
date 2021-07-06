# fuxpin-aplicacion-ventas

Application secured with Spring Security integrated with Keycloak.

Once the credentials are validated, the authentication is successful. Keycloak then provides the user attributes and Spring Security puts them in the Principal Object.

Roles are defined in the Keycloak console administration. They are also finally sent to the app via Spring Security. Just before that, Spring Security connects to the Eureka Netflix server to get the roles of the legacy application through a microservice.

Eureka Netflix Server implements a Black Box Modernization using a layer of microservices. This layer provides any interaction with the old application.

#Url

https://tfc.dnsnet.info:8444/ventas/user-profile

# Related projects
+ [fuxpin-eureka-server](https://github.com/xrodriguezang/fuxpin-eureka-server) &#8594; Microservices Layer
+ [fuxpin-cloud-config-server](https://github.com/xrodriguezang/fuxpin-cloud-config-server) &#8594; Provides application settings
+ [fuxpin-legacy-ventas-rol-microservice](https://github.com/xrodriguezang/fuxpin-legacy-ventas-rol-microservice) &#8594; Microservice that provides legacy roles
+ [fuxpin-legacy-ventas-client-microservice](https://github.com/xrodriguezang/fuxpin-legacy-ventas-client-microservice) &#8594; Microservice that provides legacy clients

# This project Uses

|Tecnology|Tecnology|Tecnology|
|---|---|---|
|<img src="https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg" width="200"></br>  |  <img src="https://upload.wikimedia.org/wikipedia/commons/2/29/Postgresql_elephant.svg" width="60"></br> | <img src="https://cdn.worldvectorlogo.com/logos/bootstrap-5-1.svg" width="70">|
|&#8594; Spring Boot</br>&#8594; Spring Cloud Config Server</br>&#8594; Spring Eureka Netflix Server</br>&#8594; Spring Data - JPA</br>&#8594; Spring Security|&#8594; *with* PostgreSQL|&#8594; Boostrap 5|
|  <img src="https://www.keycloak.org/resources/images/keycloak_logo_480x108.png" width="180"></br> |<img src="https://www.thymeleaf.org/images/thymeleaf.png" width="70">|<img src="https://cdn.icon-icons.com/icons2/2107/PNG/512/file_type_light_gradle_icon_130462.png" width="70">|
|&#8594; Integrated with Keycloak|&#8594; Thymeleaf| builded with Gradle|

# Application roles
The application has two main roles:

+ admin &#8594; provided by microservices Layer - Legacy application (fuxpin-legacy-ventas-rol-microservice)
+ user &#8594; provided by keycloak

An user that has has admin role can view:

<img src="https://raw.githubusercontent.com/xrodriguezang/fuxpin-aplicacion-ventas/main/src/main/resources/imageDocs/roleAdmin.PNG" width="800">

An user that has has admin user can view:

<img src="https://raw.githubusercontent.com/xrodriguezang/fuxpin-aplicacion-ventas/main/src/main/resources/imageDocs/roleUser.PNG" width="800">

View that shows the legacy clients. Only admin user can see this page:

<img src="https://raw.githubusercontent.com/xrodriguezang/fuxpin-aplicacion-ventas/main/src/main/resources/imageDocs/adminActionClients.PNG" width="800">

When an user with only role user acces to he before view:

<img src="https://raw.githubusercontent.com/xrodriguezang/fuxpin-aplicacion-ventas/main/src/main/resources/imageDocs/notAllowed.PNG" width="800">

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
# *Serverless* execution

* default (localhost):

``C:\Users\amgri\.jdks\jdk-11.0.7\bin\java -jar -Dspring.profiles.active=localhost -Deureka.user=user -Deureka.password=password -Dkeystore.password=password -Dcloud.config.rest.user=user -Dcloud.config.rest.password=password -Dpostgresql.user=user -Dpostgresql.password=password .\ventas-0.0.1.war``

* production:

``C:\Users\amgri\.jdks\jdk-11.0.7\bin\java -jar -Dspring.profiles.active=production -Deureka.user=user -Deureka.password=password -Dkeystore.password=password -Dcloud.config.rest.user=user -Dcloud.config.rest.password=password -Dpostgresql.user=user -Dpostgresql.password=password .\ventas-0.0.1.war``

````
$ gradew clean assemble
````
jar location
````
${PROJECT_DIRECTORY}/build/libs/
````

## Create a Run Java Jar Application with Systemd
* For this configuration *pi* user is used to run the *serveless* installation. Before proced, create a directory:
````
/opt/java-jar 
````
Give the user ang group ownership permissions for the Fuxpin Systems Jars:
````
sudo chown -R pi:pi /opt/java-jars 
````
* Create Systemd Service
````
sudo vi /etc/systemd/system/aplicacionventas.service
````
with contents:
````editorconfig
[Unit]
Description=Fuxpin Legacy Ventas Role Microservice

[Service]
WorkingDirectory=/opt/java-jars
ExecStart=java -Xms128m -Xmx256m -jar -Dspring.profiles.active=production -Deureka.user=user -Deureka.password=password -Dkeystore.password=password -Dcloud.config.rest.user=user -Dcloud.config.rest.password=password -Dpostgresql.user=user -Dpostgresql.password=password fuxpin-aplicacionventas-0.0.1.war
User=pi
Type=simple
Restart=on-failure

[Install]
WantedBy=multi-user.target
````

* Before start the application, reload systemd so that it knows ot the new service added:

````
sudo systemctl daemon-reload
````
* Once realoaded, the service is avaliable:

````
sudo systemctl start aplicacionventas
````
* To stop the service-application:

````
sudo systemctl stop aplicacionventas
````

* To restart the service-application:

````
sudo systemctl restart aplicacionventas
````

* To enable the service on startup server boot:
````
sudo systemctl enable aplicacionventas
````

# More info:

https://zetcode.com/springboot/postgresql/
https://gist.github.com/thomasdarimont/860a8a8420762c14d57766425b036c13