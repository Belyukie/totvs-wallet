#!/bin/sh
mvn clean package && docker build -t com.mycompany/TotvsCoins .
docker rm -f TotvsCoins || true && docker run -d -p 9080:9080 -p 9443:9443 --name TotvsCoins com.mycompany/TotvsCoins