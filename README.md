# Challenge_meli Spring Boot, MySQL, JPA, Hibernate Rest API

Restful CRUD API for a simple application using Spring Boot, Mysql, JPA and Hibernate.

Requirements
Java 11 (SE Development Kit 11.0.12 (JDK 11.0.12))

Maven - 3.6.3

Mysql - 5.x.x

Steps to Setup
1. Clone the application

git clone https://github.com/PabloGuasco-dev/challenge_meli.git

2. Build and run the app using maven

mvn package
java -jar target/challenge_2021-0.0.1-SNAPSHOT.jar
Alternatively, you can run the app without packaging it using -

mvn spring-boot:run

The app will start running at http://localhost:8080

Explore Rest APIs
The app defines following CRUD APIs.

POST /topsecret

POST /topsecret_split/{satellite_name}

GET /topsecret_split

Examples

POST -> /topsecret
{
    "satellites": [
        {
            "name" : "kenobi",
            "distance" : 100.0,
            "message" : ["","este","es","un","mensaje"]
        },
        {
            "name" : "skywalker",
            "distance" : 115.5,
            "message" : ["este","","un","mensaje"]
        },
        {
            "name" : "sato",
            "distance" : 142.7,
            "message" : ["","","es","","mensaje"]
        }
    ]
}

POST -> /topsecret_split/kenobi
{
    "distance" : 142.7,
    "message" : ["","","es","","mensaje"]
}

GET -> /topsecret_split

Response 
{
    "position": {
        "x": -487.4977,
        "y": 1574.9954
    },
    "message": "es  mensaje"
}
