(defproject in-nn-terest "0.1.0-SNAPSHOT"
  :description "Clojure web-appication that displays some events information using Google Maps"
  :url "https://github.com/ivryakhov/in-nn-terest"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [enlive "1.1.5"]
                 [ring "1.2.2"]
                 [compojure "1.1.6"]]

  :source-paths ["src/clj" "src/cljs"]
  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-ring "0.8.10"]]

  :ring {:handler in-nn-terest.core/handler}
  
  :cljsbuild {:builds
              [{:source-paths ["src/cljs"]
                :compiler {:output-to "resources/public/js/main.js"
                           :optimizations :whitespace
                           :pretty-print true}}]})
