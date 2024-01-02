FROM openjdk:17-oracle
ENV PORT 8080
EXPOSE 8080
RUN mkdir /opt/app
ADD maven/ms-celulas.jar /opt/app
ENTRYPOINT ["java", "-jar", "/opt/app/ms-celulas.jar"]