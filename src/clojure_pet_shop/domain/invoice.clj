(ns clojure-pet-shop.domain.invoice
  (:require [schema.core :as s] 
            [ring.swagger.schema :refer [coerce!]]))

(s/defschema Invoice {:invoiceId      Integer
                      :totalPrice     Double
                      :dateOfShopping   String
                      :clientId      Integer})

(s/defschema Invoice (dissoc Invoice :invoiceId))