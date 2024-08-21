(ns clojure-pet-shop.repository.manufacturerRep
  (:require [clojure-pet-shop.sql.database] 
            [clojure-pet-shop.domain.manufacturer :refer :all] 
            [korma.core :refer :all]))
  
  (defentity manufacturer)
  
  (defn get-manufacturers []
    (select manufacturer))
  
  (defn get-manufacturer[manufacturerId]
    (first
     (select manufacturer
             (where {:manufacturerId [= manufacturerId]})
             (limit 1))))
  
  (defn get-manufacturer-by-name[name]
    (first
     (select manufacturer
             (where {:name [= name]})
             (limit 1))))
  
  (defn create-manufacturer [newManufacturer]
    (def existingManufacturer (get-manufacturer-by-name (get newManufacturer :name)))
    (if existingManufacturer
      (format "Manufacturer with name %s already exist!" (get newManufacturer :name))
      ((def insertManufacturerResult
         (insert manufacturer
                 (values {:name (get newManufacturer :name)
                          :country (get newManufacturer :country)
                          :address (get newManufacturer :address)})))
       (def insertedManufacturerId (get insertManufacturerResult :generated_key))
       (get-manufacturer insertedManufacturerId))))

  (defn parse-integer [s]
    (Integer/parseInt (re-find #"\A-?\d+" s)))

  (defn update-manufacturer [manufacturerId updatedManufacturer]
    (def existingManufacturer (get-manufacturer manufacturerId))
    (def existingManufacturerByName (get-manufacturer-by-name (get updatedManufacturer :name)))
    (if existingManufacturer
      (if (and existingManufacturerByName (not= (parse-integer manufacturerId) (get existingManufacturerByName :manufacturerId)))
        (format "Manufacturer with name %s already exist!" (get updatedManufacturer :name))
        (update manufacturer
                (set-fields {:name (get updatedManufacturer :name)
                             :country (get updatedManufacturer :country)
                             :address (get updatedManufacturer :address)})
                (where {:manufacturerId [= manufacturerId]})))
      (format "Manufacturer with id %s doesn't exist!" manufacturerId)))
  
  (defn delete-manufacturer [manufacturerId]
    (def existingManufacturer (get-manufacturer manufacturerId))
    (if existingManufacturer
      (delete manufacturer
              (where {:manufacturerId [= manufacturerId]}))
      (format "Manufacturer with id %s doesn't exist!" manufacturerId)))