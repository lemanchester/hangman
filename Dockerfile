FROM clojure

RUN mkdir -p /usr/src/hangman

WORKDIR /usr/src/hangman

COPY project.clj /usr/src/hangman/

RUN lein deps

COPY . /usr/src/hangman

RUN mv "$(lein uberjar | sed -n 's/^Created \(.*standalone\.jar\)/\1/p')" app-standalone.jar

CMD ["java", "-jar", "app-standalone.jar"]
