package com.example.collectorsgarage.collectorGarage;

import com.google.gson.Gson;

public class GenericUtils {

    public static <T> String serialize(T object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static <T> T deserialize(String json, Class<T> object) {
        try {
            Gson gson = new Gson();
            return object.cast(gson.fromJson(json, object));
        } catch (Exception ignore) {
            return null;
        }
    }
}
