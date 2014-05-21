(ns in-nn-terest.base
  (:require [datomic.api :as d]
            [clj-time.core :as ctc]
            [clj-time.format :as ctf]))

;;(def uri "datomic:free://localhost:4334/in-nn-terest-db")
(def uri "datomic:mem://in-nn-terest-db")
(d/create-database uri)
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
  @(d/transact conn schema)
  (load-sample-data sample-data))

(defn str->cljt [date-str]
  (ctf/parse (ctf/formatter "dd-MM-yyyy") date-str))

(defn check-int [d-begin d-end d-check]
  (ctc/within? (str->cljt d-begin) (str->cljt d-end) d-check))

(defn select-events-for-date [cljtime-date]
  (d/q '[:find ?id ?begin-date ?end-date
         :in $ ?check-date
         :where
         [?id :event/begin-date ?begin-date]
         [?id :event/end-date ?end-date]
         [(in-nn-terest.base/check-int ?begin-date ?end-date ?check-date)]]
       (d/db conn) cljtime-date))
