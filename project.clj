(defproject object-api "0.1.0-SNAPSHOT"
  :description "Object Api - data router for services"
  :url "https://github.com/ToniIltanen/object-api"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-json "0.1.2"]
                 [c3p0/c3p0 "0.9.1.2"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [org.clojure/data.json "0.2.6"]
                 [com.h2database/h2 "1.3.168"]
                 [cheshire "4.0.3"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler object-api.application/app}
  :profiles
      {:dev {:dependencies [[ring-mock "0.1.3"]]}})