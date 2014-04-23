(ns in-nn-terest.client
  (:require
   [enfocus.core :as ef])
  (:use-macros
    [enfocus.macros :only [deftemplate defsnippet defaction]])
  (:require-macros
    [shoreleave.remotes.macros :as macros]))

(defsnippet index-page :compiled "public/prototype/index.html" ["body"]
  []
 ;; "#home-btn a" (set-attr :href "/")
  )

(defaction init []
  "body" (ef/content (index-page)))

(set! (.-onload js/window) init)

