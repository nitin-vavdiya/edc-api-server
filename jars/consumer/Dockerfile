FROM openjdk:11-slim
RUN mkdir /home/app
COPY ./consumer.jar /home/app
COPY ./config.properties /home/app
RUN apt-get update
RUN apt-get install -y curl
WORKDIR /home/app
EXPOSE 9191
EXPOSE 9192
EXPOSE 9292
CMD java -Djava.security.egd=file:/dev/./urandom  -Dedc.fs.config=/home/app/config.properties -jar consumer.jar
