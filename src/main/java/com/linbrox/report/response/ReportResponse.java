package com.linbrox.report.response;

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
