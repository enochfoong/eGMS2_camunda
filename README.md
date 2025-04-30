## 1. Start project

mvn spring-boot:run

## 2. Bypass CORS issue

start msedge --disable-web-security --user-data-dir="C:\temp\edge-dev"

# Camunda services

## Operate

### username: demo, password: demo

http://192.168.102.231:8081/operate

## Task List

### username: demo, password: demo

http://192.168.102.231:8082/tasklist

## Identity

### username: demo, password: demo

http://192.168.102.231:8084/identity

## Keycloak

### username: admin, password: admin

http://192.168.102.231:18080/auth

## Start containers on 192.168.102.231

cd camunda/camunda-platform/

docker-compose start

## Elasticsearch queries

query index list:
http://192.168.102.231:9200/_cat/indices?v

query an index:
http://192.168.102.231:9200/zeebe-record_process-instance_8.6.6_2025-04-29/_search?pretty (edited) 