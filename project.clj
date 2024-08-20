(defproject clojure-pet-shop "0.1.0-SNAPSHOT"
  :description "A Clojure application for managing a pet shop"
  :url "http://example.com/clojure-pet-shop"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [clj-http "2.0.0"]
                 [ring/ring-core "1.6.3"]   ; Core Ring library
                 [ring/ring-jetty-adapter "1.6.3"]  ; Jetty adapter for Ring
                 [ring/ring-defaults "0.3.2"]    ; Defaults middleware
                 [ring/ring-json "0.4.0"]
                 [compojure "1.6.1"]
                 [korma "0.4.3"]
                 [http-kit "2.3.0"] 
                 [ring/ring-defaults "0.3.2"] 
                 [org.clojure/data.json "0.2.6"]
                 [mysql/mysql-connector-java "5.1.6"]
                 [metosin/compojure-api "2.0.0-alpha19"]]
  :plugins [[lein-ring "0.12.5"]            ; lein-ring plugin
            [compojure "1.6.1"]]
  :main ^:skip-aot clojure-pet-shop.api.core
  :ring {:handler clojure-pet-shop.api.core/app}  ; Configure Ring handler
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
