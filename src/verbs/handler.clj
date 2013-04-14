(ns verbs.handler
  (:use [compojure.core]
        [ring.util.json-response])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [verbs.verbdb :as verbdb]
            [verbs.view.verbs :as vwverbs]))

(defn init []
  (verbdb/init))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/raw/verbs" [] (json-response (verbdb/verbs)))
  (GET "/raw/verb/:name" [name] (json-response (verbdb/verb (keyword name))))
  (GET "/raw/verbs/tenses" [] (json-response (verbdb/tenses)))
  (GET "/verbs" [] (vwverbs/list-verbs))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/api app-routes))
