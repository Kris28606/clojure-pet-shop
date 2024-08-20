(ns clojure-pet-shop.query
  (:require [clojure-pet-shop.database]
             [clojure-pet-shop.domain :refer :all]
             [korma.core :refer :all]))
  
  (defentity client)
  
  (defn get-clients []
    (select client))
  
  (defn get-client [clientId]
    (first
     (select client 
             (where {:clientId [= clientId]}) 
             (limit 1))))
  
  (defn get-client-by-username [username]
    (first
     (select client
             (where {:username [= username]})
             (limit 1))))
  
  (defn add-client [newClient]
    (def result (insert client 
                        (values {:firstName (get newClient :firstName)
                                 :lastName (get newClient :lastName)
                                 :username (get newClient :username)
                                 :password (get newClient :password)})))
    (def insertedId (get result :generated_key))
    (get-client insertedId)
    )
  
  (defn update-client [clientId updatedClient]
    (update client
            (set-fields {:firstName (get updatedClient :firstName)
                          :lastName (get updatedClient :lastName)
                          :username (get updatedClient :username)
                          :password (get updatedClient :password)})
             (where {:clientId [= clientId]}))
            (get-client clientId))
  
  (defn delete-client [clientId]
    (delete client
            (where {:clientId [= clientId]})))