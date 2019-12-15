# Spring Security Starter


## Overview

* 2 kind of library
  - Using Spring Security + Spring Data JDBC
  - Updated for the current version thymeleaf-extras-springsecurity5 (3.0.4.RELEASE)
* 2 type of pages (as per official example)
  - Main page http://localhost:8080/ is open to public.
  - Hello page http://localhost:8080/hello is login required.


## Run

* `mvn spring-boot:run`
* web page: http://localhost:8080/
* h2 console: http://localhost:8080/h2-console > JDBC URL: `jdbc:h2:mem:testdb`


## Reference

__Spring Security__

* Spring Security Official
  - https://spring.io/guides/gs/securing-web/
* Spring Boot Security Basics
  - https://www.youtube.com/watch?v=krLrHwRvTHc
* thymeleaf-extras-springsecurity5
  - https://github.com/thymeleaf/thymeleaf-extras-springsecurity

__Spring JDBC__

* Spring Data JDBC Official
  - https://spring.io/blog/2018/09/17/introducing-spring-data-jdbc
  - https://docs.spring.io/spring-data/jdbc/docs/1.1.1.RELEASE/reference/html/#reference
* Spring Boot + Spring Data JDBC
  - https://qiita.com/yoshikawaa/items/c25715df81ba0d18a74f
  - Spring Data JDBC (NOT Spring Data JdbcTemplate, NOT Spring Data JPA)
