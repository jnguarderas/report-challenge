package com.linbrox.report.application.service;

import com.linbrox.report.domain.model.ReportResponse;

import java.util.Date;

public interface SummarySaleService {
    ReportResponse retrieveDailySale(Date currentDay, String model, String cryptoCurrency);
}
