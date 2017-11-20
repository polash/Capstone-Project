
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

public class Venue {

    private String name;
    private String location;
    private String timezone;
    private String lat;
    private String _long;


    public Venue(String name, String location, String timezone, String lat, String _long) {
        this.name = name;
        this.location = location;
        this.timezone = timezone;
        this.lat = lat;
        this._long = _long;
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

    public String get_long() {
        return _long;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", timezone='" + timezone + '\'' +
                ", lat='" + lat + '\'' +
                ", _long='" + _long + '\'' +
                '}';
    }
}
