(ns in-nn-terest.base
  (:require [datomic.api :as d]))

;;(def uri "datomic:free://localhost:4334/in-nn-terest-db")
(def uri "datomic:mem://localhost:4334/in-nn-terest-db")
(def conn (d/connect uri))

(def schema (load-file "resources/datomic/schema.edn"))

(defn init-db []
  (d/create-database uri)
  @(d/transact conn schema))
