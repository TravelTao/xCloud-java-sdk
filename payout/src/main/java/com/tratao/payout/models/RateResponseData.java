package com.tratao.payout.models;

import java.io.Serializable;

/**
 * Rate response data
 *
 * {
 *      "queryNo": "",
 *      "rate": ,
 *      "sourceCurrency": "",
 *      "targetCurrency": ""
 * }
 */
public class RateResponseData implements Serializable {
    private String queryNo;
    private double rate;
    private String sourceCurrency;
    private String targetCurrency;

    public String getQueryNo() {
        return queryNo;
    }

    public void setQueryNo(String queryNo) {
        this.queryNo = queryNo;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
}
