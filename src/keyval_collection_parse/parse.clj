(ns keyval-collection-parse.parse)

(defn to-map
  "takes an attribute collection and a value collection and creates a map"
  ([attributes values] (to-map attributes values {}))
  ([attributes values output-map]
    (if (empty? attributes)
      output-map
      (recur (rest attributes) (rest values) (assoc output-map (keyword (str (first attributes))) (first values))))))

(defn parse
  "takes a collection of collections and creates a map using the first collection as attributes"
  ([lines]
    (parse lines []))
  ([lines output-vector]
   (parse (rest lines) output-vector (first lines)))
  ([lines output-vector attributes]
    (if (empty? lines) 
      output-vector
      (recur (rest lines) (conj output-vector (to-map attributes (first lines))) attributes))))
