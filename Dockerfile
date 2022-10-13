FROM openjdk:11-jdk

ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

COPY build/lib/*.jar application.jar

EXPOSE 8281

CMD ["java", "-jar", "application.jar"]