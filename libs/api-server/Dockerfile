FROM openjdk:11-slim
RUN mkdir /home/app
COPY ./edc-api-server-0.0.1-SNAPSHOT.jar /home/app
RUN apt-get update
RUN apt-get install -y curl
WORKDIR /home/app
EXPOSE 8187
CMD java -Djava.security.egd=file:/dev/./urandom -jar edc-api-server-0.0.1-SNAPSHOT.jar