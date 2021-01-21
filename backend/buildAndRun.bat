@echo off
call mvn clean package
call docker build -t com.mycompany/TotvsCoins .
call docker rm -f TotvsCoins
call docker run -d -p 9080:9080 -p 9443:9443 --name TotvsCoins com.mycompany/TotvsCoins