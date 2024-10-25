# Use uma imagem base do Gradle que tenha JDK pré-instalado
FROM gradle:8.5-jdk21 AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o código fonte para o contêiner
COPY . .

# Compile o projeto
RUN gradle build --no-daemon

# Usar uma nova imagem para executar o aplicativo
FROM openjdk:21-jdk

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR gerado do estágio de build
COPY --from=build /app/build/libs/candidate-evaluation-project-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta 8080
EXPOSE 8080

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]
