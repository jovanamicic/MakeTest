package com.maketest.service;

/**
 * Created by Jovana Micic on 04-Nov-16.
 */
public interface EmailService {
    void send (String to, String subject, String body);
}
