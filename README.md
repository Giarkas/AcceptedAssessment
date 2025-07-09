This Project contains the assessment.

You will find the Dockerfile to create the image of the project with the following command

docker build -t assessment/assessment-accepted-docker:v1 .

After the build please run the docker compose file, located at the folder of the project with the following comand

docker-compose up -d

This compose will set up a postgres db 

USER: accepted
PASSWORD: accepted
DB: accepted_db
URL: jdbc:localhost://postgres:5432/accepted_db

Upon the project's startup the tables will be created throough liquibase.

The swagger uri, after deployment, is located at http://localhost:8080/swagger-ui.html
