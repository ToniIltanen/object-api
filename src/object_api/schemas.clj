(ns object-api.schemas
      (:use compojure.core)
      (:use cheshire.core)
      (:use ring.util.response)
      (:require [ring.middleware.json :as middleware]))

    (defn get-all-schemas []
      (response {:foo "bar"} ))