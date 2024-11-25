# Imagen Modelo
FROM eclipse-temurin:17.0.13_11-jdk

# Informar el puerto donde se ejecuta el contenedor(informativo)
EXPOSE 8080

# Directorio raiz
WORKDIR /root

# Copiar y pegar archivos dentro del contenedor
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

# Descargar las dependencias
RUN ./mvnw dependency:go-offline

# Copiar el codigo fuente dentro del contenedor
COPY ./src /root/src

# Construir nuestra aplicacion
RUN ./mvnw clean install -DskipTests

# Levantan nuestra aplicacion cuando el contenedor inicie
ENTRYPOINT ["java", "-jar", "/root/target/hrtracker-0.0.1-SNAPSHOT.jar"]