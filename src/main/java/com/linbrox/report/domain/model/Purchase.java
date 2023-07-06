package com.linbrox.report.domain.model;


import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Purchase {
    private UUID convertionID;
    private Date createdAt;
    private String cryptoCurrencyEnum;
    private String fullName;
    private String hyundaiModel;
    private Object id;
    private Double priceCryptoCurrency;
    private Double priceUSD;
    private String version;
}
