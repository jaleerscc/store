# Shopping Cart App

## About

* Demo app to show a sample shopping cart implementation
* Uses spring jpa and spring rest
* Application starts with two items already added to store.

## App Config

### Pre-requisites

* Java version 1.8 or higher
* Maven version 3.6 or higher

### Build
Clone the code from repository
```
mvn clean install
```

### Run
* Mac:
```
./mvnw spring-boot:run
```
* Windows: 
```
mvnw spring-boot:run
```

## How to test

Once the app starts, go to below URL to see the status in JSON format. Please reload the page to see the latest data.
This is a test page to see changes after insert/update
```
http://localhost:8080/monitor.html
 ```

### Add items to Cart

Invoke the REST endpoint with **name** and **quantity** parameters to add the item to cart in current session:
```
 http://localhost:8080/addToCart/{name}/{quantity}
```
### Add items to Store

Invoke the REST endpoint with **name** and **price** parameters to add the item to cart in current session:
```
 http://localhost:8080/addToStore/{name}/{price}
```
### View cart items

Invoke the REST endpoint for a list of items cart in current session:
```
 http://localhost:8080/cart/items
```
### View cart total

Invoke the REST endpoint for total price of items in cart in current session:
```
 http://localhost:8080/cart/total
```
### View items in Store

Invoke the REST endpoint for the total list of items in store:
```
 http://localhost:8080/store/items
```
## Author
Sreelaj