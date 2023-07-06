package com.linbrox.report.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
public class ReportResponse {
    public Date date;
    public String model;
    public String cryptocurrency;
    public Double usdAmount;
    public Double cryptocurrencyAmount;
}
