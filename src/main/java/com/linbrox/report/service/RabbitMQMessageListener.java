package com.linbrox.report.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linbrox.report.Purchase;
import com.linbrox.report.config.RabbitMQConfig;
import com.linbrox.report.entity.SummarySale;
import com.linbrox.report.repository.SummarySaleRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RabbitMQMessageListener {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    private final SummarySaleRepository summarySaleRepository;

    public RabbitMQMessageListener(RabbitTemplate rabbitTemplate,
                                   ObjectMapper objectMapper,
                                   SummarySaleRepository summarySaleRepository) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.objectMapper.setDateFormat(dateFormat);
        this.summarySaleRepository = summarySaleRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receivePurchaseMessage(String message) {
        try{
            // Process the received purchase message
            // Save the information to the report microservice database or perform any other necessary actions
            Purchase purchase =  objectMapper.readValue(message, Purchase.class);
            System.out.println("Received Purchase Message: " + purchase);
            SummarySale summarySale = SummarySale.builder()
                    .model(purchase.getHyundaiModel())
                    .cryptoCurrency(purchase.getCryptoCurrencyEnum())
                    .amountUSD(purchase.getPriceUSD())
                    .purchasedAt(purchase.getCreatedAt())
                    .createdAt(new Date())
                    .amountCryptCurrency(purchase.getPriceCryptoCurrency())
                    .build();
            this.summarySaleRepository.save(summarySale);
        }
        catch (Exception e){
            System.out.print(e);
        }

    }
}