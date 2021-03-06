package com.voidabhi.travelapp.models;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABHIJEET on 24-01-2015.
 * POJO for google places
 */
public class Place {

    // pojo properties
    private String name;
    private LatLng location;
    private String iconUrl;

    // json keys
    private static final String JSON_KEY_NAME = "name";
    private static final String JSON_KEY_GEOMETRY = "geometry";
    private static final String JSON_KEY_LOCATION = "location";
    private static final String JSON_KEY_ICON= "icon";
    private static final String JSON_KEY_LAT = "lat";
    private static final String JSON_KEY_LNG = "lng";

    public Place() {
        this.name = null;
        this.location = null;
        this.iconUrl = null;
    }

    public Place(String name, LatLng location,String iconUrl) {
        this.name = name;
        this.location = location;
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    // parsing place json object 
    public static Place fromJson(JSONObject jsonObject) {

        Place p = null;

        try {
            p = new Place();
            if (jsonObject.has(JSON_KEY_NAME))
                p.setName(jsonObject.getString(JSON_KEY_NAME));
            if (jsonObject.has(JSON_KEY_GEOMETRY)) {
                JSONObject geometryObject = jsonObject.getJSONObject(JSON_KEY_GEOMETRY);
                if(geometryObject.has(JSON_KEY_LOCATION))
                 p.setLocation(new LatLng(geometryObject.getJSONObject(JSON_KEY_LOCATION).getDouble(JSON_KEY_LAT), geometryObject.getJSONObject(JSON_KEY_LOCATION).getDouble(JSON_KEY_LNG)));
            }
            if(jsonObject.has(JSON_KEY_ICON)) {
                p.setIconUrl(jsonObject.getString(JSON_KEY_ICON));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;

    }

    // parsing places json array
    public static List<Place> fromJsonArray(JSONArray jsonArray) {

        try {
            ArrayList<Place> places = new ArrayList<>(jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject placeJson = null;

                placeJson = jsonArray.getJSONObject(i);
                Place place = Place.fromJson(placeJson);
                if (place != null) {
                    places.add(place);
                }
            }
            return places;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
