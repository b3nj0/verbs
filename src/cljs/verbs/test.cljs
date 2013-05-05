(ns verbs.test
  (:use-macros
   [dommy.macros :only [sel1 sel]])
  (:require [goog.net.XhrIo :as xhr]
            [dommy.core :as dommy]))


;; data

(def verbs (atom {}))

;; utils

(defn log [v]
  (.log js/console v))

;; name the verb

(defn play-name-the-verb []
  (log "Name the verb"))

;; conjugate the verb

(defn play-conjugate-the-verb []
  (log "Conjugate the verb"))

;; select verbs + tenses

(defn select-verbs-and-tenses []
  (log "Select verbs + tenses"))

;; initialisation

(defn init []
  ;; request and store all the verbs from the backend.  then attach
  (xhr/send "/raw/verbs" (fn [e] (reset! verbs (.getResponseJson (.-target e)))))

  ;; wire up event handlers
  (dommy/listen! (sel1 :#conjugate-the-verb) :click (fn [e] (play-conjugate-the-verb)))
  (dommy/listen! (sel1 :#name-the-verb) :click (fn [e] (play-name-the-verb)))
  (dommy/listen! (sel1 :#select-verbs-and-tenses) :click (fn [e] (select-verbs-and-tenses))))

(set! (.-onload js/window) init)
