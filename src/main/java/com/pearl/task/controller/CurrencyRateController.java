package com.pearl.task.controller;

import com.pearl.task.entity.Items;
import com.pearl.task.exception.ExceptionHandler;
import com.pearl.task.service.CurrencyApiService;
import com.pearl.task.service.CurrencyRateService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@Slf4j
@RequestMapping("/currencyRate")
public class CurrencyRateController {

    private final CurrencyApiService apiService;
    private final CurrencyRateService service;

    public CurrencyRateController(CurrencyApiService apiService, CurrencyRateService service) {
        this.apiService = apiService;
        this.service = service;
    }


    @PostMapping(value="/save/{date}", produces={"application/json"})
    public ResponseEntity<String> saveCurrencyData(@PathVariable String date) {
        try {
            Items items = apiService.fetchCurrencyRates(date);
            int count = service.saveCurrencyRates(items);
            return ResponseEntity.ok().body("Data saved successfully! {\"count\": " + count + "}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred while processing the request" + e.getMessage());
        }
    }

    @GetMapping("/{date}/{code}")
    public ResponseEntity<Object> getCurrencyDataByDateAndCode(@PathVariable("date") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date, @PathVariable(required = false) String code) {
        try {
            return ResponseEntity.ok().body(service.getCurrencies(date, code));
            }
        catch (Exception e) {
            String errorMessage = "Error occurred while fetching currency data: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

}
