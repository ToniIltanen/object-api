(ns object-api.application
      (:import com.mchange.v2.c3p0.ComboPooledDataSource)
      (:use compojure.core)
      (:use cheshire.core)
      (:use ring.util.response)
      (:require [compojure.handler :as handler]
                [ring.middleware.json :as middleware]
                [clojure.java.jdbc :as sql]
                [object-api.routes :as routes]
                [compojure.route :as route])) 
    
    (def app
        (-> (handler/api routes/app-routes)
            (middleware/wrap-json-body)
            (middleware/wrap-json-response)))