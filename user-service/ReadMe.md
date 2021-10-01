# User Service



Users defined as admin can add, update and delete users to the system through this service.All users registered to the registered system are displayed in the portal section of the application.PostgreSQL, which is one of the relational databases, was used as the database. The data from the user were saved in the database on RDS.

- Validation processes have been carried out for the fields to be filled by the customer.




<p align="center">
  <img src="https://user-images.githubusercontent.com/67712162/135620005-c28fffd3-66a6-43ac-83e5-3834cb0f6a4e.JPG" />
</p>




- You can see the users registered to the system by the admins through this portal.



![Capture2](https://user-images.githubusercontent.com/67712162/135355478-73668044-4464-4b1f-90d1-243dbd7bc92e.JPG)

## Prerequisites

- Oracle JDK 8 and JRE 8 
- PostgreSQL Database
- SpringBoot 2.5.4
- Docker 



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



## User Service Swagger Document

You can review the business rules developed within the scope of the project from the API document of this API project.

**<a href="http://userservice-env.eba-qbg3p8bg.eu-central-1.elasticbeanstalk.com/swagger-ui.html" rel="some text">--  User Service API's </a>**

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact


![Foo](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)   --  ozancelikinfo@gmail.com

[![Foo](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/ozan-%C3%A7elik/)

[![Foo](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/OzanClk)
