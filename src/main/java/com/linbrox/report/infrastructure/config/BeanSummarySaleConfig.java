package com.linbrox.report.infrastructure.config;

import com.linbrox.report.application.service.MessageBrokerService;
import com.linbrox.report.application.service.SummarySaleService;
import com.linbrox.report.application.service.impl.SummarySaleServiceImpl;
import com.linbrox.report.domain.repository.SummarySaleRepository;
import com.linbrox.report.infrastructure.adapter.SummarySaleRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanSummarySaleConfig {

    @Bean
    SummarySaleService purchaseService(
            final SummarySaleRepository purchaseRepository,
            final MessageBrokerService messageBrokerService
            ){
        return new SummarySaleServiceImpl(purchaseRepository, messageBrokerService);
    }

    @Bean SummarySaleRepository purchaseRepository(SummarySaleRepositoryAdapter summarySaleRepositoryAdapter){
        return summarySaleRepositoryAdapter;
    }

}
