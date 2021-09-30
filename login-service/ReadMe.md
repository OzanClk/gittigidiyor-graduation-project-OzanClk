# Login Service

Login service and frontend were developed under the same project.Spring security of the spring boot framework was used for the login process.Authorization and authentication processes were carried out with Spring security.React.js framework was used for the frontend part.


<p align="center">
  <img src="https://user-images.githubusercontent.com/67712162/135362858-c9f5dedb-f9b7-4b5d-802d-484b25aed168.JPG" />
</p>





- User credentials are stored in the PostgreSQL database on RDS.



![RDSlogin](https://user-images.githubusercontent.com/67712162/135362870-b00cef1e-afb5-438c-b882-ed6cd0c6fe8f.JPG)



## Prerequisites

- Oracle JDK 8 and JRE 8 
- PostgreSQL
- SpringBoot 2.5.4
- Docker 
- React.js
- Node.js



## Installation

- Clone the repo

```java
  git clone https://github.com/113-GittiGidiyor-Java-Spring-Bootcamp/gittigidiyor-graduation-project-OzanClk.git
```

- To create network 

  ```java
  docker network create db
  ```

- Initialize a Postgres Docker on WINDOWS

  ```java
  docker run --name db -p 5432:5432 --network=db -v "%cd%:/var/lib/postgresql/data" -e POSTGRES_PASSWORD=password -d postgres:alpine
  ```

- To connect Postgres Docker Container via psql

  ```java
  run -it --rm --network=db postgres:alpine psql -h db -U postgres
  ```

- The following command is run under the frontend directory

  ```java
  npm install --save bootstrap@4.1.3 react-cookie@3.0.4 react-router-dom@4.3.1 reactstrap@6.5.0
  ```

- After the installations are completed

  ```java
  npm start
  ```

  

## Login Service Swagger Document

You can review the business rules developed within the scope of the project from the API document of this API project.

**<a href="http://frontendlogin-env.eba-fawzs9nr.eu-central-1.elasticbeanstalk.com/swagger-ui.html#/" rel="some text">Login Service API's </a>**

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact


![Foo](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)   --  ozancelikinfo@gmail.com

[![Foo](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/ozan-%C3%A7elik/)

[![Foo](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/OzanClk)
