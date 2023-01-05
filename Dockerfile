FROM openjdk:11.0.8-jdk-slim
ADD build/libs/*.jar ./
EXPOSE 9009
ENTRYPOINT [ "java" ]
CMD [ "-jar", "weather-0.0.1-SNAPSHOT.jar" ]