(ns in-nn-terest.datepicker
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(def app-state
  (atom {:things []}))

(defn datepicker-app [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil
               (dom/h1 nil "datepicker is working!")))))

(om/root app-state datepicker-app {:target (.getElementById js/document "datepicker")})
