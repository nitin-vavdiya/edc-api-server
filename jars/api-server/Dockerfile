FROM openjdk:11-slim
RUN mkdir /home/app
COPY ./edc-api-server-0.0.1-SNAPSHOT.jar /home/app
COPY ./config.properties /home/app
RUN apt-get update
RUN apt-get install -y curl
WORKDIR /home/app
EXPOSE 8187
CMD java -Djava.security.egd=file:/dev/./urandom -Dedc.fs.config=/home/app/config.properties -jar edc-api-server-0.0.1-SNAPSHOT.jar