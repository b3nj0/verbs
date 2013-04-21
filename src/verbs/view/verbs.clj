(ns verbs.view.verbs
  (:use [hiccup.element]
        [ring.util.codec])
  (:require [verbs.verbdb :as verbdb]
            [verbs.view.common :as common]))

(defn grouped-verbs []
  (let [sorted (sort-by first (verbdb/collator) (verbdb/verbs))]
    (group-by (fn [v] (first (first v))) sorted)))

(defn list-verbs []
  (common/scaffold (for [[letter verbs] (grouped-verbs)]
                     [:div [:h5 letter]
                      [:dl.dl-horizontal
                       (for [[v tr] verbs]
                         (list [:dt (link-to (str "/verb/" (url-encode v)) v)] [:dd tr]))]
                      [:hr]])))
