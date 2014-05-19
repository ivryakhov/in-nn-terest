(ns in-nn-terest.base
  (:require [datomic.api :as d]))

;;(def uri "datomic:free://localhost:4334/in-nn-terest-db")
(def uri "datomic:mem://localhost:4334/in-nn-terest-db")
(def conn (d/connect uri))

(def schema (load-file "resources/datomic/schema.edn"))

(def sample-data (load-file "resources/datomic/sample-data.edn"))

(defn eid [db attr val]
  (->> (d/q '[:find ?e :in $ ?a ?v :where [?e ?a ?v]] db attr val)
       ffirst))

(defn attrs [db attr val]
  (some->> (eid db attr val)
           (d/entity db)
           d/touch))

(defn process-event [event-map]
  (let [{:keys [headline
                link
                description
                begin-date
                end-date]} event-map
        id (d/tempid :db.part/user)]
    @(d/transact conn [{:db/id id
                        :event/headline headline
                        :event/link link
                        :event/description description
                        :event/begin-date begin-date
                        :event/end-date end-date}])
    (eid (d/db conn) :event/headline headline)))

(defn process-place [place-map]
  (let [{:keys [name
                lat 
                lng
                events]} place-map]
    @(d/transact conn [{:db/id (d/tempid :db.part/user)
                        :place/name name
                        :place/lat lat
                        :place/lng lng
                        :place/events (mapv  process-event events) }])))

(defn load-sample-data [sample-data]
  (map process-place sample-data))

(defn init-db []
  (d/create-database uri)
  @(d/transact conn schema)
  (load-sample-data sample-data))
