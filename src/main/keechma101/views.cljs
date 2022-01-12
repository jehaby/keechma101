(ns keechma101.views
  (:require
   [reagent.dom :as rdom]))

(defn render! [view]
  (rdom/render view
               (.getElementById js/document "app")))

(defn render-not-found-page! []
  (render! [:div
            [:h1 "404"]
            [:div "page not found"]]))

(defn profile [{:keys [name email] :as _user}]
  [:div
   [:h2 "Profile"]
   [:div "name: " name]
   [:div "email: " email]])

(defn books-list [books]
  [:div
   [:h2 "Books"]
   (for [{:keys [id title author]} books]
     ^{:key id}
     [:div
      [:h4 title]
      [:div author]])])

(defn books-show [{:keys [title author] :as _book}]
  [:div
   [:h2 title]
   [:div author]])
