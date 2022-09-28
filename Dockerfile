FROM openjdk:17
WORKDIR /usr/src/ab2d-properties
ADD build/libs/ab2d-properties.jar /usr/src/ab2d-properties/ab2d-properties.jar

CMD java \
    -XX:+UseContainerSupport \
    -jar ab2d-properties.jar

EXPOSE 8060
