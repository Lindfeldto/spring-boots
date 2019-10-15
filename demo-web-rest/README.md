# Web Rest


## Run

```bash
cd demo-web-rest/
mvn spring-boot:run
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
