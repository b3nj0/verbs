(ns verbs.view.test
  (:use [hiccup.page])
  (:require [verbs.view.common :as common]))

(defn test []
  (common/scaffold {:includes (include-js "/cljs/verb-test.js")}
                   [:div.row
                    [:div#content.span8]
                    [:div#settings.span4]]))
