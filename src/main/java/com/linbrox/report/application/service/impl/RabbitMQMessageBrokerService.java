package com.linbrox.report.application.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.linbrox.report.domain.model.Purchase;
import com.linbrox.report.application.service.MessageBrokerService;
import com.linbrox.report.infrastructure.config.RabbitMQConfig;
import com.linbrox.report.infrastructure.entity.SummarySaleEntity;
import com.linbrox.report.infrastructure.repository.SummarySaleRepositorySpringData;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RabbitMQMessageBrokerService implements MessageBrokerService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    private final SummarySaleRepositorySpringData summarySaleRepositorySpringData;

    public RabbitMQMessageBrokerService(RabbitTemplate rabbitTemplate,
                                   ObjectMapper objectMapper,
                                   SummarySaleRepositorySpringData summarySaleRepositorySpringData) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.objectMapper.setDateFormat(dateFormat);
        this.summarySaleRepositorySpringData = summarySaleRepositorySpringData;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receivePurchaseMessage(String message) {
        try{
            // Process the received purchase message
            // Save the information to the report microservice database or perform any other necessary actions
            Purchase purchase =  objectMapper.readValue(message, Purchase.class);
            System.out.println("Received Purchase Message: " + purchase);
            SummarySaleEntity summarySaleEntity = SummarySaleEntity.builder()
                    .model(purchase.getHyundaiModel())
                    .cryptoCurrency(purchase.getCryptoCurrencyEnum())
                    .amountUSD(purchase.getPriceUSD())
                    .purchasedAt(purchase.getCreatedAt())
                    .createdAt(new Date())
                    .amountCryptCurrency(purchase.getPriceCryptoCurrency())
                    .build();
            this.summarySaleRepositorySpringData.save(summarySaleEntity);
        }
        catch (Exception e){
            System.out.print(e);
        }

    }
}