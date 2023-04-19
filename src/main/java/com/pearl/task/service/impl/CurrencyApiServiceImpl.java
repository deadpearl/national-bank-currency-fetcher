package com.pearl.task.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.pearl.task.exception.ExceptionHandler;
import com.pearl.task.entity.Items;
import com.pearl.task.service.CurrencyApiService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class CurrencyApiServiceImpl  implements CurrencyApiService {

    @Value("${currency.api.url}")
    private String urlTemplate;

    @Override
    public Items fetchCurrencyRates(String date) {
        String url = urlTemplate + date;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new ExceptionHandler("Failed to fetch currency rates: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String xmlData = doc.outerHtml();
        ObjectMapper mapper = new XmlMapper();
            try {
                return mapper.readValue(xmlData, Items.class);
            } catch (IOException e) {
                throw new ExceptionHandler("Could not parse XML data into Items object: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }




}
