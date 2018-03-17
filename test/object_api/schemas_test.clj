(ns object-api.schemas-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [clojure.data.json :as json]
            [object-api.application :refer :all]))

(deftest schema-tests
  ;;person.json only schema at this point
  (testing "available schemas"
    (is (= (app (mock/request :get "/schemas"))
      {:status  200
       :headers {"Content-Type" "application/json"}
       :body    "[\"person.json\"]"})))
  (testing "Schema not found"
    (let [response (app (mock/request :get "/schemas/foobar.json"))]
      (is (= (:status response) 404)))))
