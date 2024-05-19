package com.mexicandeveloper.iplocation.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ip_geo_location_table")
public class IPGeolocationEntity {
    @PrimaryKey
    @NonNull
    public String ipQuery;
    public Long timeStamp;
    public String status;
    public String country;
    public String countryCode;
    public String region;
    public String regionName;
    public String city;
    public String zip;
    public double lat;
    public double lon;
    public String timezone;
    public String isp;
    public String org;
    public String as;

    public IPGeolocationEntity(@NonNull String ipQuery, Long timeStamp, String status, String country, String countryCode, String region, String regionName, String city, String zip, double lat, double lon, String timezone, String isp, String org, String as) {
        this.ipQuery = ipQuery;
        this.timeStamp = timeStamp;
        this.status = status;
        this.country = country;
        this.countryCode = countryCode;
        this.region = region;
        this.regionName = regionName;
        this.city = city;
        this.zip = zip;
        this.lat = lat;
        this.lon = lon;
        this.timezone = timezone;
        this.isp = isp;
        this.org = org;
        this.as = as;
    }

    @NonNull
    public String getIpQuery() {
        return ipQuery;
    }

    public void setIpQuery(@NonNull String ipQuery) {
        this.ipQuery = ipQuery;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }

    @NonNull
    public String toString() {

        return "ipQuery = " + ipQuery + "\n" +
                "timeStamp = " + timeStamp + "\n" +
                "status = " + status + "\n" +
                "country = " + country + "\n" +
                "countryCode = " + countryCode + "\n" +
                "region = " + region + "\n" +
                "regionName = " + regionName + "\n" +
                "city = " + city + "\n" +
                "zip = " + zip + "\n" +
                "lat = " + lat + "\n" +
                "lon = " + lon + "\n" +
                "timezone = " + timezone + "\n" +
                "isp = " + isp + "\n" +
                "org = " + org + "\n" +
                "as = " + as + "\n";
    }

}
