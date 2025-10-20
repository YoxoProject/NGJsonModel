package fr.romaindu35.model;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PlayerListData implements BaseData {

    private Long timestamp;
    private String server;
    private List<PlayerData> players;

    public PlayerListData(Long timestamp, String server, List<PlayerData> players) {
        this.timestamp = timestamp;
        this.server = server;
        this.players = players;
    }

    @Override
    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public String getServer() {
        return server;
    }

    public List<PlayerData> getPlayers() {
        return players;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setPlayers(List<PlayerData> players) {
        this.players = players;
    }

    public static class Serializer implements JsonSerializer<PlayerListData> {
        @Override
        public JsonElement serialize(PlayerListData src, Type type, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            obj.addProperty("t", src.timestamp);
            obj.addProperty("s", src.server);
            if (src.players != null && !src.players.isEmpty()) {
                obj.add("p", context.serialize(src.players));
            }
            return obj;
        }
    }

    public static class Deserializer implements JsonDeserializer<PlayerListData> {
        @Override
        public PlayerListData deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject obj = json.getAsJsonObject();
            Long timestamp = obj.get("t").getAsLong();
            String server = obj.get("s").getAsString();
            List<PlayerData> players = obj.has("p")
                ? (List<PlayerData>) context.deserialize(obj.get("p"), new com.google.gson.reflect.TypeToken<List<PlayerData>>(){}.getType())
                : new ArrayList<PlayerData>();
            return new PlayerListData(timestamp, server, players);
        }
    }

    public static class PlayerData {
        private String name;
        private String country;
        private Integer power;
        private Integer powerMax;
        private Integer playtime;
        private Long lastLogin; // Timestamp Unix en secondes (pas millisecondes pour compacter)

        public PlayerData(String name, String country, Integer power, Integer powerMax, Integer playtime, Long lastLogin) {
            this.name = name;
            this.country = country;
            this.power = power;
            this.powerMax = powerMax;
            this.playtime = playtime;
            this.lastLogin = lastLogin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Integer getPower() {
            return power;
        }

        public void setPower(Integer power) {
            this.power = power;
        }

        public Integer getPowerMax() {
            return powerMax;
        }

        public void setPowerMax(Integer powerMax) {
            this.powerMax = powerMax;
        }

        public Integer getPlaytime() {
            return playtime;
        }

        public void setPlaytime(Integer playtime) {
            this.playtime = playtime;
        }

        public Long getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(Long lastLogin) {
            this.lastLogin = lastLogin;
        }

        public static class Serializer implements JsonSerializer<PlayerData> {
            @Override
            public JsonElement serialize(PlayerData src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                obj.addProperty("n", src.name);
                if (src.country != null && !src.country.isEmpty()) obj.addProperty("c", src.country);
                if (src.power != null && src.power != 0) obj.addProperty("p", src.power);
                if (src.powerMax != null && src.powerMax != 0) obj.addProperty("pm", src.powerMax);
                if (src.playtime != null && src.playtime != 0) obj.addProperty("pt", src.playtime);
                if (src.lastLogin != null && src.lastLogin != 0) obj.addProperty("l", src.lastLogin);
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<PlayerData> {
            @Override
            public PlayerData deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                String name = obj.get("n").getAsString();
                String country = obj.has("c") ? obj.get("c").getAsString() : "";
                Integer power = obj.has("p") ? obj.get("p").getAsInt() : 0;
                Integer powerMax = obj.has("pm") ? obj.get("pm").getAsInt() : 0;
                Integer playtime = obj.has("pt") ? obj.get("pt").getAsInt() : 0;
                Long lastLogin = obj.has("l") ? obj.get("l").getAsLong() : 0L;
                return new PlayerData(name, country, power, powerMax, playtime, lastLogin);
            }
        }
    }
}