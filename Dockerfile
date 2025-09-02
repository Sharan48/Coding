# Use an official Maven image with JDK 17
FROM maven:3.9.6-eclipse-temurin-17

# Set working directory
WORKDIR /app

# Copy Maven project
COPY . /app

CMD ["mvn", "test", "-Denv=qa", "-DsuiteXmlFile=testng.xml"]