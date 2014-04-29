(ns in-nn-terest.googmap
  (:require [goog.events :as events]
            [goog.dom :as dom]))

(def *map* nil)
(def *marker* nil)

(def my-opts
  (extend-object! (js-obj) {"zoom" 12
                            "mapTypeId" google.maps.MapTypeId.ROADMAP
                            "center" (google.maps.LatLng. 56.278292, 43.989537)}))

(def marker-opts {"position" (google.maps.LatLng. 56.278292, 43.989537)
                  "map" *map*
                  "title" "Hello"})

(defn map-load []
  (let [elem (goog.dom/getElement "map_canvas")]
    (do (set! *map* (google.maps.Map. elem my-opts))
        (marker-load))))

(defn marker-load [marker-opts]
  (let [l-opts (extend-object! (js-obj) marker-opts)]
    (set! *marker* (google.maps.Marker. l-opts))))

;;(events/listen js/window "load" map-load)




