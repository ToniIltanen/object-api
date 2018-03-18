(ns object-api.schema-controller-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [webjure.json-schema.validator :refer [validate]]
            [cheshire.core :as cheshire]
            [object-api.application :refer :all]))

;; Result schema for every query response
(def resultSchema (clojure.java.io/reader "./src/object_api/schemas/result.json"))

(deftest schema-tests

  (testing "available schemas"
    (is (= (app (mock/request :get "/schemas"))
      {:status  200
       :headers {"Content-Type" "application/json"}
       :body    "[\"article.json\",\"person.json\",\"resourceInfo.json\",\"result.json\"]"})))

  (testing "Schema not found"
    (let [response (app (mock/request :get "/schemas/foobar.json"))]
      (is (= (:status response) 404))))

  (testing "Article query result validation"
      (def articles (clojure.java.io/reader "./test/response_templates/articles.json"))
      (is (= (validate (cheshire/parse-stream resultSchema)
                        (cheshire/parse-stream articles)) nil)))

    (testing "Persons query result validation"
      (def persons (clojure.java.io/reader "./test/response_templates/persons.json"))
      (is (= (validate (cheshire/parse-stream resultSchema)
                        (cheshire/parse-stream persons)) nil))))
    
