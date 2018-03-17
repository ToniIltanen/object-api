(ns object-api.routes
      (:use compojure.core)
      (:require [compojure.route :as route]
                [object-api.schemas :as schema]))

    (defroutes app-routes
      (context "/schemas" [] (defroutes schema-routes
        (GET "/" [] (schema/get-all-schemas))
      (route/not-found "Not Found"))))
