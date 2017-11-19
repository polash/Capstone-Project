package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

/**
 * Created by sksho on 19-Nov-17.
 */

class Venue {
    private String name;
    private String location;
    private String timezone;
    private String lat;
    private String longitude;


    public Venue(String name, String location, String timezone, String lat, String longitude) {
        this.name = name;
        this.location = location;
        this.timezone = timezone;
        this.lat = lat;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getLat() {
        return lat;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", timezone='" + timezone + '\'' +
                ", lat='" + lat + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
