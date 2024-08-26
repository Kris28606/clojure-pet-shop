(ns clojure-pet-shop.repository.invoiceRep
  (:require [clojure-pet-shop.sql.database]
            [clojure-pet-shop.domain.client :refer :all]
            [clojure-pet-shop.utility.helper :refer :all]
            [korma.core :refer :all]))

(defentity invoice)
(defentity invoiceItem)

(defn get-invoice-by-id [invoiceId]
  (select invoice
          (where {:invoiceId invoiceId})
          (limit 1)))

(defn get-invoices-for-client [clientId]
  (select invoice
          (where {:clientId clientId})))

(defn create-invoice [invoiceData]
  (def existingInvoice (get-invoice-by-id (:invoiceId invoiceData)))
    (if existingInvoice
      (format "Invoice with ID %d already exists!" (:invoiceId invoiceData))
      (if (pos? (count (:invoiceItems invoiceData)))
        (let [insertResult (insert invoice
                                   (values {:totalPrice       (:totalPrice invoiceData)
                                            :dateOfShopping   (:dateOfShopping invoiceData)
                                            :clientId         (:clientId invoiceData)}))
              insertedInvoiceId (get insertResult :generated_key)]
          (doseq [item (:invoiceItems invoiceData)]
            (insert invoiceItem
                    (values (assoc item :invoiceId insertedInvoiceId))))
          (get-invoice-by-id insertedInvoiceId))
        "Invoice items length must be greater than 0!")))
