(ns verbs.test
  (:use-macros
   [dommy.macros :only [node sel1 sel text]])
  (:require [dommy.core :as dommy]
            [goog.net.XhrIo :as xhr]))

;; data

(def verbs (atom {}))

;; utils

(defn log [v]
  (.log js/console v))

;; name the verb

(defn on-name-the-verb []
  (let [guess (dommy/value (sel1 "#guess"))
        target (dommy/value (sel1 "#target"))]
    (log (str "Verb named " guess " expected " target))))

(defn play-name-the-verb []
  (log "Name the verb")
  (let [content (sel1 :#content)
        verb (rand-nth @verbs)
        verb-en (get verb 1)
        verb-fr (get verb 0)]
    (dommy/replace-contents! content [:form
                                      [:legend "Name the verb"]
                                      [:fieldset
                                       [:label.span3 [:span.pull-right verb-en]]
                                       [:div.input-append
                                        [:input#target {:type "hidden" :value verb-fr}]
                                        [:input#guess.input-medium {:type "text"}]
                                        [:button#go.btn {:type "submit"} "Go!"]]]])
    (dommy/listen! (sel1 :#go) :click (fn [e] (on-name-the-verb)))))

;; conjugate the verb

(defn play-conjugate-the-verb []
  (log "Conjugate the verb"))

;; select verbs + tenses

(defn select-verbs-and-tenses []
  (log "Select verbs + tenses")
  (log (sel1 :#content))
  (log (dommy/text (sel1 :#content)))
  (dommy/set-text! (sel1 :#content) "HOLA"))

;; initialisation

(defn init []
  ;; request and store all the verbs from the backend.  then attach
  (xhr/send "/raw/verbs" (fn [e] (reset! verbs (.getResponseJson (.-target e)))))

  ;; wire up event handlers
  (dommy/listen! (sel1 :#conjugate-the-verb) :click (fn [e] (play-conjugate-the-verb)))
  (dommy/listen! (sel1 :#name-the-verb) :click (fn [e] (play-name-the-verb)))
  (dommy/listen! (sel1 :#select-verbs-and-tenses) :click (fn [e] (select-verbs-and-tenses))))

(set! (.-onload js/window) init)
