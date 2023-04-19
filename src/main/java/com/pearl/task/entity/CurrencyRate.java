package com.pearl.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "r_currency")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id")
    private int id;

    @JacksonXmlProperty(localName = "fullname")
    @Column(name = "title")
    private String title;

    @JacksonXmlProperty(localName = "title")
    @Column(name = "code")
    private String code;

    @JacksonXmlProperty(localName = "description")
    @Column(name = "value")
    private double value;


    @Column(name = "a_date")
    private LocalDate date;



    public CurrencyRate(){

    }

    public CurrencyRate(int id, String title, String code, double value, LocalDate date) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.value = value;
        this.date = date;
    }

    public CurrencyRate(String title, String code, double value, LocalDate date) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.value = value;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return  date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
