(ns learning.core)

(println "starting core")

(def numbers [3 5 8 10])

(println numbers)

(println "map * 2" (map #(* % 2) numbers))
(println "map inc" (map inc numbers))

(println "filter > 3" (filter #(> % 3) numbers))

(println "reduce +" (reduce + numbers))

(def order {
            :shipment {:id 1
                       }
            :items [{
                     :name "Mochila Tedge"
                     :value 59.9
                     :quantity 1
                     }
                    {
                     :name "Lanterna Tática"
                     :value 29.9
                     :quantity 3
                     }]
            :freight 12.56
            })

(println order)

(println (-> order
             :items
             (get 0)
             :name))

(defn total-item-value
  [item]
  (* (:value item) (:quantity item)))

(defn order-total-value
  [order]
  (let [items-value
        (reduce +
                (map total-item-value
                     (-> order :items)))]
    (+ items-value (:freight order))
    ))

(def order (assoc order :total-value (order-total-value order)))

(println order)

; destruct dos campos do map de order
(println "atributos do order" (map
                                (fn [[key value]] key)
                                order))

(println (keys order))
(println (vals order))

; (->) thread first (->>) thread last

; sum of unit value
(println (->> order
             :items
             (map #(:value %))
             (reduce +)))

; total sum without freight
(println (->> order
              :items
              (map #(* (:quantity %) (:value %)))
              (reduce +)))

; negate (not) 2 is even
(println (not (even? 2)))

; comp functions
(def not-even (comp not even?))
(println (not-even 2))

; encontrar o total de certificados de todos os clientes
(def clients [{ :nome "Guilherme" :certificados ["Clojure" "Java" "Machine Learning"] }
               { :nome "Paulo" :certificados ["Java" "Ciência da Computação"] }
               { :nome "Daniela" :certificados ["Arquitetura" "Gastronomia"] }])

(println (reduce + (map #(count (:certificados %)) clients)))
(println (->> clients
              (map #(count (:certificados %)))
              (reduce +)))