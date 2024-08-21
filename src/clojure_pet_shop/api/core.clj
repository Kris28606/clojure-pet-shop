(ns clojure-pet-shop.api.core
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [clojure-pet-shop.domain.client :refer :all]
            [clojure-pet-shop.domain.manufacturer :refer :all]
            [clojure-pet-shop.repository.clientRep :refer :all]
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
                   {:name "manufacturers", :description "Manufacturer API"}]}}}

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
         (ok nil))))))

