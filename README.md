# Drone Delivery App - gRPC-based microservices approach 

Online Shop prototype application based on microservices that follow the gRPC architectural style. This project is being developed in the scope of [ISEP's Master Thesis in Software Engineering](https://www.isep.ipp.pt/Course/Course/87) and aims to study the adoption of different approaches to communication between services in a microservice based application. This project is based on [this example](https://docs.microsoft.com/en-us/azure/architecture/microservices/model/domain-analysis).  

## Prerequisites

To follow these instructions the following is required:  
- [Docker](https://www.docker.com/)  
- [Docker Compose](https://docs.docker.com/compose/install/)  
- Make

## Getting Started

These instructions will give you a copy of the project up and running on your local machine for development and testing purposes.

### To run

`make start` or `docker-compose up`

**Note:** there must be a `.env` file with:  
- USER_ACCOUNT_DB_USERNAME  
- USER_ACCOUNT_DB_PASSWORD  
- DRONE_DB_USERNAME  
- DRONE_DB_PASSWORD

In this current setup, some users and drones are created upon creation of the containers.

### To access database

**MySQL:**  
1. `make connect-mysql`  
2. `mysql -u root -p`
3. `insert password`  
4. `USE {databaseName}`

**MongoDB:**  
1. `make connect-mongodb`  
2. `use {databaseName}`

### If failures happen when running `./mvnw dependency:go-offline -B`

Run `sed -i 's/\r$//' mvnw` in the folders with `mvnw` files

## License

This project is licensed under the [MIT License](LICENSE) - see the [LICENSE](LICENSE) file for further details.
