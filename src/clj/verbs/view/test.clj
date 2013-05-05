(ns verbs.view.test
  (:use [hiccup element page])
  (:require [verbs.view.common :as common]))

(defn test []
  (common/scaffold {:includes (include-js "/cljs/verb-test.js")}
                   [:div.row
                    [:div#content.span9]
                    [:div#settings.span3
                     [:ul.nav.nav-list
                      [:li.nav-header "Verb Tests"]
                      [:li (link-to "#" "Conjugate")]
                      [:li (link-to "#" "Name the verb")]
                      [:li.nav-header "Settings"]
                      [:li (link-to "#" "Select verbs")]
                      [:li (link-to "#" "Select tenses")]]]]))
