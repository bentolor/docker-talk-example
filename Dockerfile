#####################################################################
# Sample Dockerfile for a Java fat jar app                          #
#                                                                   #
# build image with                                                  #
# docker build -t exxcellent/docker_talk .                          #
#                                                                   #
# run image interactively with                                      #
# docker run -it --rm -p 8080:8080 exxcellent/docker_talk /bin/bash #
# run image daemonized with                                         #
# docker run -d -p 8080:8080 --name lt_app exxcellent/docker_talk   #
#####################################################################
# use java 8 as base image
FROM java:8
MAINTAINER Ralph Guderlei
# add uberjar to image
ADD target/docker-1.0.0.jar /opt/app.jar
ADD src/main/resources/example.yml /opt/app.yml
WORKDIR /opt
# announce to use port 8080 and 8081
EXPOSE 8080
EXPOSE 8081
# run the jar on container start
ENTRYPOINT java -jar app.jar server
