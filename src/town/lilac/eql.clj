(ns town.lilac.eql
  (:require
   [babashka.cli :as cli]
   [clojure.edn :as edn]
   [pyramid.core :as p]))


(def version "0.0.1")


(def cli-spec
  {:query {:ref "<query>"
           :desc "EQL query to run on the input data."
           :alias :q}
   :to-query {:desc "Return a valid EQL query for the entire input data."}})


(defn -main
  [& opts]
  (let [{:keys [opts args]} (cli/parse-args opts {:spec cli-spec})
        data #(if (some? (first args))
                (edn/read-string (first args))
                (edn/read *in*))]
    (cond
      (contains? opts :to-query) (p/data->query (data))
      (contains? opts :query) (->> (:query opts)
                                   (edn/read-string)
                                   (p/pull (data)))
      :else (do (println (str "eql v" version))
                (println "Usage: eql <options> <data>")
                (println "If data is elided, expects it to be sent via stdin.")
                (newline)
                (println (cli/format-opts {:spec cli-spec}))))))


(when (= *file* (System/getProperty "babashka.file"))
  (-main))
