FROM openjdk:11-slim
RUN mkdir /home/app
COPY ./provider.jar /home/app
COPY ./config.properties /home/app
RUN apt-get update
RUN apt-get install -y curl
WORKDIR /home/app
EXPOSE 8181
EXPOSE 8182
EXPOSE 8282
CMD java -Djava.security.egd=file:/dev/./urandom  -Dedc.fs.config=/home/app/config.properties -jar provider.jar