package com.linbrox.report.application.service;

import com.linbrox.report.application.service.impl.SummarySaleServiceImpl;
import com.linbrox.report.common.CryptoCurrencyEnum;
import com.linbrox.report.common.HyundaiModelEnum;
import com.linbrox.report.domain.model.ReportResponse;
import com.linbrox.report.domain.model.SummarySale;
import com.linbrox.report.domain.repository.SummarySaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SummarySaleServiceTest {

    @Mock
    private SummarySaleRepository summarySaleRepository;
    @Mock
    private MessageBrokerService messageBrokerService;

    SummarySaleService saleService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        this.saleService = new SummarySaleServiceImpl(summarySaleRepository, messageBrokerService);
    }

    @Test
    @DisplayName("Should return 0d when summary sale is empty")
    void shouldReturnZeroWhenSummarySaleIsEmpty(){
        String model = HyundaiModelEnum.TUCSON.name();
        String crypto = CryptoCurrencyEnum.ETH.name();
        Date today = new Date();
        List<SummarySale> summarySaleList = new ArrayList<>();
        when(summarySaleRepository.findByModelAndCryptoCurrencyAndPurchasedAtBetween(any(), any(), any(), any()))
                .thenReturn(summarySaleList);
        var result = this.saleService.retrieveDailySale(today, model, crypto);
        ReportResponse expected = ReportResponse.builder()
                .date(today)
                .model(model)
                .usdAmount(0d)
                .cryptocurrencyAmount(0d)
                .cryptocurrency(crypto)
                .build();
        assertEquals(result, expected);

    }
}