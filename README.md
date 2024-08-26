# ClojurePetShop

ClojurePetShop by Kristina Stanisavljevic (index no. 2022/3720)

---

ClojurePetStore is a [Leiningen][1] project written in Clojure that provides a REST API for an imaginary pet shop, dealing with users and pet products. It uses [composure-api][2] as a REST API framework, [korma][3] to communicate with MySQL, [ring][4] as a web server that understands json requests/responses ([ring/json][5]), and [lein/ring][6] profile to bind it together with Leiningen. The API uses [Swagger][7] as a means of API testing and documenting.

## Project description

The PetShop API simulates the operation of a virtual pet shop. It includes and uses the following domain entities: Client, PetProduct, Manufacturer, Invoice, and InvoiceItem. The PetShop API contains 19 API methods, of which 5 are related to clients, 5 to manufacturers, 6 to pet products, and 3 to invoices.

For client, we have the following options:   

    1. GET /clients - Get all clients 
    2. POST /clients - Create new client 
        - if client with that username already exist, it returns a formatted message indicating that a client with that username already exists
    3. DELETE /clients/{id} - Delete client with specific ID 
        - if client with specific ID doesn't exist, it returns a formatted message indicating that a client with that ID doens't exist
    4. GET /clients/{id} - Get client with specific ID
    5. PUT /clients/{id} - Update client with specific ID 
        - if client with specific ID doesn't exist, it returns a formatted message indicating that a client with that ID doens't exist 
        - if we attempt to change the username that is already taken it also returns message

For manufacturer, we have the following options:   

    1. GET /manufacturers - Get all manufacturers 
    2. POST /manufacturers - Create new manufacturer 
        - if manufacturer with that name already exist, it returns a formatted message indicating that a manufacturer with that name already exists
    3. DELETE /manufacturers/{id} - Delete manufacturer with specific ID 
        - if manufacturer with specific ID doesn't exist, it returns a formatted message indicating that a manufacturer with that ID doens't exist
    4. GET /manufacturers/{id} - Get manufacturer with specific ID
    5. PUT /manufacturers/{id} - Update manufacturer with specific ID 
        - if client with specific ID doesn't exist, it returns a formatted message indicating that a client with that ID doens't exist 
        - if we attempt to change the name that is already taken it also returns message

For pet product, we have the following options:   

    1. GET /products - Get all pet products 
    2. POST /products - Create new pet product 
        - if manufacturer with specific ID doesn't exist, it returns a formatted message indicating that no manufacturer with that ID exists
        - if pet product with specific pet product name already exist, it returns a formatted message indicating that a pet product with that name already exists
        - if price for pet product is lower or equal to 0, it returns a formatted message indicating that a price must be greater than 0
    3. DELETE /products/{id} - Delete pet product with specific ID 
        - if pet product with specific ID doesn't exist, it returns a formatted message indicating that a pet product with that ID doens't exist
    4. GET /products/{id} - Get product with specific ID
    5. PUT /products/{id} - Update product with specific ID 
        - if pet product with specific ID doesn't exist, it returns a formatted message indicating that a pet product with that ID doens't exist 
        - if we attempt to change the name of pet product to one that is already in use it also returns message
        - if we attempt to change the manufacturerId to an ID for which no manufacturer exists, we will receive an error message
    6. GET /products/{manufacturerId}/products - Get all products that are made by the manufacturer with the specific ID 
        - if manufacturer with specific ID doesn't exist it returns a formatted message indicating that no manufacturer with that ID exist

For invoice, we have the following options:   

    1. GET /invoices/{id} - Get invoice with specific ID
    2. GET /invoices/client/{id} - Get all invoices for client with specific ID 
    3. POST /invices/new - Create new invoice
        - if length of invoice items is 0, it returns a formatted message indicating that invoice items length must be greater than 0

## Prerequisites to run and use

You will need Clojure 1.11.1 with Leiningen 2.11.2 on Java 1.8.0 or above installed. 

If you're using Visual Studio Code as your IDE of choice (and that's a recommendation) make sure you install Clojure and Clojure Code plugins.

### Database

On the root of the repo, in the sql/ folder you'll find an .sql file that you will have to use to restore the MySQL database. MySQL Community bundle with the actual db server and Workbench can be downloaded from the [official website][8].

## Running

Run `lein ring server` in the root of the project to run the actual app. This will start a web server and open the home page of Swagger API documentation.

## Project architecture

`/src/clojure_pet_shop/db` holds latest database restore.

`/src/clojure_pet_shop/domain` holds the domain entities that provide an image of the DB tables.

`/src/clojure_pet_shop/sql` contains code to provide a connection to the MySQL server

`/src/clojure_pet_shop/repository` abstracts the individual entities SQL related logic - data layer

`/src/clojure_pet_shop/api` contains the core.clj file that provides the app logic - API routes. This layer responds to HTTP requests and calls the data layer to eventually return HTTP responses with the results gotten from data layer.

`/src/clojure_pet_shop/utility` contains helpers

## Libraries with versions

`[metosin/compojure-api "2.0.0-alpha19"]` - Library on top of Composure that brings the functionalities for development of REST APIs

`[korma "0.4.3"]` - SQL helper library for Clojure 

`[ring/ring-core "1.6.3"] ,[ring/ring-jetty-adapter "1.6.3"], [ring/ring-json "0.4.0"], [ring/ring-defaults "0.3.2"]` - Ring web server dependent libraries - HTTP server abstraction

`[mysql/mysql-connector-java "5.1.6"]` - Standard MySQL connector for java

`[clj-http "2.0.0"]` - Standard set of HTTP utilities for Clojure - used as a lower-level dependency of composure and swagger.

## Plugins with versions

`[lein-ring "0.12.5"]` - Ring server plugin for leiningen

`[compojure "1.6.1"]` - Composure - concise routing library for Ring/Clojure

## License

Copyright © 2024 Kristina Stanisavljevic. 

[1]: https://github.com/technomancy/leiningen
[2]: https://github.com/metosin/compojure-api
[3]: https://github.com/korma/Korma
[4]: https://github.com/ring-clojure/ring
[5]: https://github.com/ring-clojure/ring-json
[6]: https://github.com/weavejester/lein-ring
[7]: https://swagger.io/
[8]: https://dev.mysql.com/downloads/windows/installer/5.7.html