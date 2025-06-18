package fr.romaindu35;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class NGJsonModel {

    private static final Gson gson = new Gson();

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static <T> T fromJson(File json, Class<T> clazz) throws IOException {
        List<String> json_list = Files.readAllLines(json.toPath(), StandardCharsets.UTF_8);
        String jsonContent = "";
        for (String line : json_list) {
            jsonContent += line;
        }
        return fromJson(jsonContent, clazz);
    }
}