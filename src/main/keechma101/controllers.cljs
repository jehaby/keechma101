(ns keechma101.controllers
  (:require
   [keechma.next.controller :as ctrl]

   [keechma101.db :as db]
   [keechma101.views :as views]
   ))

(derive :router :keechma/controller)

(defmethod ctrl/start :router [_ _ _ _]
  :books)

(defmethod ctrl/handle :router [{:keys [state*]} event _]
  (if event
    (reset! state* event)
    (views/render-not-found-page!)))

(derive :profile :keechma/controller)

(defmethod ctrl/start :profile [_ _ _]
  (prn "IN ctrl/start :profile")
  (views/render! (fnil views/profile db/user)))

(derive :books-list :keechma/controller)

(defmethod ctrl/start :books-list [_ _ _]
  (prn "IN ctrl/start :books-list")
  (views/render! (fnil views/books-list db/books)))

(derive :books-show :keechma/controller)

(defmethod ctrl/start :books-show [_ _ payload]
  (prn "IN ctrl/start :books-show" payload)
  (let [book-id (-> payload :router :parameters :path :id js/parseInt)
        book    (-> (filter #(= book-id (:id %)) db/books) first)]
    (if book
      (views/render! (fnil views/books-show book))
      (views/render-not-found-page!))))
