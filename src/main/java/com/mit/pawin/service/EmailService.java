package com.mit.pawin.service;

public interface EmailService {

    public boolean sendEmail(String toEmailAddress, String message, String subject);
}
