# Thymeleaf starter

* Minimal Thymeleaf CRUD example using Layout and Parametrized Fragment
* Including updates for Spring Boot 2.2.0, Thymeleaf 3 and Bootstrap 4
* Endpoints are following Rails REST convention


## Run Server

```bash
cd demo-web-thymeleaf/
./mvnw spring-boot:run
```

__Open >>__ http://localhost:8080/customers


## Show Routes (Mappings)

```bash
./routes.sh
GET /customers
GET /customers/id/edit
POST /customers
POST /customers/id, params [_method=delete]
POST /customers/id, params [_method=put]
```


## Metrics

```bash
http://localhost:8080/actuator/metrics/
http://localhost:8080/actuator/metrics/jvm.memory.used
```


## Reference

* hajiboot-samples chapter03
  - https://github.com/making/hajiboot-samples/tree/master/chapter03
* Layout with thymeleaf on Spring Boot
  - https://medium.com/@omeryazir/layout-with-thymeleaf-on-spring-boot-b604a46e7265
* Working with Fragments in Thymeleaf - 4.3. Parametrized Fragments
  - https://www.baeldung.com/spring-thymeleaf-fragments
* Rails Routing from the Outside In - 2.2 CRUD, Verbs, and Actions
  - https://guides.rubyonrails.org/routing.html#crud-verbs-and-actions
* SpringBoot + JPA + Thymeleafで簡単なCRUDを作る②
  - https://qiita.com/ozaki25/items/3b348874b6db5ab4f04f
* Custom Validation MessageSource in Spring Boot
  - https://www.javadevjournal.com/spring-boot/spring-custom-validation-message-source/
