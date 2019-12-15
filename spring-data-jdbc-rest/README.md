# Spring Data JDBC + Spring Data Rest

* `Spring Data JDBC` is very simple and straightforward db access library.
  - It's not `Spring Data JPA`, not `jdbcTemplate` but it's in the middle.
  - `Spring Data JDBC`'s Design Choice - "Simplicity is King"
    - No Lazy Loading
    - No caching
    - No Proxies, No deferred flushing...
  - Check this video for details: https://www.youtube.com/watch?v=AnIouYdwxo0
* `Spring Data Rest` provides REST API out of the box without controllers.
* This is demo app of these use cases
  - Simple case (just Item)
  - One to Many (Person has Skill Set)
  - One to Many (Customer has Order List)
  - Many to Many (Books and Authors)


## Run REST API

```bash
mvn spring-boot:run -f "spring-data-jdbc-rest/pom.xml"

# list models
curl http://localhost:8080/
```


## Try REST API of One to Many (with Set)

Case: Person has Skill Set.

```bash
# Create one person
curl -i -X POST -H "Content-Type:application/json" \
    -d '{"name" : "Foo", "skills": []}' http://localhost:8080/persons
# curl http://localhost:8080/persons/1

# Create person with skills
curl -i -X POST -H "Content-Type:application/json" \
    -d '{"name": "Bar", "skills": [{"name": "Java"}, {"name": "Python"}]}' \
    http://localhost:8080/persons
# curl http://localhost:8080/persons/2

# List people
curl http://localhost:8080/persons

# PUT (Replace person and skills) will not work
# Spring Data Rest - PUT is not working for associated reference types?
# https://stackoverflow.com/questions/44117091
# curl -i -X PUT -H "Content-Type:application/json" \
#     -d '{"name": "Baz", "skills": [{"name": "Scala"}]}' \
#     http://localhost:8080/persons/1

# Update person name (skills will remain same)
curl -i -X PATCH -H "Content-Type:application/json" \
    -d '{"name": "Bilbo"}' http://localhost:8080/persons/1

# Update person and skills
curl -i -X PATCH -H "Content-Type:application/json" \
    -d '{"name": "Bilbo Jr.", "skills": [{"name": "Go"}]}' \
    http://localhost:8080/persons/1

# Delete person
curl -X DELETE http://localhost:8080/persons/1

# Search people
curl "http://localhost:8080/persons/search/findByName?name=Foo"
```


## Reference

* Official docs, blogs and examples
  - https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#reference
  - https://spring.io/blog/2018/09/17/introducing-spring-data-jdbc
  - https://spring.io/blog/2018/09/24/spring-data-jdbc-references-and-aggregates
  - https://github.com/spring-projects/spring-data-examples/tree/master/jdbc
* YouTube videos
  - Spring Academy - Spring Data JDBC video series
    - https://www.youtube.com/watch?v=EaHlancPA14
  - The New Kid on the Block: Spring Data JDBC
    - https://www.youtube.com/watch?v=AnIouYdwxo0
* Spring Data JDBC 101
  - https://blog.tagbangers.co.jp/2019/04/02/spring-data-jdbc-101
* Spring Data REST intro
  - https://www.baeldung.com/spring-data-rest-intro
* GETTING STARTED Accessing JPA Data with REST
  - https://spring.io/guides/gs/accessing-data-rest/
* OneToMany Spring Data JDBC
  - https://stackoverflow.com/questions/53376101
