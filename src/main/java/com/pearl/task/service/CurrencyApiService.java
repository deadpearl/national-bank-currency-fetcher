package com.pearl.task.service;

import com.pearl.task.entity.Items;


public interface CurrencyApiService {
    Items fetchCurrencyRates(String date);
}
