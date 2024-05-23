FROM openjdk:17-jdk

WORKDIR /app

COPY target/menueasy.jar /app/menueasy.jar

ENV SPRING_DATASOURCE_URL=jdbc:mysql://db_project:3306/db_project
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=12345678
ENV PROJECT_SECRET_KEY=9d84d7b6e5c541f203c51eb9827156d17e638dd045f1fc8b2c7738bca0c52936
ENV PROJECT_FRONTEND_PATH=http://localhost:5174

EXPOSE 8080

CMD ["java", "-jar", "menueasy.jar"]
# I'm tired