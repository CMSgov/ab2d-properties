FROM amazoncorretto:21-al2023-jdk
WORKDIR /usr/src/ab2d-properties
# ADD build/libs/Ab2d-*-Properties-Service-*.jar /usr/src/ab2d-properties/ab2d-properties.jar
ADD build/libs/ab2d-properties-*.jar /usr/src/ab2d-properties/ab2d-properties.jar
CMD java -jar /usr/src/ab2d-properties/ab2d-properties.jar

CMD java \
    -XX:+UseContainerSupport \
    -jar ab2d-properties.jar

EXPOSE 8060
