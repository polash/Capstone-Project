
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

public class Venue {

    public String name;
    public String location;
    public String timezone;

    public Venue(String name, String location, String timezone) {
        this.name = name;
        this.location = location;
        this.timezone = timezone;
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

    @Override
    public String toString() {
        return "Venue{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}
