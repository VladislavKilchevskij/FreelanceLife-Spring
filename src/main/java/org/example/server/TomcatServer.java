package org.example.server;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.io.IOException;

public class TomcatServer {
    private static final int PORT = 8080;
    private Tomcat tomcat;
    public TomcatServer() {
        configureTomcat();
    }

    public void start() {
        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }

    private void configureTomcat() {
        tomcat = new Tomcat();
        tomcat.setBaseDir(createTempDir());
        tomcat.setPort(PORT);
        tomcat.getConnector();
        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("/", ".");
    }

    private String createTempDir() {
        try {
            File tempDir = File.createTempFile("tomcat.", "." + PORT);
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir.getAbsolutePath();
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"), ex
            );
        }
    }
}