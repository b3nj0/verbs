(ns verbs.test
  (:require [goog.net.XhrIo :as xhr]))

(def verbs (atom {}))

;; request and store all the verbs from the backend
(xhr/send "/raw/verbs" (fn [e] (reset! verbs (.getResponseJson (.-target e)))))
