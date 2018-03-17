FROM clojure
COPY . /usr/src/object-api
WORKDIR /usr/src/object-api
CMD ["lein", "ring server"]