# Object API

Object API is a data harvester and response combiner for local or external services to create dynamic objects from multiple sources and mapping the results together.

Object API *will* have and **awesome** queryscheme, which is based on: 

    1. What type of main object do we want 
    2. What external mapped data do you want to include on the main object
    3. What filters you wish to assign on the main object query

## Status

Object API is still under development, and the first release is yet to come.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## Testing

To start tests, run:

    lein eftest

(or run the original tester, lein test)

## Docker

Docker image is automatically built from the master branch as latest. 
Image can be found at: https://hub.docker.com/r/toniiltanen/object-api/

## License

Copyright © 2018 Toni Iltanen

Distributed under the MIT License
