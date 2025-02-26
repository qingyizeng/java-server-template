FROM openjdk:8

ENV APP_HOME=/app
ENV PROFILE=docker
RUN mkdir -p ${APP_HOME}
RUN mkdir -p /usr/local/tomcat8.5/webapps/fileUpload
WORKDIR ${APP_HOME}

EXPOSE 8085

COPY target/api.jar ${APP_HOME}/

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "/app/api.jar"]
