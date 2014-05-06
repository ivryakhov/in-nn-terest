(ns in-nn-terest.scraping
  (:require [net.cgrand.enlive-html :as enht]))

(def ^:dynamic *base-url* "http://ngvk.ru/news/")

(defn fetch-url [url]
  (enht/html-resource (java.net.URL. url)))

(defn select-tags [url-data tags]
  (map enht/text (enht/select url-data tags)))

(defn select-attrs [url-data tags attr]
  (map attr (map :attrs (enht/select url-data tags))))

(defn scrape-events [url]
  (let [url-data (fetch-url url)
        headlines (select-tags url-data [:div.ename :a])
        links (select-attrs url-data [:div.ename :a] :href)
        descriptions (select-tags url-data [:div.epress])]
    [headlines links descriptions] ))
