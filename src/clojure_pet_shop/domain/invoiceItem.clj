(ns clojure-pet-shop.domain.invoiceItem
  (:require [schema.core :as s] 
            [ring.swagger.schema :refer [coerce!]]))

(s/defschema InvoiceItem {:invoiceId      Integer
                          :sequenceNumber Integer
                          :itemPrice     Double
                          :quantity        Integer
                          :productId      Integer})