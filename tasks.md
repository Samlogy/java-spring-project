**_ TODO _**

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
