package fr.romaindu35;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.romaindu35.model.SkillData;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class NGJsonModel {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(SkillData.PlayerSkillData.class, new SkillData.PlayerSkillData.Serializer())
            .registerTypeAdapter(SkillData.PlayerSkillData.class, new SkillData.PlayerSkillData.Deserializer())
            .registerTypeAdapter(SkillData.class, new SkillData.Serializer())
            .registerTypeAdapter(SkillData.class, new SkillData.Deserializer())
            .create();

    public static <T> T loadObjectFromJson(Path path, Class<T> clazz) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        String json = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        return gson.fromJson(json, clazz);
    }

    public static <T> List<T> loadListObjectFromJson(Path path, Class<T> clazz) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        String json = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        Type listType = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(json, listType);
    }

    public static <T, V> Map<T, V> loadMapObjectFromJson(Path path, Class<T> keyClass, Class<V> valueClass) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        String json = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        Type mapType = TypeToken.getParameterized(Map.class, keyClass, valueClass).getType();
        return gson.fromJson(json, mapType);
    }

    public static void writeObjectToJson(Path path, Object object) throws IOException {
        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        String json = gson.toJson(object);
        Files.write(path, json.getBytes(StandardCharsets.UTF_8));
    }
}