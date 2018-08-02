FROM java:8

WORKDIR /tmp
COPY ./target/scala-2.12/http4s-doobie-circle-example-assembly-0.1.jar /tmp/app.jar

ENTRYPOINT ["java", "-jar", "/tmp/app.jar"]