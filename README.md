Weather rest api application
---

Welcome to the Weather rest api application!

### Introduction

This is a simple application that requests its data from [OpenWeather](https://openweathermap.org/) and stores the result in a database. 

Weather rest client application

### Dev Installation 

1) This project uses `postgresql 9` as the database. You can install a docker image with it by running `cd postgresql-docker && docker-compose up -d` and then creating the db: `./create-db.sh`
3) Since it's a bad practice to save keys in plain text in a public repo, my OpenWeather key was not commited. You can add a valid one in `application-dev.yml` file under `openWeather.appId`. If you wish to run the tests, repeat this procedure for `application.yml` in the `test/` directory. 
4) Run `./mvnw clean install`. This will also run the tests (to skip them, add `-Dmaven.test.skip=true`).
5) To run the project from the command line, `./mvnw spring-boot:run -Dspring.profiles.active=dev`. The last parameter is important since there are a few configuration values that are only set on an environment basis (even though the `dev` one was the only created)
6) This will start a Tomcat Application WebServer by in default in localhost, port 8080. [Example request for Amsterdam](localhost:8080/weather?city=amsterdam)
7) To cleanup the docker container from step 2), you can stop it and remove its volume with the command: `./remove-db.sh`

### Installation on application servers 
If installing the application outside of your own environment then:
1) Use one of the corresponding property files and fill these with the correct values or add an new property file yourself. 
2) Run `./mvnw clean install`. This will also run the tests (to skip them, add `-Dmaven.test.skip=true`).
3) Get the Jar file, install it on a application server and run the jar with the desired profile. 
   For instance `java -jar -Dspring.profiles.active=test <jar-file>`.
   
   Note: the profile is important for the selection of the properties file. A 'test' profile will use the application-test.yml file.

### Footnote API KEY
It's possible to generate the API key going to the [OpenWeather Sign up](https://openweathermap.org/appid) page. Consider to create a key for any environment u use. Never save the key in your source code repository!