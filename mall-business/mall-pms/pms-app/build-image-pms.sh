#!/usr/bin/env bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# hub.docker.com/szopen
group=szopen
#docker login -u szopen --password

# mvn
mvn clean -DskipTests
mvn package -DskipTests
cd target
artifactId=$(sed '/artifactId/!d;s/.*=//' maven-archiver/pom.properties)
version=$(sed '/version/!d;s/.*=//' maven-archiver/pom.properties)
cd ..
mkdir -p target/dependency
(cd target/dependency; jar -xf ../*.jar)
# docker
docker build -t ${artifactId}:${version} -f ./Dockerfile .
docker tag ${artifactId}:${version} ${group}/${artifactId}:${version}
#docker push ${group}/${artifactId}:${version}
