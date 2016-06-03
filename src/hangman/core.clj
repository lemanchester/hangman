(ns hangman.core
  (:gen-class)
  (:use faker.name faker.lorem faker.internet)
)

(def lives-total 9)
(def secret-word (domain-word))

(defn lose [] (print "You lose"))
(defn won [] (print "You won"))

(defn missing-letters [word hits]
  (remove (fn [letter] (contains? hits (str letter))) word)
)

(defn guessed-word? [word hits]
  (empty? (missing-letters word hits))
)

(defn read-letter![](read-line))

(defn hit? [guess word] (.contains word guess))

(defn print-hang
  [lives words hits]
  (println "Lives " lives)
  (doseq [letter (seq words)]
    (if
      (contains? hits (str letter))
        (print letter " ")
        (print "_" " ")))
  (println))

(defn game
  [lives word hits]
  (print-hang lives word hits)
  (cond
    (= lives 0) (lose)
    (guessed-word? word hits) (won)
    :else
    (let [guess (read-letter!)]
      (do
        (if (hit? guess word)
          (do
            (println "You have guessed a letter!")
            (recur lives word (conj hits guess)))
          (do
            (println "Wrong letter! You have missed a life!")
            (recur (dec lives) word hits)))))))

(defn start-game [] (game lives-total secret-word #{}))

(defn -main
  [& args]
  (start-game))
