# keyval-collection-parse

A Clojure library for parsing a list of vectors into a vector of maps.

# Example

```clj
(parse '(["Attribute1" "Attribute2" "Attribute3"] 
         ["Value1" "Value2" "Value3"]
         ["Value4" "Value5" "Value6"]))
```

Output

```
[{:Attribute1 "Value1", :Attribute2 "Value2", :Attribute3 "Value3"}
 {:Attribute1 "Value4", :Attribute2 "Value5", :Attribute3 "Value6"}]
```

Works great with the list returned from the `read-csv` method in [clojure/data.csv](https://github.com/clojure/data.csv).

# Method

## parse

Creates a vector of maps using the items in the first vector as attributes matched with the values found in the subsequent vectors. The primary use case was string attributes and either string or numeric values, but the method is pretty open to what types (or even collections) it will allow as either attributes or values.

# Usage

```
usage: (parse collection)
  collection: Expected to be a list or vector of vectors.
  
  Creates a vector of maps using the first vector as attributes
  and matches them with values from the subsequent vectors.
```

# Install

[Leiningen](https://github.com/technomancy/leiningen) dependency information:

    [keyval-collection-parse "0.1.0"]

[Maven](http://maven.apache.org/) dependency information:

    <dependency>
      <groupId>org.clojure</groupId>
      <artifactId>data.csv</artifactId>
      <version>0.1.3</version>
    </dependency>

# Test
`lein tests`

# License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
