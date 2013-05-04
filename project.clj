(defproject verbs "0.1.0-SNAPSHOT"
  :description "french verb training web application"
  :url "http://example.com/FIXME"
  :source-paths ["src/clj"]
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.3"]
                 [ring-json-response "0.2.0"]
                 [org.clojure/data.json "0.2.2"]]
  :plugins [[lein-ring "0.8.2"]]
  :ring {:init verbs.handler/init
         :handler verbs.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
