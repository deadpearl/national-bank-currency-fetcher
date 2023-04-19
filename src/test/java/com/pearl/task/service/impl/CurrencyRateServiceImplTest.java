package com.pearl.task.service.impl;

import com.pearl.task.entity.CurrencyRate;
import com.pearl.task.entity.Items;
import com.pearl.task.repository.CurrencyRateRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyRateServiceImplTest {

    @Test
    public void testSaveCurrencyRates() {
        // create some sample data
        Items rates = new Items();
        rates.setDate("01.01.2022");

        CurrencyRate rate1 = new CurrencyRate(
                "US Dollar", "USD",  1.23,
                LocalDate.parse( rates.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        );
        CurrencyRate rate2 = new CurrencyRate(
                "Euro", "EUR", 1.45,
                LocalDate.parse( rates.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")
                ));
        rates.setCurrencyRateList(Arrays.asList(rate1, rate2));

        // create a mock repository
        CurrencyRateRepository repository = Mockito.mock(CurrencyRateRepository.class);
        Mockito.when(repository.saveAll(Mockito.anyList())).thenReturn(Arrays.asList(rate1, rate2));

        // create a service instance with the mock repository
        CurrencyRateServiceImpl service = new CurrencyRateServiceImpl(repository);

        // call the method and check the result
        int result = service.saveCurrencyRates(rates);
        assertEquals(2, result);
    }

    @Test
    void testGetCurrencies() {
        String code = "USD";
        LocalDate date = LocalDate.now();
        CurrencyRate currencyRate = new CurrencyRate(
                "US Dollar", code,  1.23, date
        );

        CurrencyRateRepository repository = Mockito.mock(CurrencyRateRepository.class);
        Mockito.when(repository.findByCodeAndDate(code, date)).thenReturn(currencyRate);
        CurrencyRateServiceImpl service = new CurrencyRateServiceImpl(repository);

        CurrencyRate answer = service.getCurrencies(date, code);

        assertEquals(currencyRate, answer);
    }
}