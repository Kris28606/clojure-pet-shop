(ns clojure-pet-shop.utility.helper)

(defn parse-int [s]
  (Integer/parseInt (re-find #"\A-?\d+" s)))