(ns object-api.routes
      (:use compojure.core)
      (:require [compojure.route :as route]
                [object-api.controllers.schema-controller :as schema]))

    (defroutes app-routes
      (context "/schemas" [] (defroutes schema-routes
        (GET "/" [] (schema/get-all-schemas))
        (GET "/:id" [id] (schema/get-schema id))
      (route/not-found "Not Found"))))
