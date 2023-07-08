package com.linbrox.report.application.service.impl;

import com.linbrox.report.application.service.MessageBrokerService;
import com.linbrox.report.application.service.SummarySaleService;
import com.linbrox.report.domain.model.SummarySale;
import com.linbrox.report.domain.repository.SummarySaleRepository;
import com.linbrox.report.domain.model.ReportResponse;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SummarySaleServiceImpl implements SummarySaleService {

    private final SummarySaleRepository summarySaleRepository;
    private final MessageBrokerService messageBrokerService;

    public SummarySaleServiceImpl(SummarySaleRepository summarySaleRepository, MessageBrokerService messageBrokerService) {
        this.summarySaleRepository = summarySaleRepository;
        this.messageBrokerService = messageBrokerService;
    }

    @Override
    public ReportResponse retrieveDailySale(Date currentDay, String model, String cryptoCurrency){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDay);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startDate = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date endDate = calendar.getTime();
        List<SummarySale> summarySaleEntityList = this.summarySaleRepository.findByModelAndCryptoCurrencyAndPurchasedAtBetween(model, cryptoCurrency, startDate, endDate);
        Double accumulatedUSD = 0d;
        Double accumulateCrypto = 0d;
        for(SummarySale summarySale : summarySaleEntityList){
            accumulateCrypto = accumulateCrypto + summarySale.amountCryptCurrency;
            accumulatedUSD = accumulatedUSD + summarySale.amountUSD;
        }
        ReportResponse reportResponse = ReportResponse.builder()
                .cryptocurrency(cryptoCurrency)
                .date(currentDay)
                .model(model)
                .cryptocurrencyAmount(accumulateCrypto)
                .usdAmount(accumulatedUSD)
                .build();
        return reportResponse;
    }
}
