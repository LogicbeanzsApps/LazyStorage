package com.martinchamarro.lazystorage.internal.database;

import com.google.gson.Gson;
import com.martinchamarro.lazystorage.internal.exception.JsonConversionException;
import com.martinchamarro.lazystorage.internal.exception.LazyStorageException;

final class TwoWaysJsonConverter {

    private Gson gson;

    public TwoWaysJsonConverter() {
        gson = new Gson();
    }

    public String convert(Object object) throws LazyStorageException {
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            throw new JsonConversionException("It's not possible to convert the object to Json.", e);
        }
    }

    public <T> T convert(String json, Class<T> classOfT) throws LazyStorageException {
        T object = gson.fromJson(json, classOfT);
        if (object == null) throw new JsonConversionException("Exception trying to convert JSON to object.");
        return object;
    }

}