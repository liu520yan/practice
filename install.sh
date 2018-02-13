#!/bin/bash

PARENT_DIR=`dirname $0 | pwd`

EUREKA_PATH=$PARENT_DIR/eureka
CONFIG_PATH=$PARENT_DIR/config
GATEWAY_PATH=$PARENT_DIR/gateway
OAUTH2_PATH=$PARENT_DIR/oauth2
RESOURCES_PATH=$PARENT_DIR/resources

VERSION="1.0.1-SNAPSHOT"
EUREKA_IMAGE="registry.xuey.top/eureka:$VERSION"

CONFIG_IMAGE="registry.xuey.top/config:$VERSION"

GATEWAY_IMAGE="registry.xuey.top/gateway:$VERSION"

OAUTH2_IMAGE="registry.xuey.top/oauth2:$VERSION"

RESOURCES_IMAGE="registry.xuey.top/resources:$VERSION"

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

function installConfig(){
    cd $CONFIG_PATH
    installImage $CONFIG_IMAGE
}

function installGateway(){
    cd $GATEWAY_PATH
    installImage $GATEWAY_IMAGE
}

function installOauth2(){
    cd $OAUTH2_PATH
    installImage $OAUTH2_IMAGE
}

function installResources(){
    cd $RESOURCES_PATH
    installImage $RESOURCES_IMAGE
}

function package(){
    mvn -DskipTests clean package
}

case $1 in
    package)
    package
    ;;
    eureka)
    package
    installEureka
    ;;
    config)
    package
    installConfig
    ;;
    oauth2)
    package
    installOauth2
    ;;
    resources)
    package
    installResources
    ;;
    gateway)
    package
    installGateway
    ;;
    *)
    package
    installEureka
    installConfig
    installOauth2
    installResources
    installGateway
esac






