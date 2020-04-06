package com.bigsoftware.core.data;

import java.util.Date;
import java.util.List;

/**
 * This class is an object representation of each line item of COVID-19 cases in the US, per CSSE at Johns Hopkins University.
 */
public class Datum {
    private String UID;
    private String iso2;
    private String iso3;
    private String code3;
    private String FIPS;
    private String admin2;
    private String provinceState;
    private String countryRegion;
    private String latitude;
    private String longitude;
    private String combinedKey;
    private List<DateValuePair> casualties;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getCode3() {
        return code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public String getFIPS() {
        return FIPS;
    }

    public void setFIPS(String FIPS) {
        this.FIPS = FIPS;
    }

    public String getAdmin2() {
        return admin2;
    }

    public void setAdmin2(String admin2) {
        this.admin2 = admin2;
    }

    public String getProvinceState() {
        return provinceState;
    }

    public void setProvinceState(String provinceState) {
        this.provinceState = provinceState;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCombinedKey() {
        return combinedKey;
    }

    public void setCombinedKey(String combinedKey) {
        this.combinedKey = combinedKey;
    }

    public List<DateValuePair> getCasualties() {
        return casualties;
    }

    public void setCasualties(List<DateValuePair> casualties) {
        this.casualties = casualties;
    }


    /**
     * Each value represents the number of cases of COVID-19 corresponding to its date.
     */
    public static class DateValuePair{

        Date date;
        int value;

        public Date getDate() {
            return date;
        }

        void setDate(Date date) {
            this.date = date;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }


    }
}
