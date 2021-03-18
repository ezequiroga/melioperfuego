FROM adoptopenjdk/openjdk11

MAINTAINER ae.quiroga.88@gmail.com

EXPOSE 9090

ADD ./target/operfuego-0.0.1-SNAPSHOT.jar /data/operfuego-0.0.1-SNAPSHOT.jar

CMD java -jar -Dsatelites=${MSSATELITES} /data/operfuego-0.0.1-SNAPSHOT.jar