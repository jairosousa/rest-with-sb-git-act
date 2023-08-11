# Rest with Spring Boot e Git Action

[![Docker Hub Repo](https://img.shields.io/docker/pulls/jnsousa/rest-with-sb-git-act.svg)](https://hub.docker.com/repository/docker/jnsousa/rest-with-sb-git-act)
![GitHub Workflow Status (with event)](https://img.shields.io/github/actions/workflow/status/jairosousa/rest-with-sb-git-act/continuos-integrations.yml)
[![Continuos Integrations with GitHub Action](https://github.com/jairosousa/rest-with-sb-git-act/actions/workflows/continuos-integrations.yml/badge.svg)](https://github.com/jairosousa/rest-with-sb-git-act/actions/workflows/continuos-integrations.yml)

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
