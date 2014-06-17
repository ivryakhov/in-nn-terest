(ns in-nn-terest.googmap
  (:require [goog.events :as events]
            [goog.dom :as dom]))

(def ^:export markers-example
  [{:placename "Нижегородский выставочный комплекс"
    :lat 56.325760
    :lng 44.004964
    :content "<div id=\"content\">Выставка чайных ложек.</div>"}
   {:placename "Государственный центр современного искусства Арсенал"
    :lat 56.328876
    :lng 44.007029
    :content "<div id=\"content\">Современная мазня как аллюзия на первобытное творчество.</div>"}])

(def *map*)

(def map-opts
  (clj->js {:zoom 12
            :mapTypeId google.maps.MapTypeId.ROADMAP
            :center (google.maps.LatLng. 56.278292, 43.989537)}))

(def marker-opts {:placename "tatata"
                  :lat 56.326104
                  :lng 44.005434})

(defn ^:export marker-display
  ([] (marker-display (last markers-example)))
  ([opts] (let [lat (:lat opts)
                lng (:lng opts)
                pos (google.maps.LatLng. lat lng)
                title (:placename opts)
                content (:content opts)
                marker (google.maps.Marker. (clj->js {:position pos
                                                      :title title}))
                info-window (google.maps.InfoWindow.
                             (clj->js {:content content}))]
            (.setMap marker *map*)
            (google.maps.event.addListener marker "mouseover" (fn [] (.open
                                                                     info-window *map* marker)))
            marker)))

(defn ^:export show-events []
  (dorun (map marker-display markers-example)))

(defn map-load []
  (let [elem (goog.dom/getElement "map-canvas")]
    (set! *map* (google.maps.Map. elem map-opts))))

(defn start []
  (do (map-load)
      (show-events)))

(events/listen js/window "load" start)
