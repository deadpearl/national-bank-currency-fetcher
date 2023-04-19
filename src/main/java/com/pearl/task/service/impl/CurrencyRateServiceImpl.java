package com.pearl.task.service.impl;

import com.pearl.task.entity.CurrencyRate;
import com.pearl.task.entity.Items;
import com.pearl.task.exception.ExceptionHandler;
import com.pearl.task.repository.CurrencyRateRepository;
import com.pearl.task.service.CurrencyRateService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyRateServiceImpl implements CurrencyRateService {


    private final CurrencyRateRepository repository;

    public CurrencyRateServiceImpl(CurrencyRateRepository repository) {
        this.repository = repository;
    }

    @Override
    public int saveCurrencyRates(Items rates) {
        List<CurrencyRate> currencies = mapItemsToCurrencyRates(rates);
        try {
            return repository.saveAll(currencies).size();
        } catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save currency rates to database", e);
        }
    }

    private List<CurrencyRate> mapItemsToCurrencyRates(Items rates) {
        return rates.getCurrencyRateList().stream().map(item -> {
            CurrencyRate currency = new CurrencyRate();
            currency.setTitle(item.getTitle());
            currency.setCode(item.getCode());
            currency.setValue(item.getValue());
            currency.setDate(LocalDate.parse(rates.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            return currency;
        }).collect(Collectors.toList());
    }


    @Override
    public CurrencyRate getCurrencies(LocalDate date, String code) {
        return repository.findByCodeAndDate(code, date);
    }

}
