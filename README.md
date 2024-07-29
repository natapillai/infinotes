
# Infinotes

[![Java](https://img.shields.io/badge/Java-11-blue.svg)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Hibernate](https://img.shields.io/badge/Hibernate-5.4.32-brightgreen.svg)](https://hibernate.org/)
[![AWS](https://img.shields.io/badge/AWS-Elastic%20Beanstalk-orange.svg)](https://aws.amazon.com/elasticbeanstalk/)

Infinotes is a scalable note-taking platform developed using Java Spring Boot with MVC architecture. The platform supports profile customization and CRUD operations on notes and contacts.

## Features

- **Profile Customization**: Users can personalize their profiles.
- **CRUD Operations**: Create, read, update, and delete notes and contacts.
- **Spring Security Integration**: Ensures application integrity with custom authentication, role-based access control, and robust session management.
- **DAO Patterns**: Utilizes Hibernate ORM for efficient data integration and manipulation.
- **AWS Deployment**: Deployed on AWS using Elastic Beanstalk for ease of management and CI/CD capabilities.

## Technologies Used

- **Back-End**: Java, Spring Boot, Hibernate ORM
- **Front-End**: Thymeleaf
- **Database**: Amazon RDS, Amazon S3
- **Security**: Spring Security
- **Deployment**: AWS Elastic Beanstalk

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- AWS Account (for RDS and S3 setup)

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/natapillai/infinotes.git
    ```

2. Set up the database on Amazon RDS and configure Amazon S3 for file storage.

3. Update the `application.properties` file with your database and AWS credentials.

4. Build the project using Maven:
    ```bash
    mvn clean install
    ```

5. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## Deployment

Infinotes is deployed on AWS Elastic Beanstalk. Follow these steps to deploy your version:

1. Create an Elastic Beanstalk environment in the AWS Management Console.
2. Deploy the application using the Elastic Beanstalk CLI or directly from the console.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

<!-- ## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details. -->

## Contact

Natarajan Lekshmi Narayana Pillai - [natapillai.app@gmail.com](mailto:natapillai.app@gmail.com)

LinkedIn: [linkedin.com/in/natapillai](https://www.linkedin.com/in/natapillai)

GitHub: [github.com/natapillai](https://github.com/natapillai)

<!-- ## Acknowledgments

- Special thanks to the developers of Spring Boot and Hibernate for their powerful frameworks.
- Thanks to AWS for providing scalable deployment solutions. -->
