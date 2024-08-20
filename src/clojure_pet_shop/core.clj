(ns clojure-pet-shop.core
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [clojure-pet-shop.domain :refer :all]
            [clojure-pet-shop.query :refer :all]))

(def app
  (api
   {:swagger
    {:ui "/"
     :spec "/swagger.json"
     :data {:info {:title "PetShop API"
                   :description "API for a college project made with clojure"}
            :tags [{:name "client", :description "client api."}]}}}

   (context "/clients" []
     :tags ["clients"]

     (GET "/" []
       :return [Client]
       :summary "Return all clients from db"
       (ok (get-clients)))
     
     (GET "/:id" []
       :path-params [id :- s/Any]
       :summary "Return client by specific ID"
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
         (ok createNewClientResult))))))

