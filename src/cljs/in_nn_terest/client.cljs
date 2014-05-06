(ns in-nn-terest.client
  (:require [enfocus.core :as ef]
            [in-nn-terest.googmap :as googmap]
            [in-nn-terest.connect :as in-connect])
  (:require-macros [shoreleave.remotes.macros :as macros]
                   [enfocus.macros :as em]))

(em/defsnippet index-body :compiled "public/prototype/index_body.html" ["body"]
  []
 ;; "#home-btn a" (set-attr :href "/")
  )


(em/defaction init []
  "body" (ef/content (index-body))
;;  ["#map_canvas"] (ef/content (googmap/map-load))
;;  "head" (ef/content (index-head))
  )

(defn start []
  (do (googmap/map-load)
      (googmap/show-events)))

(set! (.-onload js/window) start)



