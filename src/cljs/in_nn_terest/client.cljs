(ns in-nn-terest.client
  (:require
   [enfocus.core :as ef])
  (:use-macros
    [enfocus.macros :only [deftemplate defsnippet defaction]])
  (:require-macros
    [shoreleave.remotes.macros :as macros]))

(defsnippet index-body :compiled "public/prototype/index_body.html" ["body"]
  []
 ;; "#home-btn a" (set-attr :href "/")
  )

(defsnippet index-head :compiled "public/prototype/index_head.html" ["head"]
  [])

(defaction init []
  "body" (ef/content (index-body))
;;  "head" (ef/content (index-head))
  )

(set! (.-onload js/window) init)

