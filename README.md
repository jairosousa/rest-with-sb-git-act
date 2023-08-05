# Rest with Spring Boot e Git Action

[![Docker Hub Repo](https://img.shields.io/docker/pulls/jnsousa/rest-with-sb-git-act.svg)](https://hub.docker.com/repository/docker/jnsousa/rest-with-sb-git-act)

# MySQL
```shell
docker run --name dev-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=bankdb -d -p 3306:3306 mysql
```

# Jar
```shell
mvn clean package -DskipTests=true
```

# Docker Compose
```shell
docker compose up -d --build
```
