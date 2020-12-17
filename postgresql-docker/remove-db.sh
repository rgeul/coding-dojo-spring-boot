#!/bin/bash

echo "stopping container"
docker-compose down

echo "removing volume"
docker volume rm postgresql-docker_spring_dojo_dbdata
