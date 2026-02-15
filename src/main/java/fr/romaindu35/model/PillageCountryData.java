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
public class PillageCountryData implements BaseData {

    @SerializedName("t")
    private Long timestamp;

    @SerializedName("s")
    private String server;

    @SerializedName("c")
    private List<PillageCountry> countries = new ArrayList<>();

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PillageCountry {

        @SerializedName("n")
        private String name;

        @SerializedName("l")
        private int level;

        @SerializedName("p")
        private int players;

        @SerializedName("np")
        private int notationPosition;

        @SerializedName("po")
        private int power;

        @SerializedName("mpo")
        private int powerMax;
    }
}