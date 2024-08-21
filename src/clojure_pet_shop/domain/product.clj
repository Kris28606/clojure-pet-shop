(ns clojure-pet-shop.domain.product
  (:require [schema.core :as s] 
            [ring.swagger.schema :refer [coerce!]]))

(s/defschema PetProduct {:productId      Integer
                         :productName  String
                         :type             String
                         :price            Double
                         :manufacturerId   Integer})

(s/defschema NewPetProduct (dissoc PetProduct :productId))