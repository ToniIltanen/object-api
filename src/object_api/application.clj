(ns object-api.application
      (:require [compojure.handler :as handler]
                [ring.middleware.json :as middleware]
                [object-api.routes :as routes])) 
    
    (def app
        (-> (handler/api routes/app-routes)
            (middleware/wrap-json-body)
            (middleware/wrap-json-response)))