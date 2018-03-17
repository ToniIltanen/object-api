FROM clojure
COPY . /usr/src/object-api
WORKDIR /usr/src/object-api
EXPOSE 3000
CMD ["lein", "ring", "server-headless"]