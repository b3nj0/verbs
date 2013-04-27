(ns verbs.view.test.identify-a-verb
  (:use [hiccup.form])
  (:require [verbs.verbdb :as verbdb]
            [verbs.view.common :as common]))

(defn ask-question []
  (let [verb (verbdb/verb (verbdb/random-verb))]
    (common/scaffold
     [:div
      [:form
       [:fieldset
        (label :answer (:translation verb))
        (text-field "answer")
        [:div.row
         [:button.span2.btn.btn-primary "Next"]
         [:button.span1.btn "Cheat"]]]]])))
