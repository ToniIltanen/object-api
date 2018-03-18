(ns object-api.controllers.query-controller
      (:use ring.util.response)
      (:require [webjure.json-schema.validator :refer [validate]]
                [cheshire.core :as cheshire]))

    (def not-nil? (complement nil?))
    
    (defn perform [query]
        (response {:status 402}))

    (defn query [query]
      (def querySchema (clojure.java.io/reader "./src/object_api/schemas/query.json"))
      (if (not-nil? (validate (cheshire/parse-stream querySchema) query))
          (perform query) {:status 400}))