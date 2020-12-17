#!/bin/bash

echo "Creating database 'weather' in 'spring_dojo_postgres' container"
docker exec -it spring_dojo_postgres psql -U postgres -c "create database weather"
