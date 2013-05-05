(ns verbs.test
  (:use-macros
   [dommy.macros :only [sel1]])
  (:require [goog.net.XhrIo :as xhr]
            [dommy.core :as dommy]))


;; data

(def verbs (atom {}))

;; utils

(defn log [v]
  (.log js/console v))



;; initialisation

(defn init []
  ;; request and store all the verbs from the backend.  then attach
  (xhr/send "/raw/verbs" (fn [e] (reset! verbs (.getResponseJson (.-target e)))))

  ;; wire up event handlers
  (dommy/listen! (sel1 :#conjugate) :click (fn [e] (log "Clicked") (log e))))

(set! (.-onload js/window) init)
