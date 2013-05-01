(ns verbs.view.common
  (:use [hiccup.element]
        [hiccup.page]))

(defn- head [headers]
  [:head
   [:meta {:charset "utf-8"}]
   [:title "verbs"]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
   [:link {:href "/css/bootstrap.min.css" :rel "stylesheet" :media "screen"}]
   "<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->"
   "<!--[if lt IE 9]>"
   (include-js "/js/html5shiv.js")
   "<![endif]-->"
   "<!-- Fav and touch icons -->"
   [:link {:rel "apple-touch-icon-precomposed" :sizes "144x144" :href "/ico/apple-touch-icon-144-precomposed.png"}]
   [:link {:rel "apple-touch-icon-precomposed" :sizes "114x114" :href "/ico/apple-touch-icon-114-precomposed.png"}]
   [:link {:rel "apple-touch-icon-precomposed" :sizes "72x72" :href "/ico/apple-touch-icon-72-precomposed.png"}]
   [:link {:rel "apple-touch-icon-precomposed" :href "/ico/apple-touch-icon-57-precomposed.png"}]
   [:link {:rel "shortcut icon" :href "/ico/favicon.png"}]
   (when (:includes headers) (:includes headers))
   ])

(defn masthead []
  [:div.masthead
   [:ul.nav.nav-pills.pull-right
    [:li.active (link-to "/" "Home")]
    [:li (link-to "/verbs" "Verbs")]
    [:li (link-to "/verb/random-verb" "Random")]
    [:li (link-to "/test" "Test")]]
   [:h3.muted "Verbs"]])

(defn- body [content]
  [:body
   [:div.container
    (masthead)
    [:hr]
    content]
   (include-js "http://code.jquery.com/jquery.js"
               "/js/bootstrap.min.js")])

(defn scaffold
  ([headers content]
     (html5
      (head headers)
      (body content)))
  ([content]
     (scaffold {} content)))
