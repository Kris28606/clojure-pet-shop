(ns clojure-pet-shop.domain.client
  (:require [schema.core :as s] 
            [ring.swagger.schema :refer [coerce!]]))

(s/defschema Client {:clientId   Integer
                     :firstName  String
                     :lastName   String
                     :username   String
                     :password   String})

(s/defschema NewClient (dissoc Client :clientId))