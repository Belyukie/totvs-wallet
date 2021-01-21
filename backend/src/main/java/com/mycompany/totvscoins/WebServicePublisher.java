package com.mycompany.totvscoins;
import javax.xml.ws.Endpoint;

public class WebServicePublisher{
 
    public static void main(String[] args) {
       Endpoint.publish("http://localhost:4848/TotvsCoins", new WebServiceImpl());
    }
 
}