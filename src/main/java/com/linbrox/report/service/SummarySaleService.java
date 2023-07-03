package com.linbrox.report.service;


import com.linbrox.report.entity.SummarySale;
import com.linbrox.report.repository.SummarySaleRepository;
import com.linbrox.report.response.ReportResponse;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SummarySaleService {

    private final SummarySaleRepository summarySaleRepository;

    public SummarySaleService(SummarySaleRepository summarySaleRepository) {
        this.summarySaleRepository = summarySaleRepository;
    }

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
        List<SummarySale> summarySaleList = this.summarySaleRepository.findByModelAndCryptoCurrencyAndPurchasedAtBetween(model, cryptoCurrency, startDate, endDate);
        Double accumulatedUSD = 0d;
        Double accumulateCrypto = 0d;
        for(SummarySale summarySale: summarySaleList){
            accumulateCrypto = accumulateCrypto + summarySale.amountCryptCurrency;
            accumulatedUSD = accumulatedUSD + summarySale.amountUSD;
        }
        ReportResponse reportResponse = ReportResponse.builder()
                .cryptocurrency(cryptoCurrency)
                .date(startDate)
                .model(model)
                .cryptocurrencyAmount(accumulateCrypto)
                .usdAmount(accumulatedUSD)
                .build();
        return reportResponse;
    }
}
