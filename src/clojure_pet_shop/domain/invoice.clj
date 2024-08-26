(ns clojure-pet-shop.domain.invoice
  (:require [schema.core :as s] 
            [clojure-pet-shop.domain.invoiceItem :refer :all]
            [ring.swagger.schema :refer [coerce!]]))

(s/defschema Invoice {:invoiceId      Integer
                      :totalPrice     Double
                      :dateOfShopping   String
                      :clientId      Integer
                      :invoiceItems [InvoiceItem]})

(s/defschema NewInvoice (dissoc Invoice :invoiceId))