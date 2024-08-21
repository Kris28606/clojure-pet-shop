(ns clojure-pet-shop.domain.manufacturer
  (:require [schema.core :as s]
             [ring.swagger.schema :refer [coerce!]]))

(s/defschema Manufacturer {:manufacturerId   Integer
                           :name           String
                           :country          String
                           :address         String})

(s/defschema NewManufacturer (dissoc Manufacturer :manufacturerId))