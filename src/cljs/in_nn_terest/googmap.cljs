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
                  "title" "Hello"})

(defn map-load []
  (let [elem (goog.dom/getElement "map_canvas")]
    (do (set! *map* (google.maps.Map. elem my-opts)))))

(defn marker-create []
  (let [l-opts (extend-object! (js-obj) marker-opts)
        marker (google.maps.Marker. l-opts)]
    (.setMap marker *map*)))

;;(events/listen js/window "load" map-load)




