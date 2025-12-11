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
public class Halloween2025Data implements BaseData {

    @SerializedName("t")
    private Long timestamp;

    @SerializedName("s")
    private String server;

    @SerializedName("p")
    private List<PlayerHalloweenData> players = new ArrayList<>();

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlayerHalloweenData {

        @SerializedName("u")
        private String username;

        @SerializedName("dk")
        private int demonsKilled;

        @SerializedName("ti")
        private int tickets;

        @SerializedName("lsc")
        private int lostSoulsCount;

        @SerializedName("es")
        private int extractedSouls;

        @SerializedName("ls")
        private int lostSouls;
    }
}