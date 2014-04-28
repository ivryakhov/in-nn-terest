(ns in-nn-terest.googmap
  (:require [goog.events :as events]
            [goog.dom :as dom]))

(def *map* nil)

(def my-opts
  (extend-object! (js-obj) {"zoom" 12
    "mapTypeId" google.maps.MapTypeId.ROADMAP
    "center" (google.maps.LatLng. 56.278292, 43.989537)}))

(defn map-load []
  (let [elem (goog.dom/getElement "map_canvas")]
    (set! *map* (google.maps.Map. elem my-opts))))

;;(events/listen js/window "load" map-load)




