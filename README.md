# REST-API-Delivery-Order #

Based on the customer and product registration, it generates an order. The order will be sent to the delivery queue (RabbitMQ), after the message received, it will be saved in the Delivery table.

### How do I get set up? ###

* need rabbitmq being installed locally
* mvn clean install
* mvn spring-boot:run

### Contribution guidelines ###

* Rabbit-MQ
* Rest-api
* Unit Test
* Spring Data JPA
* Swagger 2
