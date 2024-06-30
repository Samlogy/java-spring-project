## keycloak:

- Realms: Isolated environments within Keycloak for managing users and configurations.
- Clients: Applications or services that request authentication from Keycloak.
- Groups: Collections of users managed as a single unit.
-Users: Individuals who can authenticate within a realm.
-Roles: Sets of permissions assigned to users, groups, or clients.
-Client Scopes: Define the permissions and information a client can request.
-Sessions: Authenticated sessions between users or clients and Keycloak.
-Events: Logged actions and occurrences for auditing and monitoring.

** config openid:**
http://localhost:8180/realms/master/.well-known/openid-configuration => master realm
http://localhost:8180/realms/java_spring_realm/.well-known/openid-configuration => my realm	

** getting started: **
- go to http://localhost:8180
- connect to master realm
- then connect/create to another realm


** configuration keycloak: **
- create a new realm,
- create a new client, (app ex: angular)
- create a new role,
- create a new user + assign a role


** Further: **
- add register endpoint (to allows users to create their own account)


**export keyclock config:**
docker compose exec keycloak /bin/bash

/opt/keycloak/bin/kcadm.sh config credentials --server http://localhost:8081/auth --realm master --user admin --password admin
Logging into http://localhost:8081/auth as user admin of realm master


** sdkman: permet de gerer une liste de java version on sein d'une meme machine

sdk list java
sdk install java 20.0.1-open
sdk default java 21.0.2-open
sdk use java 20.0.1-open

nb:
java 21: spring 3.1.5, lombok 1.18.21, maven 

**mon projet**
java 21, spring boot 3.3.0, maven 


** remote origin to project: **
git remote add origin https://github.com/Samlogy/java-spring-project.git
git checkout -b dev
git checkout dev
git push origin dev --force


** intellij/git/github: **
https://medium.com/@AlexanderObregon/a-comprehensive-guide-to-intellij-ideas-built-in-version-control-git-and-github-integration-210b046af96b


** lombok problem: **
Solution for the problem Cannot Resolve Method ‘builder’ in Spring Boot with lombok:
installer lombok plugins => intellij or eclipse, ...


export JAVA_HOME=/usr/local/jdk-20.0.2


**Java Melody:**
a poweful monitoring tool that helps to monitoring our java app.
`http://localhost:8080/monitoring`


**Architecture Modulaire:**
- add `<packaging>pom</packaging>` to pom.xml
- execute this command to create each module: `mvn archetype:generate -DgroupId=com.example -DartifactId=common -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false`
- 