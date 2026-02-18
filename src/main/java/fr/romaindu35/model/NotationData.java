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
public class NotationData implements BaseData {

    @SerializedName("t")
    private Long timestamp;

    @SerializedName("s")
    private String server;

    @SerializedName("n")
    private List<Notation> notations = new ArrayList<>();

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Notation {

        @SerializedName("c")
        private String country;

        @SerializedName("s")
        private String server;

        @SerializedName("a")
        private double activity;

        @SerializedName("g")
        private double gestion;

        @SerializedName("sk")
        private double skills;

        @SerializedName("e")
        private double economy;

        @SerializedName("m")
        private double military;

        @SerializedName("am")
        private int antimatter;

        @SerializedName("rm")
        private int redmatter;

        @SerializedName("eb")
        private int endbringer;

        @SerializedName("sp")
        private int spatial;

        @SerializedName("u")
        private int unesco;

        @SerializedName("na")
        private double noteArchitecturale;

        @SerializedName("ar")
        private Architecture architecture;

        @SerializedName("to")
        private double total;

        @SerializedName("ra")
        private int rang;

        @SerializedName("ri")
        private int rangInterserv;

        @SerializedName("bo")
        private double bourse;

        @SerializedName("bu")
        private double bourseUnesco;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Architecture {

        @SerializedName("te")
        private int terraforming;

        @SerializedName("cs")
        private int coherenceStyle;

        @SerializedName("ar")
        private int activiteRecente;

        @SerializedName("bc")
        private int blocsCatalogue;

        @SerializedName("tm")
        private int trouMissiles;

        @SerializedName("bg")
        private double beauteGenerale;

        @SerializedName("hm")
        private int habitabiliteMaison;

        @SerializedName("bi")
        private int biomeCoherent;

        @SerializedName("ba")
        private int batimentsAbandonnes;

        @SerializedName("us")
        private int utilisationSchematica;

        @SerializedName("cl")
        private int coherenceLumieres;

        @SerializedName("rp")
        private int roleplayPays;

        @SerializedName("or")
        private int organics;

        @SerializedName("sc")
        private double surfaceConstruite;

        @SerializedName("st")
        private String staff;
    }
}