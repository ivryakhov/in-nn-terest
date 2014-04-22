(defproject in-nn-terest "0.1.0-SNAPSHOT"
  :description "Clojure web-appication that displays some events information using Google Maps"
  :url "https://github.com/ivryakhov/in-nn-terest"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]

  :source-paths ["src/clj" "src/cljs"]
  :plugins [[lein-cljsbuild "1.3.0"]]

  :cljsbuild {:builds
              [{:source-paths ["src/cljs"]
                :compiler {:output-to "resources/public/js/in-nn-terest.js"
                           :optimizations :whitespace
                           :pretty-print true}}]})
