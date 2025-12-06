package fr.romaindu35;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.romaindu35.compression.Compressor;
import fr.romaindu35.model.CountryData;
import fr.romaindu35.model.EnterpriseData;
import fr.romaindu35.model.Halloween2025Data;
import fr.romaindu35.model.PlayerListData;
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

            .registerTypeAdapter(PlayerListData.PlayerData.class, new PlayerListData.PlayerData.Serializer())
            .registerTypeAdapter(PlayerListData.PlayerData.class, new PlayerListData.PlayerData.Deserializer())
            .registerTypeAdapter(PlayerListData.class, new PlayerListData.Serializer())
            .registerTypeAdapter(PlayerListData.class, new PlayerListData.Deserializer())

            .registerTypeAdapter(Halloween2025Data.PlayerHalloweenData.class, new Halloween2025Data.PlayerHalloweenData.Serializer())
            .registerTypeAdapter(Halloween2025Data.PlayerHalloweenData.class, new Halloween2025Data.PlayerHalloweenData.Deserializer())
            .registerTypeAdapter(Halloween2025Data.class, new Halloween2025Data.Serializer())
            .registerTypeAdapter(Halloween2025Data.class, new Halloween2025Data.Deserializer())

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

            .registerTypeAdapter(CountryData.class, new CountryData.Serializer())
            .registerTypeAdapter(CountryData.class, new CountryData.Deserializer())
            .registerTypeAdapter(CountryData.Country.class, new CountryData.Country.Serializer())
            .registerTypeAdapter(CountryData.Country.class, new CountryData.Country.Deserializer())
            .registerTypeAdapter(CountryData.FHomeCoord.class, new CountryData.FHomeCoord.Serializer())
            .registerTypeAdapter(CountryData.FHomeCoord.class, new CountryData.FHomeCoord.Deserializer())
            .registerTypeAdapter(CountryData.Bank.class, new CountryData.Bank.Serializer())
            .registerTypeAdapter(CountryData.Bank.class, new CountryData.Bank.Deserializer())
            .registerTypeAdapter(CountryData.BankLog.class, new CountryData.BankLog.Serializer())
            .registerTypeAdapter(CountryData.BankLog.class, new CountryData.BankLog.Deserializer())
            .registerTypeAdapter(CountryData.Actions.class, new CountryData.Actions.Serializer())
            .registerTypeAdapter(CountryData.Actions.class, new CountryData.Actions.Deserializer())
            .registerTypeAdapter(CountryData.DividendeClassement.class, new CountryData.DividendeClassement.Serializer())
            .registerTypeAdapter(CountryData.DividendeClassement.class, new CountryData.DividendeClassement.Deserializer())
            .registerTypeAdapter(CountryData.DividendeHistory.class, new CountryData.DividendeHistory.Serializer())
            .registerTypeAdapter(CountryData.DividendeHistory.class, new CountryData.DividendeHistory.Deserializer())
            .registerTypeAdapter(CountryData.Settings.class, new CountryData.Settings.Serializer())
            .registerTypeAdapter(CountryData.Settings.class, new CountryData.Settings.Deserializer())
            .registerTypeAdapter(CountryData.Permission.class, new CountryData.Permission.Serializer())
            .registerTypeAdapter(CountryData.Permission.class, new CountryData.Permission.Deserializer())
            .registerTypeAdapter(CountryData.SettingLog.class, new CountryData.SettingLog.Serializer())
            .registerTypeAdapter(CountryData.SettingLog.class, new CountryData.SettingLog.Deserializer())

            .create();

    public static <T> T loadObjectFromJson(Path path, Class<T> clazz) throws IOException {
        return loadObjectFromJson(path, clazz, null);
    }

    public static <T> T loadObjectFromJson(Path path, Class<T> clazz, Compressor compressor) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        byte[] fileData = Files.readAllBytes(path);
        if (compressor != null && fileData.length > 0) {
            fileData = compressor.decompress(fileData);
        }
        String json = new String(fileData, StandardCharsets.UTF_8);
        return gson.fromJson(json, clazz);
    }

    public static <T> List<T> loadListObjectFromJson(Path path, Class<T> clazz) throws IOException {
        return loadListObjectFromJson(path, clazz, null);
    }

    public static <T> List<T> loadListObjectFromJson(Path path, Class<T> clazz, Compressor compressor) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        byte[] fileData = Files.readAllBytes(path);
        if (compressor != null && fileData.length > 0) {
            fileData = compressor.decompress(fileData);
        }
        String json = new String(fileData, StandardCharsets.UTF_8);
        Type listType = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(json, listType);
    }

    public static <T, V> Map<T, V> loadMapObjectFromJson(Path path, Class<T> keyClass, Class<V> valueClass) throws IOException {
        return loadMapObjectFromJson(path, keyClass, valueClass, null);
    }

    public static <T, V> Map<T, V> loadMapObjectFromJson(Path path, Class<T> keyClass, Class<V> valueClass, Compressor compressor) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        byte[] fileData = Files.readAllBytes(path);
        if (compressor != null && fileData.length > 0) {
            fileData = compressor.decompress(fileData);
        }
        String json = new String(fileData, StandardCharsets.UTF_8);
        Type mapType = TypeToken.getParameterized(Map.class, keyClass, valueClass).getType();
        return gson.fromJson(json, mapType);
    }

    public static void writeObjectToJson(Path path, Object object) throws IOException {
        writeObjectToJson(path, object, null);
    }

    public static void writeObjectToJson(Path path, Object object, Compressor compressor) throws IOException {
        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        String json = gson.toJson(object);
        byte[] data = json.getBytes(StandardCharsets.UTF_8);
        if (compressor != null) {
            data = compressor.compress(data);
        }
        Files.write(path, data);
    }

    public static Gson getGson() {
        return gson;
    }
}