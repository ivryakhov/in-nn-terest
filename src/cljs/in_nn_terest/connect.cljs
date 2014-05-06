(ns in-nn-terest.connect
  (:require [clojure.browser.repl :as repl]))

(defn ^:export connect []
  (repl/connect "http://37.139.24.91:3000/repl"))

