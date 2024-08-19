(ns clojure-pet-shop.query
  (:require [clojure-pet-shop.database]
             [clojure-pet-shop.domain :refer :all]
             [korma.core :refer :all]))
  
  (defentity korisnik)
  
  (defn get-clients []
    (select korisnik))
  
  (defn get-client [clientId]
    (first
     (select korisnik 
             (where {:korisnikId [= clientId]}) 
             (limit 1))))
  
  (defn add-client [newClient]
    (def result (insert korisnik 
                        (values {:ime (get newClient :ime)
                                 :prezime (get newClient :prezime)
                                 :korisnickoIme (get newClient :korisnickoIme)
                                 :korisnickaSifra (get newClient :korisnickaSifra)})))
    (def insertedId (get result :generated_key))
    (get-client insertedId)
    )
  
  (defn update-client [clientId updatedClient]
    (update korisnik
            (set-fields {:ime (get updatedClient :ime)
                          :prezime (get updatedClient :prezime)
                          :korisnickoIme (get updatedClient :korisnickoIme)
                          :korisnickaSifra (get updatedClient :korisnickaSifra)})
             (where {:korisnikId [= clientId]}))
            (get-client clientId))
  
  (defn delete-client [korisnikId]
    (delete korisnik
            (where {:korisnikId [= korisnikId]})))