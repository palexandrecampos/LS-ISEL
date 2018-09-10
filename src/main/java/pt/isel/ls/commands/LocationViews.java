package pt.isel.ls.commands;

import pt.isel.ls.common.Writable;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class LocationViews {

    private String location;

    public Map<String, Writable> views;

    public LocationViews() {
        views = new HashMap<>();
    }

    public Writable getView(String option) {
        return views.get(option);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
