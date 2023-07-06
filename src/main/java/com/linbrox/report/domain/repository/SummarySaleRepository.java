package com.linbrox.report.domain.repository;

import com.linbrox.report.domain.model.SummarySale;
import com.linbrox.report.infrastructure.entity.SummarySaleEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SummarySaleRepository {

    SummarySale save(SummarySale purchase);
    List<SummarySale> findAll();
    Optional<SummarySale> findById(UUID uuid);

    List<SummarySale> findByModelAndCryptoCurrencyAndPurchasedAtBetween(String model, String crypto, Date startDate, Date endDate);
}
