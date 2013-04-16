(ns verbs.view.verb
  (:use [hiccup.element]
        [hiccup.util])
  (:require [verbs.verbdb :as verbdb]
            [verbs.view.common :as common]))

(defn apostrophise [pronoun word]
  "transfrom je habite into j'habite"
  (if (contains? #{\h \e \é \ê \a \o \u \i} (first word))
    (str (first pronoun) "'")
    pronoun))

(defn show-tense [name tense]
  (let [[je tu il nous vous ils] tense
        row (fn [lbl val] [:tr [:td.muted.text-right lbl] [:td val]])]
    [:div.span4
     [:h5 name]
     [:table.table-condensed
      (row (apostrophise "je" je) je)
      (row "tu" tu)
      (row "il/elle/on" il)
      (row "nous" nous)
      (row "vous" vous)
      (row "ils/elles" ils)]]))

(defn show-imperatif [name tense]
  (let [[tu nous vous] tense
        row (fn [lbl val] [:tr [:td.muted.text-right lbl] [:td val]])]
    [:div.span4
     [:table.table-condensed
      (row "tu" tu)
      (row "nous" nous)
      (row "vous" vous)]]))

(defn show-verb [name]
  (let [v (verbdb/verb name)
        ts (:tenses v)]
    (common/scaffold [:div
                      [:h3 (:verb v) " " [:small (:translation v)]]
                      [:div [:h4 "indicatif"]
                       [:div.row
                        (show-tense "présent" (:présent ts))
                        (show-tense "passé composé" ((keyword "passé composé") ts))
                        (show-tense "imparfait" (:imparfait ts))]
                       [:div.row
                        (show-tense "plus-que-parfait" (:plus-que-parfait ts))
                        (show-tense "futur" (:futur ts))
                        (show-tense "futur proche" ((keyword "aller + infinitif") ts))]]
                      [:div [:h4 "impératif"]
                       [:div.row
                        (show-imperatif "impératif" (:impératif ts))]]
                      [:div [:h4 "conditionel"]
                       [:div.row
                        (show-tense "conditionnel" (:conditionnel ts))
                        (show-tense "conditionnel passé" ((keyword "conditionnel passé") ts))
                        ]]
                      [:div [:h4 "subjonctif"]
                       [:div.row
                        (show-tense "subjonctif" (:subjonctif ts))
                        (show-tense "subjonctif passé" ((keyword "subjonctif passé") ts))
                        ]]])))
