(ns verbs.test
  (:require [goog.net.XhrIo :as xhr]))

(defn display-results [e]
  (let [json (.getResponseJson (.-target e))]
    (.log js/console json)))

(xhr/send "/raw/verbs" display-results)
