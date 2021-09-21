################################################################################
# Sample Dockerfile for a Java fat jar app                                     #
#                                                                              #
# Build image                                                                  #
#   docker build -t exxcellent/docker_talk .                                   #
#                                                                              #
# run image interactively with                                                 #
#   docker run -it --rm -p 8080:8080 exxcellent/docker_talk /bin/bash          #
#                                                                              #
# run image daemonized with                                                    #
#   docker run -d -p 8080:8080 --name myapp exxcellent/docker_talk             #
#   docker stop myapp                                                          #
#   docker start myapp                                                         #
################################################################################


                                            # use java as base image
FROM java:17-jre
MAINTAINER Benjamin Schmid

                                            # add JAR & config file to image
WORKDIR /opt
ADD target/rest-microservice-1.0.0.jar  app.jar
ADD src/main/resources/example.yml      app.yml

                                            # announce exported ports
EXPOSE 8080 8081

                                            # example storage volume
VOLUME ["/srv/"]

                                            # Clean up APT & tmp when done
RUN apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

                                            # run the jar on container start
ENTRYPOINT java -jar app.jar server
