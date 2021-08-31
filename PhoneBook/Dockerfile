FROM openjdk:8-jdk-alpine
MAINTAINER Gabriel Diaz <gabriel.leonardo.diaz@gmail.com>
ARG JAR_FILE=target/*.jar
ENV DB_USER=root
ENV DB_PASS=1234
ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_SCHEMA=phone-book
COPY ${JAR_FILE} phone-book.jar
ENTRYPOINT ["java","-Dphonebook_db_username=${DB_USER}","-Dphonebook_db_password=${DB_PASS}","-Dphonebook_db_host=${DB_HOST}","-Dphonebook_db_port=${DB_PORT}","-Dphonebook_db_schema=${DB_SCHEMA}","-jar","/phone-book.jar"]