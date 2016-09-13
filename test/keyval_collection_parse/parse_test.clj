(ns keyval-collection-parse.parse-test
  (:require [clojure.test :refer :all]
            [keyval-collection-parse.parse :refer :all]))

(def ^{:private true} empty-list
  '())

(def ^{:private true} empty-vector
  [])

(def ^{:private true} list-of-empty-vectors
  '([] []))

(def ^{:private true} vector-of-empty-vectors
  [[] []])

(deftest empty-collections
  (let [returned-collection (parse empty-list)]
    (is (= returned-collection [])))
  (let [returned-collection (parse list-of-empty-vectors)]
    (is (= returned-collection [{}])))
  (let [returned-collection (parse empty-vector)]
    (is (= returned-collection [])))
  (let [returned-collection (parse vector-of-empty-vectors)]
    (is (= returned-collection [{}]))))

(def ^{:private true} list-of-vectors-of-strings
  '(["Attribute1" "Attribute2" "Attribute3" "Attribute4"] ["Value1" "Value2" "Value3" "Value4"] ["Value5" "Value6" "Value7" "Value8"]))

(def ^{:private true} vector-of-vectors-of-strings
  [["Attribute1" "Attribute2" "Attribute3" "Attribute4"] ["Value1" "Value2" "Value3" "Value4"] ["Value5" "Value6" "Value7" "Value8"]])

(def ^{:private true} list-of-vectors-of-strings-numeric-values
  '(["Attribute1" "Attribute2" "Attribute3" "Attribute4"] [1 2 3 4] [5 6 7 8]))

(def ^{:private true} vector-of-vectors-of-strings-numeric-values
  [["Attribute1" "Attribute2" "Attribute3" "Attribute4"] [1 2 3 4] [5 6 7 8]])

(deftest happy-paths
  (let [returned-collection (parse list-of-vectors-of-strings)]
    (is (= returned-collection [{:Attribute1 "Value1",
                                 :Attribute2 "Value2",
                                 :Attribute3 "Value3",
                                 :Attribute4 "Value4"}
                                {:Attribute1 "Value5",
                                 :Attribute2 "Value6",
                                 :Attribute3 "Value7",
                                 :Attribute4 "Value8"}])))
  (let [returned-collection (parse vector-of-vectors-of-strings)]
    (is (= returned-collection [{:Attribute1 "Value1",
                                 :Attribute2 "Value2",
                                 :Attribute3 "Value3",
                                 :Attribute4 "Value4" }
                                {:Attribute1 "Value5",
                                 :Attribute2 "Value6",
                                 :Attribute3 "Value7",
                                 :Attribute4 "Value8"}])))
  (let [returned-collection (parse list-of-vectors-of-strings-numeric-values)]
    (is (= returned-collection [{:Attribute1 1,
                                 :Attribute2 2,
                                 :Attribute3 3,
                                 :Attribute4 4 }
                                {:Attribute1 5,
                                 :Attribute2 6,
                                 :Attribute3 7,
                                 :Attribute4 8}])))
  (let [returned-collection (parse list-of-vectors-of-strings-numeric-values)]
    (is (= returned-collection [{:Attribute1 1,
                                 :Attribute2 2,
                                 :Attribute3 3,
                                 :Attribute4 4 }
                                {:Attribute1 5,
                                 :Attribute2 6,
                                 :Attribute3 7,
                                 :Attribute4 8}]))))

(def ^{:private true} list-of-vectors-more-attributes-than-values
  '(["Attribute1" "Attribute2" "Attribute3" "Attribute4"] ["Value1" "Value2" "Value3"]))

(def ^{:private true} vector-of-vectors-more-attributes-than-values
  [["Attribute1" "Attribute2" "Attribute3" "Attribute4"] ["Value1" "Value2" "Value3"]])

(def ^{:private true} list-of-vectors-less-attributes-than-values
  '(["Attribute1" "Attribute2" "Attribute3"] ["Value1" "Value2" "Value3" "Value4"]))

(def ^{:private true} vector-of-vectors-less-attributes-than-values
  [["Attribute1" "Attribute2" "Attribute3"] ["Value1" "Value2" "Value3" "Value4"]])

(deftest count-mismatches
  (let [returned-collection (parse list-of-vectors-more-attributes-than-values)]
    (is (= returned-collection [{:Attribute1 "Value1",
                                 :Attribute2 "Value2",
                                 :Attribute3 "Value3",
                                 :Attribute4  nil}])))
  (let [returned-collection (parse vector-of-vectors-more-attributes-than-values)]
    (is (= returned-collection [{:Attribute1 "Value1",
                                 :Attribute2 "Value2",
                                 :Attribute3 "Value3",
                                 :Attribute4 nil }])))
  (let [returned-collection (parse list-of-vectors-less-attributes-than-values)]
    (is (= returned-collection [{:Attribute1 "Value1",
                                 :Attribute2 "Value2",
                                 :Attribute3 "Value3"}])))
  (let [returned-collection (parse vector-of-vectors-less-attributes-than-values)]
    (is (= returned-collection [{:Attribute1 "Value1",
                                 :Attribute2 "Value2",
                                 :Attribute3 "Value3" }]))))

(def ^{:private true} list-of-vectors-numeric-attributes
  '([1 2 3 4] ["Value1" "Value2" "Value3" "Value4"]))

(def ^{:private true} vector-of-vectors-numeric-attributes
  [[1 2 3 4] ["Value1" "Value2" "Value3" "Value4"]])

(deftest numeric-attributes
  (let [returned-collection (parse list-of-vectors-numeric-attributes)]
    (is (= returned-collection [{:1 "Value1",
                                 :2 "Value2",
                                 :3 "Value3",
                                 :4 "Value4"}])))
  (let [returned-collection (parse vector-of-vectors-numeric-attributes)]
    (is (= returned-collection [{:1 "Value1",
                                 :2 "Value2",
                                 :3 "Value3",
                                 :4 "Value4"}]))))

(def ^{:private true} list-of-one-vector
  '(["Attribute1" "Attribute2" "Attribute3" "Attribute4"]))

(def ^{:private true} vector-of-one-vector
  [["Attribute1" "Attribute2" "Attribute3" "Attribute4"]])

(deftest one-row
  (let [returned-collection (parse list-of-one-vector)]
    (is (= returned-collection [])))
  (let [returned-collection (parse vector-of-one-vector)]
    (is (= returned-collection []))))
