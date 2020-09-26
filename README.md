##This is a chat application with google oauth2

The application is running by its own inside a docker container.
We have a docker-compose file just for make it easier to start the app and for 
future extendability 

## Start application
### With maven
./mvnw spring-boot:run
#### avalilable: http://localhost:8080/

### With docker
./mvnw clean install

docker-compose up --build
#### avalilable: http://localhost

## About contribution

For now our only goal with this project is to share my solutions, so we are not looking for contributors.
Contribution could be an option in the future, but currently it is not.