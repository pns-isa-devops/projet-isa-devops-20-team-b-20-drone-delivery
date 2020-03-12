# projet-isa-devops-20-team-b-20-drone-delivery

## Launch the tomee back-end

```sh
# clena and package each module
mvn clean package
# run tomee
cd ./web-services/
mvn tomee:run
# test the end-point
http://localhost:8080/drone-delivery-backend/webservices/DeliveryWS?wsdl
```
