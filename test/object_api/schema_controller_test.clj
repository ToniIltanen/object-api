(ns object-api.schema-controller-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [webjure.json-schema.validator :refer [validate]]
            [cheshire.core :as cheshire]
            [object-api.application :refer :all]))

;; Result schema for every query response

(def not-nil? (complement nil?))

(deftest schema-tests

  (testing "available schemas"
  (let [response (app (mock/request :get "/schemas"))]
      (def result (cheshire/parse-string (:body response)))
      (is (= 5 (count result)))
      (is (= (:status response) 200))))

  (testing "Schema not found"
    (let [response (app (mock/request :get "/schemas/foobar.json"))]
      (is (= 404 (:status response)))))

  (testing "Invalid article query result validation"
    (def resultSchema (clojure.java.io/reader "./src/object_api/schemas/result.json"))
    (def articles_invalid (clojure.java.io/reader "./test/response_templates/articles_invalid.json"))
    (is (not-nil? (validate (cheshire/parse-stream resultSchema)
                            (cheshire/parse-stream articles_invalid)))))

  (testing "Article query result validation"
    (def resultSchema (clojure.java.io/reader "./src/object_api/schemas/result.json"))
    (def articles (clojure.java.io/reader "./test/response_templates/articles.json"))
    (is (= nil (validate (cheshire/parse-stream resultSchema)
                         (cheshire/parse-stream articles)))))

  (testing "Invalid persons query result validation"
    (def resultSchema (clojure.java.io/reader "./src/object_api/schemas/result.json"))
    (def persons_invalid (clojure.java.io/reader "./test/response_templates/persons_invalid.json"))
    (is (not-nil? (validate (cheshire/parse-stream resultSchema)
                            (cheshire/parse-stream persons_invalid)))))

  (testing "Persons query result validation"
    (def resultSchema (clojure.java.io/reader "./src/object_api/schemas/result.json"))
    (def persons (clojure.java.io/reader "./test/response_templates/persons.json"))
    (is (= nil (validate (cheshire/parse-stream resultSchema)
                     (cheshire/parse-stream persons))))))
    
