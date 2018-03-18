(ns object-api.routes
      (:use compojure.core)
      (:require [compojure.route :as route]
                [object-api.controllers.schema-controller :as schemaController]
                [object-api.controllers.query-controller :as queryController]))

    (defroutes app-routes
      (context "/schemas" [] (defroutes schema-routes
        (GET "/" [] (schemaController/get-all-schemas))
        (GET "/:id" [id] (schemaController/get-schema id))
      (route/not-found "Not Found")))
      (context "/query" [] (defroutes query-routes
        (POST "/:query" [query] (queryController/query query))
      (route/not-found "Not Found"))))
