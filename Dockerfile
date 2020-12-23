FROM adoptopenjdk:11-jre-hotspot

ADD build/libs/kerst-hanne-2020-0.0.1-SNAPSHOT.jar kerst-hanne-2020.jar

ENTRYPOINT exec java -jar kerst-hanne-2020.jar