package com.example.brojekt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class CarJasonParser {
    public static List<Car> getObjectFromJason(String jason) {
        List<Car> cars;
        try {
            JSONArray jsonArray = new JSONArray(jason);
            cars = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                Car car = new Car();
                car.setAccidents(jsonObject.getBoolean("accidents"));
                car.setDistance(jsonObject.getInt("distance"));
                car.setMake(jsonObject.getString("make"));
                car.setModel(jsonObject.getString("model"));
                car.setOffers(jsonObject.getBoolean("offers"));
                car.setYear(jsonObject.getInt("year"));
                car.setPrice(jsonObject.getInt("price"));
                cars.add(car);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return cars;
    }
}
