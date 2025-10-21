package fr.romaindu35.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Halloween2025Data implements BaseData {

    private Long timestamp;
    private String server;
    private List<PlayerHalloweenData> players;

    public Halloween2025Data(Long timestamp, String server, List<PlayerHalloweenData> players) {
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

    public List<PlayerHalloweenData> getPlayers() {
        return players;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setPlayers(List<PlayerHalloweenData> players) {
        this.players = players;
    }

    public static class Serializer implements JsonSerializer<Halloween2025Data> {
        @Override
        public JsonElement serialize(Halloween2025Data src, Type type, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            obj.addProperty("t", src.timestamp);
            obj.addProperty("s", src.server);
            if (src.players != null && !src.players.isEmpty()) {
                obj.add("p", context.serialize(src.players));
            }
            return obj;
        }
    }

    public static class Deserializer implements JsonDeserializer<Halloween2025Data> {
        @Override
        public Halloween2025Data deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject obj = json.getAsJsonObject();
            Long timestamp = obj.get("t").getAsLong();
            String server = obj.get("s").getAsString();
            List<PlayerHalloweenData> players = obj.has("p")
                    ? (List<PlayerHalloweenData>) context.deserialize(obj.get("p"), new com.google.gson.reflect.TypeToken<List<PlayerHalloweenData>>() {
            }.getType())
                    : new ArrayList<PlayerHalloweenData>();
            return new Halloween2025Data(timestamp, server, players);
        }
    }

    public static class PlayerHalloweenData {
        private String username;
        private Integer demonsKilled;
        private Integer tickets;
        private Integer lostSoulsCount;
        private Integer extractedSouls;
        private Integer lostSouls;

        public PlayerHalloweenData(String username, Integer demonsKilled, Integer tickets, Integer lostSoulsCount, Integer extractedSouls, Integer lostSouls) {
            this.username = username;
            this.demonsKilled = demonsKilled;
            this.tickets = tickets;
            this.lostSoulsCount = lostSoulsCount;
            this.extractedSouls = extractedSouls;
            this.lostSouls = lostSouls;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Integer getDemonsKilled() {
            return demonsKilled;
        }

        public void setDemonsKilled(Integer demonsKilled) {
            this.demonsKilled = demonsKilled;
        }

        public Integer getTickets() {
            return tickets;
        }

        public void setTickets(Integer tickets) {
            this.tickets = tickets;
        }

        public Integer getLostSoulsCount() {
            return lostSoulsCount;
        }

        public void setLostSoulsCount(Integer lostSoulsCount) {
            this.lostSoulsCount = lostSoulsCount;
        }

        public Integer getExtractedSouls() {
            return extractedSouls;
        }

        public void setExtractedSouls(Integer extractedSouls) {
            this.extractedSouls = extractedSouls;
        }

        public Integer getLostSouls() {
            return lostSouls;
        }

        public void setLostSouls(Integer lostSouls) {
            this.lostSouls = lostSouls;
        }

        public static class Serializer implements JsonSerializer<PlayerHalloweenData> {
            @Override
            public JsonElement serialize(PlayerHalloweenData src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                obj.addProperty("u", src.username);
                if (src.demonsKilled != null && src.demonsKilled != 0) obj.addProperty("dk", src.demonsKilled);
                if (src.tickets != null && src.tickets != 0) obj.addProperty("ti", src.tickets);
                if (src.lostSoulsCount != null && src.lostSoulsCount != 0) obj.addProperty("lsc", src.lostSoulsCount);
                if (src.extractedSouls != null && src.extractedSouls != 0) obj.addProperty("es", src.extractedSouls);
                if (src.lostSouls != null && src.lostSouls != 0) obj.addProperty("ls", src.lostSouls);
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<PlayerHalloweenData> {
            @Override
            public PlayerHalloweenData deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                String username = obj.get("u").getAsString();
                Integer demonsKilled = obj.has("dk") ? obj.get("dk").getAsInt() : 0;
                Integer tickets = obj.has("ti") ? obj.get("ti").getAsInt() : 0;
                Integer lostSoulsCount = obj.has("lsc") ? obj.get("lsc").getAsInt() : 0;
                Integer extractedSouls = obj.has("es") ? obj.get("es").getAsInt() : 0;
                Integer lostSouls = obj.has("ls") ? obj.get("ls").getAsInt() : 0;
                return new PlayerHalloweenData(username, demonsKilled, tickets, lostSoulsCount, extractedSouls, lostSouls);
            }
        }
    }
}
