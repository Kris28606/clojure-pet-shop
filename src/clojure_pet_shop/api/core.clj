(ns clojure-pet-shop.api.core
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [clojure-pet-shop.domain.client :refer :all]
            [clojure-pet-shop.domain.manufacturer :refer :all]
            [clojure-pet-shop.domain.product :refer :all]
            [clojure-pet-shop.repository.clientRep :refer :all]
            [clojure-pet-shop.repository.productRep :refer :all]
            [clojure-pet-shop.repository.manufacturerRep :refer :all]))

(def app
  (api
   ; Clients api
   {:swagger
    {:ui "/"
     :spec "/swagger.json"
     :data {:info {:title "PetShop API"
                   :description "PetShop API for a college project made with clojure"}
            :tags [{:name "clients", :description "Client API"}
                   {:name "manufacturers", :description "Manufacturer API"}
                   {:name "products", :description "Pet products API"}]}}}

   (context "/clients" []
     :tags ["clients"]

     (GET "/" []
       :return [Client]
       :summary "Get all clients"
       (ok (get-clients)))

     (GET "/:id" []
       :path-params [id :- s/Any]
       :summary "Get client by specific ID"
       (def foundClient (get-client id))
       (if foundClient (ok foundClient) (not-found)))

     (PUT "/:id" []
       :summary "Update client with specific ID"
       :path-params [id :- s/Any]
       :body [updatedClient NewClient]
       (def updateClientResult (update-client id updatedClient))
       (if (= (type updateClientResult) java.lang.Integer)
         (ok nil)
         (bad-request updateClientResult)))

     (DELETE "/:id" []
       :summary "Delete client with specific ID"
       :path-params [id :- s/Any]
       (def deleteClientResult (delete-client id))
       (if (= (type deleteClientResult) java.lang.String)
         (bad-request deleteClientResult)
         (ok nil)))

     (POST "/" []
       :summary "Create new client"
       :body [newClient NewClient]
       (def createNewClientResult (add-client newClient))
       (if (= (type createNewClientResult) java.lang.String)
         (bad-request createNewClientResult)
         (ok createNewClientResult))))

   (context "/manufacturers" []
     :tags ["manufacturers"]

     (GET "/" []
       :return [Manufacturer]
       :summary "Get all manufacturers"
       (ok (get-manufacturers)))

     (GET "/:id" []
       :path-params [id :- s/Any]
       :summary "Get manufacturer by specific ID"
       (def foundManufacturer (get-manufacturer id))
       (if foundManufacturer (ok foundManufacturer) (not-found)))

     (POST "/" []
       :summary "Create new manufacturer"
       :body [newManufacturer NewManufacturer]
       (def createManufacturerResult (create-manufacturer newManufacturer))
       (if (= (type createManufacturerResult) java.lang.String)
         (bad-request createManufacturerResult)
         (ok createManufacturerResult)))

     (PUT "/:id" []
       :summary "Update manufacturer with specific ID"
       :path-params [id :- s/Any]
       :body [updatedManufacturer NewManufacturer]
       (def updateManufacturerResult (update-manufacturer id updatedManufacturer))
       (if (= (type updateManufacturerResult) java.lang.Integer)
         (ok nil)
         (bad-request updateManufacturerResult)))

     (DELETE "/:id" []
       :summary "Delete manufacturer with specific ID"
       :path-params [id :- s/Any]
       (def deleteManufacturerResult (delete-manufacturer id))
       (if (= (type deleteManufacturerResult) java.lang.String)
         (bad-request deleteManufacturerResult)
         (ok nil)))) 
   
   (context "/products" []
     :tags ["products"]

     (GET "/" []
       :return [PetProduct]
       :summary "Get all pet products"
       (ok (get-pet-products)))

     (GET "/:id" []
       :path-params [id :- s/Any]
       :summary "Get pet product by specific ID"
       (def foundPetProduct (get-pet-product id))
       (if foundPetProduct (ok foundPetProduct) (not-found)))

     (POST "/" []
       :summary "Create new pet product"
       :body [newPetProduct NewPetProduct]
       (def createPetProductResult (create-pet-product newPetProduct))
       (if (= (type createPetProductResult) java.lang.String)
         (bad-request createPetProductResult)
         (ok createPetProductResult)))

     (PUT "/:id" []
       :summary "Update pet product by specific ID"
       :path-params [id :- s/Any]
       :body [updatedPetProduct NewPetProduct]
       (def updatedPetProductResult (update-pet-product id updatedPetProduct))
       (if (= (type updatedPetProductResult) java.lang.Integer)
         (ok nil)
         (bad-request updatedPetProductResult)))

     (DELETE "/:id" []
       :summary "Delete pet product by specific ID"
       :path-params [id :- s/Any]
       (def deletePetProductResult (delete-pet-product id))
       (if (= (type deletePetProductResult) java.lang.String)
         (bad-request deletePetProductResult)
         (ok nil)))
     
     (GET "/:manufacturerId/products" []
       :summary "Get all products from manufacturer with specific ID"
       :path-params [manufacturerId :- s/Any]
       (ok (get-pet-products-for-manufacturer manufacturerId))))))

