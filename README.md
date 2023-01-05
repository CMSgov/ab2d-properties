# AB2D Properties Service

This service is used to save runtime variables for services to access. Unlike an envrionment variable, these
values can be updated at runtime and shared across services. Individual application code
should retrieve the values of these properties at any time, allowing them to change
their behaviour across the system or coordinate services. 

Examples include the status of BFD. If one service detects BFD is down, it can update a value indicating this. This prevents
other services from querying BFD or creating a backlog of tasks whith no ability to satisfy them. Another example is coordinating scaling. 

The interface provides the following endpoints:

***GET /properties***

- Lists the existing properties (key and value)

***GET /properties/{key}***

- Return a specific property using it's key

***DELETE /properties/{key}***

- Delete a property using it's key

***POST /properties?key={key}&value={value}***

- Create a property given a key and value

***GET /health***

- Returns a status code of 200 if this service can connect to the database and the internet

## Running the application

This application is a spring boot application which depends on a configurable database connection defined in the 
[application.properties](https://github.com/CMSgov/ab2d-properties/blob/enhancement/ab2d-documentation_enhancement/src/main/resources/application.properties) file. This application also uses liquibase to create
the tables in the database at startup and provide DB versioning.

To create the spring boot jar:

```gradle -b build.gradle bootJar```

To execute the jar:

```java -jar properties.jar``` (or whatever the built jar's name is)
