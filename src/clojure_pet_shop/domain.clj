(ns clojure-pet-shop.domain
  (:require [schema.core :as s] 
            [ring.swagger.schema :refer [coerce!]]))
                           
;; Domain
                           
(s/defschema Korisnik {:korisnikId      Integer
                       :ime             String
                       :prezime         String
                       :korisnickoIme   String
                       :korisnickaSifra String})
                           
(s/defschema NewKorisnik (dissoc Korisnik :korisnikId))