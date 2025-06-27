This Project contains the assessment.

After the deployment of the compose.yml file through docker, connect to the Postgres Db and run the tables.sql (located at the resourses of OrderManagement)
Next, connect to MongoDb database and at the admin database create a collection with the name "orders"

After the deployment of both microservices you can call the following APIs

Get Order
Type: GET
url: http://localhost:8080/orders/{id}
parameter: id= the id of the order

Place Order
Type: POST
url: http://localhost:8090/orders
parameter: (application/json)

{
  "customerName": "XXXXXXXX",
  "orderDate": "YYYY-MM-ddTHH:mm:ssZ",
  "orderLines": [
      {
      "productId": XXXX,
      "quantity": XXX,
      "price": XXX.XX
      },
      ....
	]
}

Update Order
Type: PUT
url: http://localhost:8090/orders/{id}
parameters: id= the id of the order
(application/json)

{
  "customerName": "XXXXXXXX",
  "orderDate": "YYYY-MM-ddTHH:mm:ssZ",
  "orderLines": [
      {
      "productId": XXXX,
      "quantity": XXX,
      "price": XXX.XX
      },
      ....
	]
}

Delete Order
Type: DELETE
url: http://localhost:8080/orders/{id}
parameter: id= the id of the order
