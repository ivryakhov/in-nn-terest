(ns in-nn-terest.client
  (:require [enfocus.core :as ef]
            [in-nn-terest.googmap :as googmap])
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
  (googmap/map-load)
  (googmap/marker-create))

(set! (.-onload js/window) #(em/wait-for-load (start)))


