(ns verbs.verbdb
  (:require [clojure.data.json :as json])
  (:import [java.text Collator]
           [java.util Locale]))

(def all (atom {}))

(defn init []
  (reset! all (json/read-str (slurp "resources/private/french_verbs.json")
                             :key-fn keyword))
  "")

(defn verb [name]
  (@all (keyword name)))

(defn verbs []
  (map (fn [v] [(:verb v) (:translation v)]) (vals @all)))

(defn tenses []
  (keys ((verb :partir) :tenses)))

(defn collator []
  "create a collator for sorting french words (accounting for accents)"
  (Collator/getInstance (Locale/FRANCE)))
