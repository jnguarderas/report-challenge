package com.linbrox.report.application.service;

public interface MessageBrokerService {
    void receivePurchaseMessage(String message);
}