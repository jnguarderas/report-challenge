package com.linbrox.report.entity;

import com.linbrox.report.common.CryptoCurrencyEnum;
import com.linbrox.report.common.HyundaiModelEnum;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "summary_sales")
@Data
@Builder
@NoArgsConstructor
public class SummarySale {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "purchased_date")
    private Date purchasedAt;
    @Column(name = "model")
    public String model;
    @Column(name = "crypto_currency")
    public String cryptoCurrency;
    @Column(name = "amount_usd")
    public Double amountUSD;
    @Column(name = "amount_currency")
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
