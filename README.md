# Ceng453_TermProject_Group8_backend
Ceng453 Monopoly game backend

We use docker to generate our server and database. There are two docker containers, one is for the mariadb database and the other is for the backend server.

We use docker because it can easily be scaled up and run anywhere.

Database Diagram:
![Database Diagram](./docs/diagrams/database_schema.png)


**How to Run:**

- change directory into monopolyserver directory
```
cd monopolyserver
```

- Give runnable privileges to setup docker script
```
chmod +x setup_mariadb_docker.sh
```

- Run the setup script
```
./setup_mariadb_docker.sh
```

- Register one user 

```
curl --location --request POST 'http://localhost:8080/api/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "swagger",
    "password": "swagger",
    "email" : "mockswagger@gmail.com"
}'
```

**Swagger Api documentation** 

Api can be found at http://localhost:8080/swagger-ui/index.html

Our backend requires http basic authentification even for swagger. For development purposes a default username and password is set
username: swagger
password: swagger


- Deprecated
Since heroku limits its free dynos swagger documentation at heroku is deprecated 
Swagger api documentation can be found at
https://ceng453-term-project-group8.herokuapp.com/swagger-ui/index.html

