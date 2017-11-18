package com.sksanwar.cricketbangla.Pojo;

/**
 * Created by sksho on 18-Nov-17.
 */

class CricketBanglaLiveMatchVenue {

    private String name;
    private String location;
    private String timezone;
    private String lat;
    private String longitude;

    public CricketBanglaLiveMatchVenue(String name, String location, String timezone, String lat, String longitude) {
        this.name = name;
        this.location = location;
        this.timezone = timezone;
        this.lat = lat;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "CricketBanglaLiveMatchVenue{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", timezone='" + timezone + '\'' +
                ", lat='" + lat + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
