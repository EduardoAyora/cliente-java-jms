package org.jboss.tools.examples.service;

public interface MessageServerRemote {
    public void sendMessage(String message) throws Exception;
}