# Jenkins

1. pull jenkins images the run it
   docker pull jenkins/jenkins:lts-alpine-jdk17
   docker run -p 11002:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home --restart=on-failure jenkins/jenkins:lts-alpine-jdk17

11002:8080 ==> access to jenkins by default
50000:50000 ==> communication between slave & master occur
attach a volume ==> jenkins_home:/var/jenkins_home

2. create an admin user
   username/password: jenkins-user/test
   email: <senanisammy@gmail.com>

3. create my pipeline

- Freestyle: single task (test)
- pipeline: chain multiple tasks (stages) --> test, build, deploy, ...
- multibranch: apply pipelie to multiple branches of a repository

4. create a multi branch pipeline
   github url
   add credentials (github)
   select branches concerned by the pipeline (regular expression)

create credentials:

- system: only available for jenkins server (pipeline scope)
- gloabl: by everyone ==> pipeline, jobs, ...

NB

- system: idéale pour des credentials valable pour une seul pipeline/projet
- global: idéale pour des credentials globales partagé par plusieurs pipelines/projets

type: username/password

pipeline:
declarative: using jenkinsfile (easier, template to fill, less complex logic)
scripted: groovy syntax (handle more complex logic, require more understanding)

**_ docker remove all volumes _**
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker volume rm $(docker volume ls -q)

## what is Jenkins ?

## How it works ?

## Pros

## Cons

# Keycloak

## what is Keycloak ?

it's an IAM identity and access management tool which is open source widely used by campanies it provides authorization, authentication and user account management to access single / mutiple applications using SSO & identity federation.

## How it works ?

- create a realm
- create clients
- create user(s)
- assign a role for the client
- enable user register & setup the register

NB

- it's token-based auth: oauth 2.0and openID connect it provides us with a identity token (access / refresh)
- grant types:
  - Authorization Code
  - implicit
  - Client Credentials
  - refresh token
  - device code
  - Resource Owner Password Credentials

## Pros

- security
- identity federation: integration with other identity providers: google, ...
- open source
- customization
- SSO (Single-Sign-On)

## Cons

- resource itensive
- complexity
- learning curve
- maintenance
-

# Sonarqube

## what is sonarqube ?

it's an open source plateform for continous quality code management & improvement

## How it works ?

- we create a project
- select the technologies involved in it
- create a quality gate with set of metrics: code smell, duplicate code, test coverage, vulnerabilities, ...
- we have report (details of the problem occured)
- dashboard it displays the issue that occured and how to fix it

NB

- if the quality gate is not valid, the pipeline will stop there

## Pros

- detect / identify errors, vulnerabilities, ...
- easy to integrate with CI/CD pipeline
- improve security
- customize according to the project requirements

## Cons

- may have a comple setup
- false positive: may encounter problems (duplicate where not vise versa)
- maintenance overhead: (update the plugins my take some time to do so)
- resource intensive: (consume a lot computation power)

# Elastic Stack

# what is Elastic Stack ?

## How it works ?

## Pros

## Cons

# Docker

## data persistance ?

volume: is good solution
database: is better solution for scalability purposes, separate data from keycloak container, durability, back up data

## data separation from project database ?

- separate database:
  scalability, maintenance, security, performence, isolation,

- same database:
  simplicity, synchronization, shared resources, complexity

## volume in folders' system or docker container ?

- in folders' system: data sharing between system & docker container, data persistance
- in docker container: data is stored is deleted in case container is removed or stopped
