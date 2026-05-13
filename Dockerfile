FROM eclipse-temurin:17-jdk AS builder
WORKDIR book_store
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} book_store.jar
RUN java -Djarmode=layertools -jar book_store.jar extract

FROM eclipse-temurin:17-jre
WORKDIR book_store
COPY --from=builder /book_store/dependencies/ ./
COPY --from=builder /book_store/spring-boot-loader/ ./
COPY --from=builder /book_store/snapshot-dependencies/ ./
COPY --from=builder /book_store/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
EXPOSE 8080