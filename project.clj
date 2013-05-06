(defproject verbs "0.1.0-SNAPSHOT"
  :description "french verb training web application"
  :url "http://example.com/FIXME"
  :source-paths ["src/clj"]
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.3"]
                 [ring-json-response "0.2.0"]
                 [org.clojure/data.json "0.2.2"]
                 [prismatic/dommy "0.1.2-SNAPSHOT"]]
  :plugins [[lein-cljsbuild "0.3.0"]
            [lein-ring "0.8.5"]]
  :hooks [leiningen.cljsbuild]
  :cljsbuild {:builds [
               {:source-paths ["src/cljs"]
                :compiler {:output-to "resources/public/cljs/verb-test.js"
                           :optimizations :whitespace
                           :pretty-print true}}]}
  :ring {:init verbs.handler/init
         :handler verbs.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
