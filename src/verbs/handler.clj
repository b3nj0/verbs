(ns verbs.handler
  (:use [compojure.core]
        [ring.util.json-response])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [verbs.verbdb :as verbdb]
            [verbs.view.verb :as vw-verb]
            [verbs.view.verbs :as vw-verbs]))

(defn init []
  (verbdb/init))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/raw/verbs" [] (json-response (verbdb/verbs)))
  (GET "/raw/verb/:name" [name] (json-response (verbdb/verb (keyword name))))
  (GET "/raw/verbs/tenses" [] (json-response (verbdb/tenses)))
  (GET "/verbs" [] (vw-verbs/list-verbs))
  (GET "/verb/random-verb" [] (vw-verb/random-verb))
  (GET "/verb/:name" [name] (vw-verb/show-verb name))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/api app-routes))
