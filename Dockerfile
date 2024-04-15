FROM openjdk:21-jdk-slim
ENV JAVA_OPTS " -Xms512m -Xmx512m -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom"
WORKDIR application
COPY target/name_file.jar ./
ENTRYPOINT ["java", "-jar", "name_file.jar"]