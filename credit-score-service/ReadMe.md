# Credit Score Service

- After entry into the system with the user information, the profile screen opens.On the screen are the token assigned to the user, the user role and the email address.



![Credit-service](https://user-images.githubusercontent.com/67712162/135358811-998c986a-adf3-4922-846c-d3227016421e.JPG)



- With the information entered by the user, the credit result is shown to the user and the credit limit is assigned.If the credit application is approved, the result is CONFIRM, and if it is not approved, the result is REFUSE.
<p align="center">
  <img src="https://user-images.githubusercontent.com/67712162/135359542-f1be1294-fabf-4314-8e76-7f8c715bb174.JPG" />
</p>

![Credit-Service2](https://user-images.githubusercontent.com/67712162/135358846-04bd5708-2976-4237-be18-d13b518fe18b.JPG)

## Cloud MongoDB Atlas

Within the scope of the project, MongoDB, which is a NoSQL database, was used for this service.Application and result information of users are kept on MongoDB atlas.



![CreditServiceMonga](https://user-images.githubusercontent.com/67712162/135359630-5f0d575a-ee1e-40da-a82b-96c0bcbe3343.JPG)





## Prerequisites

- Oracle JDK 8 and JRE 8 
- MongoDB
- SpringBoot 2.5.4
- Docker 



## Installation

- Clone the repo

```java
  git clone https://github.com/113-GittiGidiyor-Java-Spring-Bootcamp/gittigidiyor-graduation-project-OzanClk.git
```

- Create a compose yaml file in the project directory and paste the following codes into it.

  ```java
  version: "3.8"
  services:
    mongodb:
      image: mongo
      container_name: mongodb
      ports:
        - 27017:27017
      volumes:
        - data:/data
      environment:
        - MONGO_INITDB_ROOT_USERNAME=rootuser
        - MONGO_INITDB_ROOT_PASSWORD=rootpass
    mongo-express:
      image: mongo-express
      container_name: mongo-express
      restart: always
      ports:
        - 8081:8081
      environment:
        - ME_CONFIG_MONGODB_ADMINUSERNAME=rootuser
        - ME_CONFIG_MONGODB_ADMINPASSWORD=rootpass
        - ME_CONFIG_MONGODB_SERVER=mongodb
  volumes:
    data: { }
  
  networks:
    default:
      name: mongodb_network
  ```

- Then run the following code via terminal in the docker compose up directory

  ```java
  docker compose up
  ```



- Then you can run the code by making the edits under application properties.



## Credi Score Service Swagger Document

You can review the business rules developed within the scope of the project from the API document of this API project.

**<a href="http://creditscoreservice-env.eba-mxjvnm78.eu-central-1.elasticbeanstalk.com/swagger-ui.html" rel="some text">Credit Score Service API's </a>**

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact


![Foo](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)   --  ozancelikinfo@gmail.com

[![Foo](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/ozan-%C3%A7elik/)

[![Foo](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/OzanClk)
