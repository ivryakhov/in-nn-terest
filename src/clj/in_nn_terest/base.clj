(ns in-nn-terest.base
  (:require [datomic.api :as d]))

(def uri "datomic:free://localhost:4334/in-nn-terest")
(def *conn* nil)

(def schema (load-file "resources/datomic/schema.edn"))

(defn init-db []
  (d/create-database uri)
  (set! *conn* (d/connect uri))
  (d/transact *conn* schema))
