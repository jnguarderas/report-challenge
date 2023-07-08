package com.linbrox.report.infrastructure.controller;

import com.linbrox.report.application.service.SummarySaleService;
import com.linbrox.report.common.CryptoCurrencyEnum;
import com.linbrox.report.common.HyundaiModelEnum;
import com.linbrox.report.domain.model.ReportResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class ReportControllerTest {

    @Mock
    private SummarySaleService summarySaleService;

    private ReportController reportController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        this.reportController = new ReportController(summarySaleService);
    }

    @Test
    @DisplayName("Should return a valid respons when send a valid request")
    void shouldReturnValidResponseWhenSendValidRequest() {

        Date today = new Date();
        String model = HyundaiModelEnum.TUCSON.name();
        String current = CryptoCurrencyEnum.BTC.name();
        ReportResponse expectedResponse = ReportResponse.builder()
                .cryptocurrency(current)
                .model(model)
                .usdAmount(1d)
                .date(today)
                .build();
        when(summarySaleService.retrieveDailySale(any(), any(), any()))
                .thenReturn(expectedResponse);
        // Act
        Mono<ReportResponse> actualResponse = reportController.report(HyundaiModelEnum.TUCSON,
                CryptoCurrencyEnum.BTC, "07-07-2023");
        // Assert
        assertEquals(expectedResponse, actualResponse.block());
    }

}