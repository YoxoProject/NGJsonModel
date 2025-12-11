package fr.romaindu35.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SkillData implements BaseData {

    @SerializedName(value = "t", alternate = "timestamp")
    private Long timestamp;

    @SerializedName(value = "s", alternate = "server")
    private String server;

    @SerializedName(value = "d", alternate = "skills")
    private List<PlayerSkillData> skills = new ArrayList<>();

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlayerSkillData {
        @SerializedName("u")
        private String username;
        // --- Builder ---
        @SerializedName("b")
        private int builder;
        @SerializedName("bP")
        private int builderPosition;
        // --- Farmer ---
        @SerializedName("f")
        private int farmer;
        @SerializedName("fP")
        private int farmerPosition;
        // --- Hunter ---
        @SerializedName("h")
        private int hunter;
        @SerializedName("hP")
        private int hunterPosition;
        // --- Lumberjack ---
        @SerializedName("l")
        private int lumberjack;
        @SerializedName("lP")
        private int lumberjackPosition;
        // --- Engineer ---
        @SerializedName("e")
        private int engineer;
        @SerializedName("eP")
        private int engineerPosition;
        // --- Miner ---
        @SerializedName("m")
        private int miner;
        @SerializedName("mP")
        private int minerPosition;
    }
}