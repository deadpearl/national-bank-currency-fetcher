package com.pearl.task.repository;

import com.pearl.task.entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Integer> {

    CurrencyRate save(CurrencyRate currencyRate);
    CurrencyRate findByCodeAndDate(String code, LocalDate date);
}
