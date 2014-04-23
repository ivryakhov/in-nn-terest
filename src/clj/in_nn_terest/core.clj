(ns in-nn-terest.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :refer (resource-response)]))

(defroutes app-routes
  (GET "/" [] (resource-response "blank.html" {:root "publlic"}))
  (route/resources "/")
  (route/not-found "Page not found"))

(def handler
  (handler/site app-routes))
