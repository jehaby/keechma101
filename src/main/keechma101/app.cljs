(ns keechma101.app
  (:require [keechma.next.core :as core]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]

            [keechma101.controllers]))

(defn- page= [page]
  (fn [router-ctr-data]
    (when (= page (-> router-ctr-data :router :data :name))
      router-ctr-data)))

(def app
  {:keechma/controllers
   {:router {:keechma.controller/params true}

    :profile {:keechma.controller/deps [:router]
              :keechma.controller/params (page= :page/profile)}

    :books-list  {:keechma.controller/deps   [:router]
                  :keechma.controller/params (page= :page/books-list)}

    :books-show  {:keechma.controller/deps   [:router]
                  :keechma.controller/params (page= :page/books-show)}
    }})

(def app-instance (core/start! app))

(def routes
  ["/"
   ["books" {:name :page/books-list}]
   ["books/{id}" {:name :page/books-show}]
   ["profile" {:name :page/profile}]])

(def router (rf/router routes))

(defn on-navigate [new-match]
  (prn "IN on-navigate " new-match)
  (core/dispatch app-instance :router new-match))

(defn init-router! []
  (rfe/start! router on-navigate {:use-fragment true}))

(defn ^:export main []
  (init-router!))

(comment
  (core/get-derived-state app-instance)                                ;; {:counter 0}

  (core/dispatch app-instance :router :books123)

  (core/get-derived-state app-instance)                                ;; {:counter 1}

  (core/subscribe app-instance :router #(prn "IN SUB HANDLER " %))
  )
