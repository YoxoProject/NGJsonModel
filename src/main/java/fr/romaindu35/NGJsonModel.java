package fr.romaindu35;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.romaindu35.model.EnterpriseData;
import fr.romaindu35.model.PlayerListData;
import fr.romaindu35.model.SkillData;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class NGJsonModel {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(SkillData.PlayerSkillData.class, new SkillData.PlayerSkillData.Serializer())
            .registerTypeAdapter(SkillData.PlayerSkillData.class, new SkillData.PlayerSkillData.Deserializer())
            .registerTypeAdapter(SkillData.class, new SkillData.Serializer())
            .registerTypeAdapter(SkillData.class, new SkillData.Deserializer())

            .registerTypeAdapter(PlayerListData.PlayerData.class, new PlayerListData.PlayerData.Serializer())
            .registerTypeAdapter(PlayerListData.PlayerData.class, new PlayerListData.PlayerData.Deserializer())
            .registerTypeAdapter(PlayerListData.class, new PlayerListData.Serializer())
            .registerTypeAdapter(PlayerListData.class, new PlayerListData.Deserializer())

            .registerTypeAdapter(EnterpriseData.class, new EnterpriseData.Serializer())
            .registerTypeAdapter(EnterpriseData.class, new EnterpriseData.Deserializer())
            .registerTypeAdapter(EnterpriseData.Enterprise.class, new EnterpriseData.Enterprise.Serializer())
            .registerTypeAdapter(EnterpriseData.Enterprise.class, new EnterpriseData.Enterprise.Deserializer())
            .registerTypeAdapter(EnterpriseData.Permission.class, new EnterpriseData.Permission.Serializer())
            .registerTypeAdapter(EnterpriseData.Permission.class, new EnterpriseData.Permission.Deserializer())
            .registerTypeAdapter(EnterpriseData.EnterpriseBet.Bet.class, new EnterpriseData.EnterpriseBet.Bet.Serializer())
            .registerTypeAdapter(EnterpriseData.EnterpriseBet.Bet.class, new EnterpriseData.EnterpriseBet.Bet.Deserializer())
            .registerTypeAdapter(EnterpriseData.EnterpriseParcelle.Parcelle.class, new EnterpriseData.EnterpriseParcelle.Parcelle.Serializer())
            .registerTypeAdapter(EnterpriseData.EnterpriseParcelle.Parcelle.class, new EnterpriseData.EnterpriseParcelle.Parcelle.Deserializer())
            .registerTypeAdapter(EnterpriseData.EnterpriseParcelle.Coords.class, new EnterpriseData.EnterpriseParcelle.Coords.Serializer())
            .registerTypeAdapter(EnterpriseData.EnterpriseParcelle.Coords.class, new EnterpriseData.EnterpriseParcelle.Coords.Deserializer())
            .registerTypeAdapter(EnterpriseData.EnterpriseBank.class, new EnterpriseData.EnterpriseBank.Serializer())
            .registerTypeAdapter(EnterpriseData.EnterpriseBank.class, new EnterpriseData.EnterpriseBank.Deserializer())
            .registerTypeAdapter(EnterpriseData.EnterpriseBank.Log.class, new EnterpriseData.EnterpriseBank.Log.Serializer())
            .registerTypeAdapter(EnterpriseData.EnterpriseBank.Log.class, new EnterpriseData.EnterpriseBank.Log.Deserializer())
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

    public static Gson getGson() {
        return gson;
    }
}