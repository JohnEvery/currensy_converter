package com.eve.emphasoft.currency.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "disclaimer",
        "license",
        "timestamp",
        "base",
        "rates"
})
public class CurrencyDTO {

    @JsonProperty("disclaimer")
    private String disclaimer;
    @JsonProperty("license")
    private String license;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("base")
    private String base;
    @JsonProperty("rates")
    private Map<String, BigDecimal> rates = new HashMap<String, BigDecimal>();

    @JsonProperty("disclaimer")
    public String getDisclaimer() {
        return disclaimer;
    }

    @JsonProperty("disclaimer")
    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    @JsonProperty("license")
    public String getLicense() {
        return license;
    }

    @JsonProperty("license")
    public void setLicense(String license) {
        this.license = license;
    }

    @JsonProperty("timestamp")
    public Integer getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("base")
    public String getBase() {
        return base;
    }

    @JsonProperty("base")
    public void setBase(String base) {
        this.base = base;
    }

    @JsonProperty("rates")
    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    @JsonProperty("rates")
    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }
}



