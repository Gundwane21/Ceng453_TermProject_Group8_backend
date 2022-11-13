#!/bin/bash

docker build -t gundwane21/monopoly-server . 
docker compose up
#docker network create monopoly-net
#docker run -d --net monopoly-net --name my_mariadb \
#    --env MARIADB_USER=project_group8 --env MARIADB_PASSWORD=6WsY>Za --env MARIADB_ROOT_PASSWORD=teamrhea --env MARIADB_DATABASE=project_group8 \
#    mariadb:10

#docker build --no-cache -t --net monopoly-net gundwane21/gs-spring-boot-docker

#docker run --net monopoly-net -d --rm gundwane21/gs-spring-boot-docker

#docker run -it --network monopoly-net --rm mariadb mariadb -hmy_mariadb -p
