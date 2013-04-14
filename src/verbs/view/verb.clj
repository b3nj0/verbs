(ns verbs.view.verb
  (:use [hiccup.element]
        [hiccup.util])
  (:require [verbs.verbdb :as verbdb]
            [verbs.view.common :as common]))

(defn show-tense [name tense]
  (let [[je tu il nous vous ils] tense
        row (fn [lbl val] [:tr [:td.muted.text-right lbl] [:td val]])]
    [:div.span4
     [:div name]
     [:table.table-condensed
      (row "je" je)
      (row "tu" tu)
      (row "il/elle/on" il)
      (row "nous" nous)
      (row "vous" vous)
      (row "ils/elles" ils)]]))

(defn show-verb [name]
  (let [v (verbdb/verb name)
        ts (:tenses v)]
    (common/scaffold [:div
                      [:h3 (:verb v) " " [:small (:translation v)]]
                      [:div.row
                       (show-tense "présent" (:présent ts))
                       (show-tense "passé composé" ((keyword "passé composé") ts))
                       (show-tense "imparfait" (:imparfait ts))]
                      [:div.row
                       (show-tense "plus-que-parfait"(:plus-que-parfait ts))]])))
