(ns keechma101.ui.main
  (:require [keechma.next.helix.core :refer [with-keechma use-sub]]
            [keechma.next.helix.lib :refer [defnc]]
            [helix.core :as hx :refer [$]]
            [helix.dom :as d]
            ))

(defnc MainRenderer [props]
  (let [{:keys [page]} (use-sub props :router)]
    (prn "DEBUG in renderer " page)
    (case page
      "home" (d/div "Welcome home")
      (d/div "404"))))

(def Main (with-keechma MainRenderer))
