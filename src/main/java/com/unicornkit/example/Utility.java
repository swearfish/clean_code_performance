package com.unicornkit.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public final class Utility {

    private Utility() {}

    public static BufferedReader openResource(String resourceName)
            throws FileNotFoundException {
        return openResource(resourceName, Utility.class);
    }

    public static BufferedReader openResource(String resourceName, Class<?> clazz)
            throws FileNotFoundException {
        var is = clazz.getClassLoader().getResourceAsStream(resourceName);
        if (is == null) {
            throw new FileNotFoundException("Cannot find resource");
        }

        return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
    }
}
