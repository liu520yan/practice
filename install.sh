#!/bin/bash

PARENT_DIR=`dirname $0 | pwd`

EUREKA_PATH=$PARENT_DIR/eureka

VERSION="1.0.1-SNAPSHOT"
EUREKA_IMAGE="registry.xuey.top/eureka:$VERSION"

function installImage(){

    IMAGE_NAME=$1
    mvn docker:build
    if [ ! $? = 0 ]; then
        echo "Image build failed!"
        exit 1;
    fi

    docker push $IMAGE_NAME
    if [ ! $? = 0 ]; then
        echo "Image push failed!"
        exit 1;
    else
        echo BUILD SUCCESS: $IMAGE_NAME
    fi
}

function installEureka(){
    cd $EUREKA_PATH
    installImage $EUREKA_IMAGE
}

function package(){
    mvn -DskipTests clean package
}

case $1 in
    package)
    package
    ;;
    server)
    package
    installEureka
    ;;
    *)
    package
    installEureka
esac






