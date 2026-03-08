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
public class PlayerListData implements BaseData {

    @SerializedName("t")
    private Long timestamp;

    @SerializedName("s")
    private String server;

    @SerializedName("p")
    private List<PlayerData> players = new ArrayList<>();

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlayerData {

        @SerializedName("n")
        private String name;

        @SerializedName("c")
        private String country;

        @SerializedName("p")
        private int power;

        @SerializedName("pm")
        private int powerMax;

        @SerializedName("pt")
        private int playtime;

        @SerializedName("ptis")
        private int playtimeInterserver;

        @SerializedName("l")
        private Long lastLogin;

        @SerializedName("e")
        private int enterprisesCount;

        @SerializedName("s")
        private Skill bestSkill;

        @SerializedName("ip")
        private boolean isPrime;

        @SerializedName("g")
        private String grade;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Skill {
        @SerializedName("n")
        private String name;

        @SerializedName("l")
        private int level;
    }
}