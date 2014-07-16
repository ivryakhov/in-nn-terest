(ns in-nn-terest.datepicker
  (:require [goog.dom :as dom]
            [goog.events :as events])
  (:import  [goog.ui DatePicker]))


(defn put-datepicker []
  (let [elem (dom/getElement "datepicker")
        dp (DatePicker. nil goog.i18n.DateTimeSymbols_ru)]
    (.setShowWeekNum dp false)
    (.setShowFixedNumWeeks dp false)
    (.setAllowNone dp false)
    (.render dp elem)
    dp))

(events/listen js/window "load" put-datepicker)

