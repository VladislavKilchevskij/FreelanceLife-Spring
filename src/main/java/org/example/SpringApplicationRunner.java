package org.example;

import org.example.server.TomcatServer;

public class SpringApplicationRunner {

    public static void main(String[] args) {
        new TomcatServer().start();
    }
}