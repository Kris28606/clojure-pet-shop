(ns clojure-pet-shop.query
  (:require [clojure-pet-shop.database]
             [clojure-pet-shop.domain :refer :all]
             [korma.core :refer :all]))
  
  (defentity korisnik)
  
  (defn get-users []
    (select korisnik))
  
  (defn get-user [korisnikId]
    (first
     (select korisnik
             (where {:korisnikId [= korisnikId]}))))
  
  (defn add-user [newKorisnik]
    (insert korisnik
            (values {:ime (get newKorisnik :ime)
                     :prezime (get newKorisnik :prezime)
                     :korisnickoIme (get newKorisnik :korisnickoIme)
                     :korisnickaSifra (get newKorisnik :korisnickaSifra)})))
  
  (defn update-user [korisnikId ime prezime korisnickoIme korisnickaSifra]
    (update korisnik
            (set-fields {:ime ime
                         :prezime prezime
                         :korisnickoIme korisnickoIme
                         :korisnickaSifra korisnickaSifra})
            (where {:korisnikId [= korisnikId]})))
  
  (defn delete-user [korisnikId]
    (delete korisnik
            (where {:korisnikId [= korisnikId]})))