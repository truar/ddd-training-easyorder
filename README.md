# Easyorder

Application developed during the DDD training with 6 students.

## MongoDB
To start MongoDB, use docker:
```shell script
docker run -p 27017:27017 --name mongo \
    -e MONGO_INITDB_ROOT_USERNAME=admin \
    -e MONGO_INITDB_ROOT_PASSWORD=admin \
    -e MONGO_INITDB_DATABASE=easyorder \
    mongo:4.4.5
```

Log into the container and add a new user:
```shell script
docker exec -it mongo bash
mongo -u admin
use easyorder
db.createUser({user: "user", pwd: "user", roles : [{role: "readWrite", db: "easyorder"}]});
```

## RabbitMQ
Start a RabbitMQ with Docker:
```shell script
docker run -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:3-management
```

## Compile and Run the application
```shell script
./mvnw clean install
```

* All Inmemory (inmemory database & spring event publisher)
```shell script
java -Dspring.profiles.active=inmemory -jar target/application-0.0.1-SNAPSHOT.jar
```

* Mongo & Rabbit
```shell script
java -Dspring.profiles.active=mongo,rabbit -jar target/application-0.0.1-SNAPSHOT.jar
```
