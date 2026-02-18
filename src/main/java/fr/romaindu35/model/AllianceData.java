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
public class AllianceData implements BaseData {

    @SerializedName("t")
    private Long timestamp;

    @SerializedName("s")
    private String server;

    @SerializedName("a")
    private List<Alliance> alliances = new ArrayList<>();

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Alliance {
        @SerializedName("n")
        private String name;

        @SerializedName("d")
        private String description;

        @SerializedName("ca")
        private long createdAt;

        @SerializedName("co")
        private String color;

        @SerializedName("t")
        private List<String> tags = new ArrayList<>();

        @SerializedName("di")
        private String discord;

        @SerializedName("yt")
        private String youtube;

        @SerializedName("ru")
        private String rules;

        @SerializedName("bu")
        private String bannerUrl;

        @SerializedName("bl")
        private String blazon;

        @SerializedName("io")
        private boolean isOpen;

        @SerializedName("ra")
        private int ranking;

        @SerializedName("pp")
        private int prestigePoints;

        @SerializedName("smc")
        private int stationModuleCount;

        @SerializedName("cc")
        private int countryCount;

        @SerializedName("le")
        private List<String> leaders = new ArrayList<>();

        @SerializedName("me")
        private List<String> members = new ArrayList<>();

        @SerializedName("pr")
        private List<String> protectorats = new ArrayList<>();

        @SerializedName("cl")
        private int claims;

        @SerializedName("cm")
        private int countMembers;

        @SerializedName("tx")
        private Taxes taxes;

        @SerializedName("m")
        private List<Module> modules = new ArrayList<>();

        @SerializedName("b")
        private Bank bank;

        @SerializedName("vh")
        private List<VoteHistory> votesHistory = new ArrayList<>();

        @SerializedName("re")
        private List<Relation> relations = new ArrayList<>();

        @SerializedName("bp")
        private PrimeBoost primeBoost;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Taxes {
        @SerializedName("ht")
        private double hdvTax;

        @SerializedName("ws")
        private double weeklySalary;

        @SerializedName("st")
        private double stationTicket;

        @SerializedName("nt")
        private double notationTax;

        @SerializedName("jt")
        private double joinTax;

        @SerializedName("mtc")
        private double moonTeleportCost;

        @SerializedName("fc")
        private double fuelCost;

    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Module {
        @SerializedName("i")
        private String id;

        @SerializedName("d")
        private String direction;

        @SerializedName("c")
        private ModuleCoordinates coordinates;

        @SerializedName("n")
        private String name;

        @SerializedName("t")
        private String description;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModuleCoordinates {
        private int x;
        private int z;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Bank {
        @SerializedName("cab")
        private double currentAccountBalance;

        @SerializedName("bab")
        private double blockedAccountBalance;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteHistory {
        @SerializedName("t")
        private String type;

        @SerializedName("i")
        private String initiator;

        @SerializedName("ic")
        private String initiatorCountry;

        @SerializedName("ts")
        private long timestamp;

        @SerializedName("m")
        private String message;

        @SerializedName("ov")
        private String oldValue;

        @SerializedName("nv")
        private String newValue;

        @SerializedName("tc")
        private String targetCountry;

        @SerializedName("yv")
        private int yesVotes;

        @SerializedName("nov")
        private int noVotes;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Relation {
        @SerializedName("n")
        private String name;

        @SerializedName("r")
        private String relation;

        @SerializedName("svf")
        private double surchargeValueFrom;

        @SerializedName("hef")
        private boolean hasEmbargoFrom;

        @SerializedName("svt")
        private double surchargeValueTo;

        @SerializedName("het")
        private boolean hasEmbargoTo;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PrimeBoost {

        @SerializedName("hba")
        private boolean hasBoostActive;

        @SerializedName("bp")
        private double boostPercent;

        @SerializedName("p")
        private int boostPoints;

        @SerializedName("b")
        private List<PrimeBooster> primeBoosters;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PrimeBooster {
        @SerializedName("u")
        private String username;

        @SerializedName("t")
        private String boostType;

        @SerializedName("v")
        private int boostValue;
    }
}