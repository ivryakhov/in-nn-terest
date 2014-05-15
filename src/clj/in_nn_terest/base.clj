(ns in-nn-terest.base
  (:require [datomic.api :as d]))

(def uri "datomic:free://localhost:4334/in-nn-terest")
(def *conn* nil)

(def schema [{:db/ident       :place/name
             :db/valueType   :db.type/string
             :db/cardinality :db.cardinality/one
             :db/id          (d/tempid :db.part/db)
             :db.install/_attribute :db.part/db}
            
            {:db/ident       :place/lat
             :db/valueType   :db.type/double
             :db/cardinality :db.cardinality/one
             :db/id          (d/tempid :db.part/db)
             :db.install/_attribute :db.part/db}

            {:db/ident       :place/lng
             :db/valueType   :db.type/double
             :db/cardinality :db.cardinality/one
             :db/id          (d/tempid :db.part/db)
             :db.install/_attribute :db.part/db}

            {:db/ident       :place/events
             :db/valueType   :db.type/ref
             :db/cardinality :db.cardinality/many
             :db/id          (d/tempid :db.part/db)
             :db.install/_attribute :db.part/db}])

(defn init-db []
  (d/create-database uri)
  (set! *conn* (d/connect uri))
  (d/transact *conn* schema))
