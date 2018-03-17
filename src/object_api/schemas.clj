(ns object-api.schemas
      (:use compojure.core)
      (:use cheshire.core)
      (:use ring.util.response))

    ;; Returns all available type schemas
    (defn get-all-schemas []
      (response (vec
                  (.list 
                    (clojure.java.io/file "./src/object_api/schemas/."))) ))
    
    ;; Returns a selected schema
    (defn get-schema [id]
      (def schema (str "./src/object_api/schemas/" id))
        (cond
          (.exists (clojure.java.io/as-file schema))
            (response (clojure.java.io/file schema ))
            :else {:status 404}))