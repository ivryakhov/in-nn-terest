(ns in-nn-terest.scraping
  (:require [net.cgrand.enlive-html :as enht]
            [clj-time.core :as ctc]
            [clj-time.format :as ctf]
            [clojure.core.match :refer [match]]
            [clojure.string :as string]))

(def ^:dynamic *base-url* "http://ngvk.ru/news/")

(defn fetch-url [url]
  (enht/html-resource (java.net.URL. url)))

(defn select-tags [url-data tags]
  (mapv enht/text (enht/select url-data tags)))

(defn select-attrs [url-data tags attr]
  (mapv attr (map :attrs (enht/select url-data tags))))

(def mnthname->num
  {"января"   1
   "февраля"  2
   "марта"    3
   "апреля"   4
   "мая"      5
   "июня"     6
   "июля"     7
   "августа"  8
   "сентября" 9
   "октября"  10
   "ноября"   11
   "декабря"  12})

(defn parse-date [date-str]
  (let [date-vec (string/split date-str #"\s")
        date-map (match date-vec
                         ["С" begin-day begin-month begin-year "до" end-day end-month end-year & rest]
                         [{:begin-day   begin-day
                           :begin-month (mnthname->num begin-month)
                           :begin-year  begin-year
                           :end-day   end-day
                           :end-month (mnthname->num end-month)
                           :end-year  end-year}]
                         :else rest)]
    date-map))

(defn date-map->cljtime [date-map]
  )

(defn select-dates [url-data tags]
  (mapv last (map :content (enht/select url-data tags))))


(defn scrape-events [url]
  (let [url-data (fetch-url url)
        headlines (select-tags url-data [:div.ename :a])
        links (select-attrs url-data [:div.ename :a] :href)
        descriptions (select-tags url-data [:div.epress])
        dates (select-dates url-data [:div.ename])]
    [headlines links dates descriptions]))
