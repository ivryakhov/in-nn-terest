(ns in-nn-terest.datepicker
  (:import goog.date)
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs-time.core :as t]))

(enable-console-print!)

(def week-days ["Пн" "Вт" "Ср" "Чт" "Пт" "Сб" "Вс"])

(def app-state
  (atom {:cur-date (t/now)}))

(defn print-td [text owner]
  (reify
    om/IRender
    (render [this]
      (dom/td nil text))))

(defn datepicker-app1 [app owner]
  (reify
    om/IRender
    (render [this]
      (dom/table #js {:className "week-days"} nil
                 (apply dom/tr nil
                        (om/build-all print-td week-days)))
      (dom/h1 (:cur-date app)))))

(defn datepicker-app [app owner]
  (dom/table #js {:className "week-days"} nil
         (apply dom/tr nil
          (map (fn [text] (dom/td nil text)) week-days))))

(om/root datepicker-app1 app-state {:target (.getElementById js/document "datepicker")})
