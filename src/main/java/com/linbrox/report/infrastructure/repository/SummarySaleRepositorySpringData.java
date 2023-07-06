package com.linbrox.report.infrastructure.repository;

import com.linbrox.report.infrastructure.entity.SummarySaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface SummarySaleRepositorySpringData extends JpaRepository<SummarySaleEntity, UUID> {

    List<SummarySaleEntity> findByModelAndCryptoCurrencyAndPurchasedAtBetween(String model, String crypto, Date startDate, Date endDate);
}
