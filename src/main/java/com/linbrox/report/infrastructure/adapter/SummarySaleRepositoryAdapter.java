package com.linbrox.report.infrastructure.adapter;

import com.linbrox.report.domain.model.SummarySale;
import com.linbrox.report.domain.repository.SummarySaleRepository;
import com.linbrox.report.infrastructure.entity.SummarySaleEntity;
import com.linbrox.report.infrastructure.repository.SummarySaleRepositorySpringData;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SummarySaleRepositoryAdapter implements SummarySaleRepository {

    private final SummarySaleRepositorySpringData summarySaleRepositorySpringData;

    public SummarySaleRepositoryAdapter(SummarySaleRepositorySpringData summarySaleRepositorySpringData) {
        this.summarySaleRepositorySpringData = summarySaleRepositorySpringData;
    }

    @Override
    public SummarySale save(SummarySale summarySale) {
        SummarySaleEntity entity = SummarySaleEntity.fromDomainModel(summarySale);
        return this.summarySaleRepositorySpringData.save(entity).toDomainModel();
    }

    @Override
    public List<SummarySale> findAll() {
        return this.summarySaleRepositorySpringData.findAll()
                .stream().map(SummarySaleEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SummarySale> findById(UUID uuid) {
        return this.summarySaleRepositorySpringData.findById(uuid)
                .map(SummarySaleEntity::toDomainModel);
    }

    @Override
    public List<SummarySale> findByModelAndCryptoCurrencyAndPurchasedAtBetween(String model, String crypto, Date startDate, Date endDate) {
        return this.summarySaleRepositorySpringData.findByModelAndCryptoCurrencyAndPurchasedAtBetween(model, crypto, startDate, endDate)
                .stream().map(SummarySaleEntity::toDomainModel)
                .collect(Collectors.toList());
    }
}
