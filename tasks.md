**_ TODO _**
	
TODO: ***	
* voir les beugs (comment les corriger ?)
* rendre l'app totalement fonctionnel.
* ajouter des tests:
	unit/ integration
	jacoco => bdd
	tdd ?
* auth => token based, oauth 2.0 (keycloak) ?
* essayer d'avoir une app modulaire (décomposer en module) comment faire ? et le faire ?
* essayer d'avoir une app microservices (décomposer en microservices) comment faire ? et le faire ?

** PENDING **


** DONE **
* faire foncitonner plusieurs environnements (integration / qua/ prod)
* architecture de l'app

AWS:
EC2, S3, ECR, LB, API GATEWAY, Route53, RDS (postgresql), Secret Manager
where to deploy api, ui, elk, jenkins/sonar, keycloak ?
how to create 2xVM for api/ui ?
how to use RDS ?
how to set secrets ?
HOW TO USE route53 ?
how to use S3 ?
how to use LB ?
How to use API gateway ?
how to use ECR, EKS, ECS, ?

Keycloak:
re-configure again

Jenkins:
re-configure again
create a pipeline (multibranch= ^master|develop|feat/_$) exclude= ^test/_
create a pipeline mono branch ==> (juste a simple example)
automate the pipeline from github ==> push

Sonar:
re-configure again
link jenkins & sonar

**_ DONE _**

Docker:
delete all volumes, networks

Keycloak:
create a docker-compose file with this conf ==> paper
persist data => db / volume ? bind volume to real folder in machine

Jenkins:
create a docker-compose file with this conf

Sonar:
create a docker-compose file with this conf

fix: keycloak performence (CPU exceeded)
