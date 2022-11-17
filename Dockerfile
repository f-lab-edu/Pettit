FROM openjdk:11-jdk

ARG JAR_FILE=build/libs/Pettit-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8281

## container 실행시 scouter 관련된 파일들을 볼륨으로 가져와서 실행
ENTRYPOINT ["java", "-javaagent:/scouter/agent.java/scouter.agent.jar", "-Dscouter.config=/scouter/agent.java/conf/scouter.conf", "-jar", "app.jar"]
