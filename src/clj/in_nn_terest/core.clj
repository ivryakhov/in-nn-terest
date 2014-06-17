(ns in-nn-terest.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :refer (resource-response)]
           ;; [shoreleave.middleware.rpc :as rpc]
            ))

(defroutes app-routes
  (GET "/" [] (resource-response "index.html" {:root "public"}))
  (route/resources "/")
  (route/not-found "Not found"))

(def handler
  (-> app-routes
   ;;   rpc/wrap-rpc
      handler/site))
