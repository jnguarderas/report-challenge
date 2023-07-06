package com.linbrox.report.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
public class SummarySale {

    private UUID id;
    private Date createdAt;
    private Date purchasedAt;
    public String model;
    public String cryptoCurrency;
    public Double amountUSD;
    public Double amountCryptCurrency;

    public SummarySale(UUID id, Date createdAt, Date purchasedAt, String model, String cryptoCurrency, Double amountUSD, Double amountCryptCurrency) {
        this.id = id;
        this.createdAt = createdAt;
        this.purchasedAt = purchasedAt;
        this.model = model;
        this.cryptoCurrency = cryptoCurrency;
        this.amountUSD = amountUSD;
        this.amountCryptCurrency = amountCryptCurrency;
    }
}
