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
    (is (= (app (mock/request :get "/schemas"))
      {:status  200
       :headers {"Content-Type" "application/json"}
       :body    "[\"article.json\",\"person.json\",\"resourceInfo.json\",\"result.json\"]"})))

  (testing "Schema not found"
    (let [response (app (mock/request :get "/schemas/foobar.json"))]
      (is (= (:status response) 404))))

  (testing "Invalid article query result validation"
    (def resultSchema (clojure.java.io/reader "./src/object_api/schemas/result.json"))
    (def articles_invalid (clojure.java.io/reader "./test/response_templates/articles_invalid.json"))
    (is (not-nil? (validate (cheshire/parse-stream resultSchema)
                       (cheshire/parse-stream articles_invalid)))))

  (testing "Article query result validation"
    (def resultSchema (clojure.java.io/reader "./src/object_api/schemas/result.json"))
    (def articles (clojure.java.io/reader "./test/response_templates/articles.json"))
    (is (= (validate (cheshire/parse-stream resultSchema)
                      (cheshire/parse-stream articles)) nil)))

  (testing "Invalid persons query result validation"
    (def resultSchema (clojure.java.io/reader "./src/object_api/schemas/result.json"))
    (def persons_invalid (clojure.java.io/reader "./test/response_templates/persons_invalid.json"))
    (is (not-nil? (validate (cheshire/parse-stream resultSchema)
                       (cheshire/parse-stream persons_invalid)))))

  (testing "Persons query result validation"
    (def resultSchema (clojure.java.io/reader "./src/object_api/schemas/result.json"))
    (def persons (clojure.java.io/reader "./test/response_templates/persons.json"))
    (is (= (validate (cheshire/parse-stream resultSchema)
                     (cheshire/parse-stream persons)) nil))))
    
