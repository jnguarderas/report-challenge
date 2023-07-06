package com.linbrox.report.infrastructure.entity;

import com.linbrox.report.domain.model.SummarySale;
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
public class SummarySaleEntity {

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

    public SummarySaleEntity(UUID id, Date createdAt, Date purchasedAt, String model, String cryptoCurrency, Double amountUSD, Double amountCryptCurrency) {
        this.id = id;
        this.createdAt = createdAt;
        this.purchasedAt = purchasedAt;
        this.model = model;
        this.cryptoCurrency = cryptoCurrency;
        this.amountUSD = amountUSD;
        this.amountCryptCurrency = amountCryptCurrency;
    }

    public static SummarySaleEntity fromDomainModel(SummarySale summarySale) {
        return SummarySaleEntity.builder()
                .model(summarySale.getModel())
                .id(summarySale.getId())
                .amountUSD(summarySale.getAmountUSD())
                .createdAt(summarySale.getCreatedAt())
                .amountCryptCurrency(summarySale.getAmountCryptCurrency())
                .cryptoCurrency(summarySale.getCryptoCurrency())
                .purchasedAt(summarySale.getPurchasedAt())
                .build();
    }

    public SummarySale toDomainModel(){
        return SummarySale.builder()
                .id(this.id)
                .createdAt(this.createdAt)
                .purchasedAt(this.purchasedAt)
                .model(this.model)
                .cryptoCurrency(this.cryptoCurrency)
                .amountUSD(this.amountUSD)
                .amountCryptCurrency(this.amountCryptCurrency)
                .build();
    }

}
