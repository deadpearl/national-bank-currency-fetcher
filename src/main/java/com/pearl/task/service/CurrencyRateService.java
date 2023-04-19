package com.pearl.task.service;

import com.pearl.task.entity.CurrencyRate;
import com.pearl.task.entity.Items;

import java.time.LocalDate;

public interface CurrencyRateService {

    int saveCurrencyRates(Items items);


    CurrencyRate getCurrencies(LocalDate date, String code);
}
