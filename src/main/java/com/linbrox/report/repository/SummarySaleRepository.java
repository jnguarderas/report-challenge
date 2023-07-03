package com.linbrox.report.repository;

import com.linbrox.report.entity.SummarySale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SummarySaleRepository extends JpaRepository<SummarySale, UUID> {

    List<SummarySale> findByModelAndCryptoCurrencyAndPurchasedAtBetween(String model, String crypto, Date startDate, Date endDate);
}
