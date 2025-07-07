package fr.romaindu35.model;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SkillData implements BaseData {

    private final Long timestamp;
    private final String server;
    private final List<PlayerSkillData> skills;

    public SkillData(Long timestamp, String server, List<PlayerSkillData> skills) {
        this.timestamp = timestamp;
        this.server = server;
        this.skills = skills;
    }

    @Override
    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public String getServer() {
        return server;
    }

    public List<PlayerSkillData> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return "SkillData{" +
                "timestamp=" + timestamp +
                ", server='" + server + '\'' +
                ", skills=" + skills +
                '}';
    }

    public static class Serializer implements JsonSerializer<SkillData> {
        @Override
        public JsonElement serialize(SkillData src, Type type, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            obj.addProperty("timestamp", src.getTimestamp());
            obj.addProperty("server", src.getServer());
            JsonArray arr = new JsonArray();
            for (SkillData.PlayerSkillData p : src.getSkills()) {
                arr.add(context.serialize(p));
            }
            obj.add("skills", arr);
            return obj;
        }
    }

    public static class Deserializer implements JsonDeserializer<SkillData> {
        @Override
        public SkillData deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject obj = json.getAsJsonObject();
            Long timestamp = obj.get("timestamp").getAsLong();
            String server = obj.get("server").getAsString();
            List<PlayerSkillData> skills = new ArrayList<>();
            JsonArray arr = obj.getAsJsonArray("skills");
            for (JsonElement el : arr) {
                skills.add((PlayerSkillData) context.deserialize(el, PlayerSkillData.class));
            }
            return new SkillData(timestamp, server, skills);
        }
    }

    public static class PlayerSkillData {
        private final String username;

        private final Integer builder;
        private final Integer farmer;
        private final Integer hunter;
        private final Integer lumberjack;
        private final Integer engineer;
        private final Integer miner;

        private final Integer builderPosition;
        private final Integer farmerPosition;
        private final Integer hunterPosition;
        private final Integer lumberjackPosition;
        private final Integer engineerPosition;
        private final Integer minerPosition;

        public PlayerSkillData(String username, Integer builder, Integer farmer, Integer hunter, Integer lumberjack, Integer engineer, Integer miner, Integer builderPosition, Integer farmerPosition, Integer hunterPosition, Integer lumberjackPosition, Integer engineerPosition, Integer minerPosition) {
            this.username = username;
            this.builder = builder;
            this.farmer = farmer;
            this.hunter = hunter;
            this.lumberjack = lumberjack;
            this.engineer = engineer;
            this.miner = miner;
            this.builderPosition = builderPosition;
            this.farmerPosition = farmerPosition;
            this.hunterPosition = hunterPosition;
            this.lumberjackPosition = lumberjackPosition;
            this.engineerPosition = engineerPosition;
            this.minerPosition = minerPosition;
        }

        public String getUsername() {
            return username;
        }

        public Integer getBuilder() {
            return builder;
        }

        public Integer getFarmer() {
            return farmer;
        }

        public Integer getHunter() {
            return hunter;
        }

        public Integer getLumberjack() {
            return lumberjack;
        }

        public Integer getEngineer() {
            return engineer;
        }

        public Integer getMiner() {
            return miner;
        }

        public Integer getBuilderPosition() {
            return builderPosition;
        }

        public Integer getFarmerPosition() {
            return farmerPosition;
        }

        public Integer getHunterPosition() {
            return hunterPosition;
        }

        public Integer getLumberjackPosition() {
            return lumberjackPosition;
        }

        public Integer getEngineerPosition() {
            return engineerPosition;
        }

        public Integer getMinerPosition() {
            return minerPosition;
        }

        @Override
        public String toString() {
            return "PlayerSkillData{" +
                    "username='" + username + '\'' +
                    ", builder=" + builder +
                    ", farmer=" + farmer +
                    ", hunter=" + hunter +
                    ", lumberjack=" + lumberjack +
                    ", engineer=" + engineer +
                    ", miner=" + miner +
                    ", builderPosition=" + builderPosition +
                    ", farmerPosition=" + farmerPosition +
                    ", hunterPosition=" + hunterPosition +
                    ", lumberjackPosition=" + lumberjackPosition +
                    ", engineerPosition=" + engineerPosition +
                    ", minerPosition=" + minerPosition +
                    '}';
        }

        public static class Serializer implements JsonSerializer<PlayerSkillData> {
            @Override
            public JsonElement serialize(PlayerSkillData src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                obj.addProperty("u", src.getUsername());
                if (src.getBuilder() > 0) {
                    obj.addProperty("b", src.getBuilder());
                    obj.addProperty("bP", src.getBuilderPosition());
                }
                if (src.getFarmer() > 0) {
                    obj.addProperty("f", src.getFarmer());
                    obj.addProperty("fP", src.getFarmerPosition());
                }
                if (src.getHunter() > 0) {
                    obj.addProperty("h", src.getHunter());
                    obj.addProperty("hP", src.getHunterPosition());
                }
                if (src.getLumberjack() > 0) {
                    obj.addProperty("l", src.getLumberjack());
                    obj.addProperty("lP", src.getLumberjackPosition());
                }
                if (src.getEngineer() > 0) {
                    obj.addProperty("e", src.getEngineer());
                    obj.addProperty("eP", src.getEngineerPosition());
                }
                if (src.getMiner() > 0) {
                    obj.addProperty("m", src.getMiner());
                    obj.addProperty("mP", src.getMinerPosition());
                }
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<PlayerSkillData> {
            @Override
            public PlayerSkillData deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                String username = obj.get("u").getAsString();

                int builder = obj.has("b") ? obj.get("b").getAsInt() : 0;
                int farmer = obj.has("f") ? obj.get("f").getAsInt() : 0;
                int hunter = obj.has("h") ? obj.get("h").getAsInt() : 0;
                int lumberjack = obj.has("l") ? obj.get("l").getAsInt() : 0;
                int engineer = obj.has("e") ? obj.get("e").getAsInt() : 0;
                int miner = obj.has("m") ? obj.get("m").getAsInt() : 0;

                int builderPosition = obj.has("bP") ? obj.get("bP").getAsInt() : 0;
                int farmerPosition = obj.has("fP") ? obj.get("fP").getAsInt() : 0;
                int hunterPosition = obj.has("hP") ? obj.get("hP").getAsInt() : 0;
                int lumberjackPosition = obj.has("lP") ? obj.get("lP").getAsInt() : 0;
                int engineerPosition = obj.has("eP") ? obj.get("eP").getAsInt() : 0;
                int minerPosition = obj.has("mP") ? obj.get("mP").getAsInt() : 0;

                return new PlayerSkillData(
                        username, builder, farmer, hunter, lumberjack, engineer, miner,
                        builderPosition, farmerPosition, hunterPosition, lumberjackPosition, engineerPosition, minerPosition
                );
            }
        }
    }
}