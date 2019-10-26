#!/bin/bash

# show endpoint mappings just like rake routes

ENDPOINT=${1:-"customers"}
URL=${2:-"http://localhost:8080/actuator/mappings"}

JQ_PATH=".contexts.application.mappings.dispatcherServlets.dispatcherServlet[].predicate"
JQ_SELECT="select(contains(\"$ENDPOINT\"))"

curl -s $URL | \
    jq -r "$JQ_PATH | $JQ_SELECT " | \
    tr -d '{}' | \
    sort
