# AB2D Properties Service

This service is used to save runtime variables which individual services may access
to determine the current state of services, access to BFD or parameters for things
like number of concurrent threads to create. Each service can update this at the same
time with the same information. The interface provides the following endpoints:

GET /properties

- Lists the existing properties (key and value)

GET /properties/{key}

- Return a specific property using it's key

DELETE /properties/{key}

- Delete a property using it's key

POST /properties?key={key}&value={value}

- Create a property given a key and value

GET /health

- Returns a status code of 200 if this service can connect to the database and the internet
