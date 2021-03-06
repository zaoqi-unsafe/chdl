(ns chdl.gamma.render
  (:require [chdl.alpha.proto :as proto]
            [chdl.gamma.protocols :as gproto]))

(defn to-vhdl
  "Renders a set of chdl items"
  [& stuff]
  (apply str (interpose "\n"
    (map #(if (satisfies? gproto/symbol-value %)
              (gproto/construct %)
              (proto/to-str %)) (flatten stuff)))))

(defn vhdl-file
  "Given a filename and a set of chdl items, renders and writes them to the
  file"
  [fname & stuff]
  (spit fname (apply to-vhdl stuff)))
