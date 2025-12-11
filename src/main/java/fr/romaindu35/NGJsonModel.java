package fr.romaindu35;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import fr.romaindu35.compression.Compressor;
import fr.romaindu35.model.EnterpriseData;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class NGJsonModel {

    private static final Gson GSON = new GsonBuilder()
            // 1. Adapter pour les Integer : Si 0, on écrit null (et Gson ignorera le null)
            .registerTypeAdapter(Integer.class, new TypeAdapter<Integer>() {
                @Override
                public void write(JsonWriter out, Integer value) throws IOException {
                    if (value == null || value == 0) {
                        out.nullValue(); // Gson n'écrira pas le champ si serializeNulls() n'est pas activé
                    } else {
                        out.value(value);
                    }
                }

                @Override
                public Integer read(JsonReader in) throws IOException {
                    if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                        in.nextNull();
                        return 0; // Valeur par défaut à la lecture
                    }
                    return in.nextInt();
                }
            })
            // 2. Adapter pour les int (primitifs) : Pareil
            .registerTypeAdapter(int.class, new TypeAdapter<Number>() {
                @Override
                public void write(JsonWriter out, Number value) throws IOException {
                    if (value == null || value.intValue() == 0) {
                        out.nullValue();
                    } else {
                        out.value(value);
                    }
                }

                @Override
                public Number read(JsonReader in) throws IOException {
                    if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                        in.nextNull();
                        return 0;
                    }
                    return in.nextInt();
                }
            })
            // 3. Adapter pour les boolean : Si false, on écrit null
            .registerTypeAdapter(boolean.class, new TypeAdapter<Boolean>() {
                @Override
                public void write(JsonWriter out, Boolean value) throws IOException {
                    if (value == null || !value) {
                        out.nullValue();
                    } else {
                        out.value(1);
                    }
                }

                @Override
                public Boolean read(JsonReader in) throws IOException {
                    if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                        in.nextNull();
                        return false;
                    }
                    if (in.peek() == JsonToken.BOOLEAN) { // Gère le cas où la valeur est un booléen (ancienne version)
                        return in.nextBoolean();
                    }
                    return in.nextInt() != 0;
                }
            })
            .registerTypeAdapter(EnterpriseData.Enterprise.class, new EnterpriseData.Enterprise.Deserializer()) // Permet de parser les différents types d'entreprises (CASINO, FARM, etc.)
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
        return GSON.fromJson(json, clazz);
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
        return GSON.fromJson(json, listType);
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
        return GSON.fromJson(json, mapType);
    }

    public static void writeObjectToJson(Path path, Object object) throws IOException {
        writeObjectToJson(path, object, null);
    }

    public static void writeObjectToJson(Path path, Object object, Compressor compressor) throws IOException {
        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        String json = GSON.toJson(object);
        byte[] data = json.getBytes(StandardCharsets.UTF_8);
        if (compressor != null) {
            data = compressor.compress(data);
        }
        Files.write(path, data);
    }

    public static Gson getGson() {
        return GSON;
    }
}