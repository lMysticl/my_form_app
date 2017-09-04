Forum app
========
Have User and Admin roles.
Add create themes, comments, see time information.
Have registration and login.
Everything works with the database.
And this page is fully adaptable, for different screen sizes.

Posrtgres server SetUp
--------------------
Only need to isert  `role` into table after frist strat of appication!

INSERT INTO logiston.role VALUES (1,'ADMIN');
--------------------
INSERT INTO logiston.role VALUES (2,'USER');
--------------------
Also to set admin role need to make role_id = 1

Don't forget Change properties in application.properties (src\main\resources\application.yml)

username: your name
password: your password

Start app:
---------
You must run the command on the command line

If install Maven: mvn spring-boot:run

Without maven installed: mvnw spring-boot:run

Open in browser: http://localhost:8080

Tools that was used:
Spring Boot, 
Spring Security, 
Hibernate,
Postgres,
CSS, 
JavaScript, 
JQuery, 
BootStrap, 
thymeleaf, 
maven, 
jpa
