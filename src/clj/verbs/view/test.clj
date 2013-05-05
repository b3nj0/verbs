(ns verbs.view.test
  (:use [hiccup element page])
  (:require [verbs.view.common :as common]))

(defn lnk [id lbl]
  [:a {:id id :href (str "#" (name id))} lbl])

(defn test []
  (common/scaffold {:includes (include-js "/cljs/verb-test.js")}
                   [:div.row
                    [:div#content.span9]
                    [:div#settings.span3
                     [:ul.nav.nav-list
                      [:li.nav-header "Verb Tests"]
                      [:li (lnk :conjugate "Conjugate")]
                      [:li (lnk :name-the-verb "Name the verb")]
                      [:li.nav-header "Settings"]
                      [:li (lnk :select-verbs "Select verbs")]
                      [:li (lnk :select-tenses "Select tenses")]]]]))
