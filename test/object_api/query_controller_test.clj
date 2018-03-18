(ns object-api.query-controller-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [webjure.json-schema.validator :refer [validate]]
            [cheshire.core :as cheshire]
            [object-api.application :refer :all]))

;; Result schema for every query response

(def not-nil? (complement nil?))

(deftest query-tests

  (testing "Invalid Route test"
    (let [response (app (mock/request :get "/query"))]
      (is (= 404 (:status response)))))

  (testing "Invalid Query schema test"
    (def invalid_query (clojure.java.io/reader "./test/request_templates/invalid_query.json"))
    (def querySchema (clojure.java.io/reader "./src/object_api/schemas/query.json"))
      (is (not-nil? (validate (cheshire/parse-stream querySchema)
                              (cheshire/parse-stream invalid_query))))))

    
