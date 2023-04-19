package com.pearl.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.Transient;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {
    @JacksonXmlProperty(localName = "item")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CurrencyRate> currencyRateList;

    @JacksonXmlProperty(localName = "date")
    @Transient
    private String date;

    public List<CurrencyRate> getCurrencyRateList() {
        return currencyRateList;
    }

    public String getDate() {
        return date;
    }
}
