(ns verbs.test
  (:use-macros
   [dommy.macros :only [node sel1 sel text]])
  (:require [dommy.core :as dommy]
            [goog.net.XhrIo :as xhr]))

;; data

(def verbs (atom {}))

; keep track of number correct answers from number of attempts
(def score (atom [0 0]))

;; utils

(defn log [v]
  (.log js/console v))

;; score

(defn reset-score []
  (reset! score [0 0]))

(defn score-plus [v]
  "add v to the current score (and increase total goes by one)"
  (swap! score (fn [[c t]] [(+ c v) (inc t)])))

(defn score-as-% []
  (let [[c t] @score]
    (* 100 (/ c t))))

;; name the verb

(defn on-name-the-verb []
  (let [guess (dommy/value (sel1 "#guess"))
        target (dommy/value (sel1 "#target"))
        correct? (= guess target)]
    (log (str "Verb named " guess " expected " target))
    (score-plus (if correct? 1 0))
    (log @score)
    (log (score-as-%))
    (if correct?
      (play-name-the-verb)
      (dommy/set-text! (sel1 :#score) (str (score-as-%) "%")))))

(defn on-skip-the-verb []
  (score-plus 0)
  (play-name-the-verb))

(defn play-name-the-verb []
  (log "Name the verb")
  (let [content (sel1 :#content)
        verb (rand-nth @verbs)
        verb-en (get verb 1)
        verb-fr (get verb 0)]
    (dommy/replace-contents! content [:div
                                      [:form
                                       [:legend "Name the verb"]
                                       [:fieldset
                                        [:label.span3 [:span.pull-right verb-en]]
                                        [:div.input-append
                                         [:input#target {:type "hidden" :value verb-fr}]
                                         [:input#guess.input-medium {:type "text"}]
                                         [:button#go.btn {:type "submit"} "Go!"]
                                         [:button#skip.btn "Skip"]]]]
                                      [:div#score (str (score-as-%) "%")]])
    (.focus (sel1 :#guess))
    (dommy/listen! (sel1 :#go) :click (fn [e] (on-name-the-verb)))
    (dommy/listen! (sel1 :#skip) :click (fn [e] (on-skip-the-verb)))))

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
