(ns verbs.view.test
  (:require [verbs.view.common :as common]))

(defn test []
  (common/scaffold [:div "HELLO"]))
