package com.edu.msu.stockanalysis.model.rest;


import java.util.Objects;

public class TickerDetail {

    private String name;
    private String ticker;
    private String permaTicker;
    private String openFIGIComposite;
    private String assetType;
    private boolean isActive;
    private String countryCode;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getPermaTicker() {
        return permaTicker;
    }

    public void setPermaTicker(String permaTicker) {
        this.permaTicker = permaTicker;
    }

    public String getOpenFIGIComposite() {
        return openFIGIComposite;
    }

    public void setOpenFIGIComposite(String openFIGIComposite) {
        this.openFIGIComposite = openFIGIComposite;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TickerDetail that = (TickerDetail) o;
        return isActive == that.isActive && Objects.equals(name, that.name) && Objects.equals(ticker, that.ticker) && Objects.equals(permaTicker, that.permaTicker) && Objects.equals(openFIGIComposite, that.openFIGIComposite) && Objects.equals(assetType, that.assetType) && Objects.equals(countryCode, that.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ticker, permaTicker, openFIGIComposite, assetType, isActive, countryCode);
    }

    @Override
    public String toString() {
        return "CompanyDetail{" +
                "name='" + name + '\'' +
                ", ticker='" + ticker + '\'' +
                ", permaTicker='" + permaTicker + '\'' +
                ", openFIGIComposite='" + openFIGIComposite + '\'' +
                ", assetType='" + assetType + '\'' +
                ", isActive=" + isActive +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
