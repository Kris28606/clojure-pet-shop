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
  (def existingClient (get-client-by-username (get newClient :username)))
  (if existingClient
    (format "Client with username %s already exist!" (get newClient :username))
    ((def insertClientResult (insert client
                                       (values {:firstName (get newClient :firstName)
                                                :lastName (get newClient :lastName)
                                                :username (get newClient :username)
                                                :password (get newClient :password)})))
     (def insertedClientId (get insertClientResult :generated_key))
     (get-client insertedClientId))))

(defn update-client [clientId updatedClient]
   (def existingClient (get-client clientId))
   (def existingClientByUsername (get-client-by-username (get updatedClient :username))) 
   (if existingClient
     ((if existingClientByUsername
        (constantly "User with that username already exists")
        ((update client
                 (set-fields {:firstName (get updatedClient :firstName)
                              :lastName (get updatedClient :lastName)
                              :username (get updatedClient :username)
                              :password (get updatedClient :password)})
                 (where {:clientId [= clientId]}))
         (constantly nil))))
     (format "Cannot find user with id %s" clientId))
   (get-client clientId))

(defn delete-client [clientId]
  (def existingClient (get-client clientId))
  (if existingClient
    (delete client
            (where {:clientId [= clientId]}))
    (format "User with id %s doesn't exist" clientId)))