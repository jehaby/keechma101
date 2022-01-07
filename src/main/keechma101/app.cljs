(ns keechma101.app)

(-> js/document
    (.getElementById "app")
    (.-innerHTML)
    (set! "Hello Clojure!"))

(prn "HW")
