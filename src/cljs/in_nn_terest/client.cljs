(ns in-nn-terest.client
  (:require [enfocus.core :as ef]
            [in-nn-terest.googmap :as googmap]
            )
  (:use-macros
    [enfocus.macros :only [deftemplate defsnippet defaction]])
  (:require-macros
    [shoreleave.remotes.macros :as macros]))

(defsnippet index-body :compiled "public/prototype/index_body.html" ["body"]
  []
 ;; "#home-btn a" (set-attr :href "/")
  )


(defaction init []
;;  "body" (ef/content (index-body))
  ["#map_canvas"] (ef/content (googmap/map-load))
;;  "head" (ef/content (index-head))
  )

;;(set! (.-onload js/window) init)


