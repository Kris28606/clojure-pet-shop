(ns clojure-pet-shop.repository.productRep
  (:require [clojure-pet-shop.sql.database] 
            [clojure-pet-shop.domain.product :refer :all] 
            [clojure-pet-shop.domain.manufacturer :refer :all] 
            [clojure-pet-shop.repository.manufacturerRep :refer :all] 
            [clojure-pet-shop.utility.helper :refer :all] 
            [korma.core :refer :all]))
  
  (defentity petProduct)
  
  (defn get-pet-products []
    (select petProduct))
  
  (defn get-pet-product [productId]
    (first
     (select petProduct
             (where {:productId [= productId]})
             (limit 1))))
  
  (defn get-pet-product-by-name [name]
    (first
     (select petProduct
             (where {:productName [= name]})
             (limit 1))))
  
  (defn create-pet-product [newPetProduct]
    (def existingPetProduct (get-pet-product-by-name (get newPetProduct :productName)))
    (def existingManufacturerById (get-manufacturer (get newPetProduct :manufacturerId)))
    (if existingPetProduct
      (format "Pet product with name %s already exist!" (get newPetProduct :productName))
      (if existingManufacturerById
        (if (> (get newPetProduct :price) 0)
          ((def createdPetProductResult
             (insert petProduct
                     (values {:productName (get newPetProduct :productName)
                              :price (get newPetProduct :price)
                              :type (get newPetProduct :type)
                              :manufacturerId (get newPetProduct :manufacturerId)})))
           (def createdPetProductId (get createdPetProductResult :generated_key))
           (get-pet-product createdPetProductId))
          "Price must be greater then 0!")
        (format "Manufacturer with ID %s doesn't exist!" (get newPetProduct :manufacturerId)))))
  
  (defn update-pet-product [productId updatedProduct]
    (def existingPetProduct (get-pet-product productId))
    (def existingPetProductByName (get-pet-product-by-name (get updatedProduct :productName)))
    (def existingmanufacturerById (get-manufacturer (get updatedProduct :manufacturerId)))
    (if existingPetProduct
      (if (and existingPetProductByName (not= (parse-int productId) (get existingPetProductByName :productId)))
        (format "Pet product with name %s already exist!" (get updatedProduct :productName))
        (if existingmanufacturerById
          (update petProduct
                  (set-fields {:productName (get updatedProduct :productName)
                               :price (get updatedProduct :price)
                               :type (get updatedProduct :type)
                               :manufacturerId (get updatedProduct :manufacturerId)})
                  (where {:productId [= productId]}))
          (format "Manufacturer with ID %s doens't exist!" (get updatedProduct :manufacturerId))))
      (format "Pet product with ID %s doens't exist!" (get updatedProduct :productId))))
  
  (defn delete-pet-product [productId]
    (def existingPetProduct (get-pet-product productId))
    (if existingPetProduct
      (delete petProduct
              (where {:productId [= productId]}))
      (format "Pet product with ID %s doens't exist!" productId)))