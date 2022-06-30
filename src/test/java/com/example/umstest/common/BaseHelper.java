package com.example.umstest.common;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

public class BaseHelper {
    public static <T> T getJsonResourceData(String resource, Class<T> responseClass) throws FileNotFoundException {
        String url = Objects.requireNonNull(BaseHelper.class.getClassLoader().getResource(resource)).getPath();

        JsonReader reader = new JsonReader(new FileReader(url));
        return new Gson().fromJson(reader, responseClass);
    }
}
