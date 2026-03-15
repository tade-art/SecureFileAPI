# Secure File API
A Spring Boot REST API for secure file upload, download, and management with JWT authentication.

## Features
* User registration & login with JWT
* File upload/download/delete
* File metadata stored in H2 DB
* JWT-secured endpoints

## Requirements
* Java 17+
* Maven 3.8+
* cURL or Postman for testing

## Quick Start
1. Clone the repo:

```bash
git clone https://github.com/tade-art/SecureFileAPI.git
cd secure-file-api
```

2. Run with Maven:

```bash
mvn spring-boot:run -DSkipTests
```

3. Default endpoints:

| Endpoint             | Method | Notes                                                    |
| -------------------- | ------ | -------------------------------------------------------- |
| `/api/auth/register` | POST   | Body: `{"email":"random email","password":"random" }`    |
| `/api/auth/login`    | POST   | Returns JWT token                                        |
| `/api/files/upload`  | POST   | Header: `Authorization: Bearer <token>` + Multipart file |
| `/api/files/{id}`    | GET    | Download file by ID                                      |
| `/api/files/{id}`    | DELETE | Delete file by ID                                        |
| `/api/files`         | GET    | List all user files                                      |

5. Test with cURL:

```bash
# Register user - example
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{"email":"test@test.com","password":"123456"}'

# Login - example
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{"email":"test@test.com","password":"123456"}'

# Upload file - example
curl -X POST http://localhost:8080/api/files/upload \
-H "Authorization: Bearer <token>" \
-F "file=@/path/to/file.txt"

# View all files for a user - example
 curl -H "Authorization: Bearer <token>" \
 http://localhost:8080/api/files  

# Delete a file - example
 curl -X DELETE \
 -H "Authorization: Bearer <token>" \ 
 http://localhost:8080/api/files/<fileID>

```
---

## 3. `application.properties` (example)

```properties
# H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

---

## 4. Folder Structure

```
secure-file-api/
│
├─ src/main/java/com/tade/secure_file_api/
│  ├─ controller/        # REST endpoints
│  ├─ model/             # Entities
│  ├─ repository/        # JPA Repositories
│  ├─ security/          # JWT & Spring Security config
│  └─ service/           # Business logic
│
├─ src/main/resources/
│  ├─ application.properties
│
├─ .gitignore
├─ pom.xml
└─ README.md
```