# Web Rest


## Run

* Run App from VSCode menu
  - EXPLORER > SPRING-BOOT DASHBOARD > select and start
  - EXPLORER > MAVEN PROJECTS > select project spring-boot:run
* Run Test from VSCode menu
  - TEST > select project > click Run Test icon
    - click "View test report" button at very bottom

* from CLI
```bash
cd demo-web-rest/
./mvnw test
./mvnw spring-boot:run
```

## Execute API

```bash
curl http://localhost:8080/api/customers -i -XGET
curl http://localhost:8080/api/customers -i -XGET | tail -1 | jq
curl "http://localhost:8080/api/customers?page=0&size=2" -i -XGET | tail -1 | jq

curl http://localhost:8080/api/customers/1 -i -XGET

curl http://localhost:8080/api/customers -i -XPOST \
    -H "Content-Type: application/json" \
    -d '{"firstName":"Hoge","lastName":"Ho"}'

curl http://localhost:8080/api/customers/1 -i -XPUT \
    -H "Content-Type: application/json" \
    -d '{"firstName":"Fuga","lastName":"Fu"}'

curl http://localhost:8080/api/customers/1 -i -XDELETE
```
