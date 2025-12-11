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
public class CountryData implements BaseData {

    @SerializedName("t")
    private Long timestamp;

    @SerializedName("s")
    private String server;

    @SerializedName("c")
    private List<Country> countries = new ArrayList<>();

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Country {
        @SerializedName("n")
        private String name;

        @SerializedName("d")
        private String description;

        @SerializedName("m")
        private String motd;

        @SerializedName("em")
        private String entryMsg;

        @SerializedName("a")
        private int age;

        @SerializedName("cm")
        private int countMembers;

        @SerializedName("cnm")
        private int countNewMembers;

        @SerializedName("fh")
        private FHomeCoord fhomeCoord;

        @SerializedName("ie")
        private boolean isEmpire;

        @SerializedName("ir")
        private boolean isReferent;

        @SerializedName("itw")
        private boolean isTopWarzone;

        @SerializedName("ira")
        private boolean isRestrictAssault;

        @SerializedName("irm")
        private boolean isRestrictMissile;

        @SerializedName("l")
        private int level;

        @SerializedName("p")
        private int progress;

        @SerializedName("np")
        private int notationsPosition;

        @SerializedName("t")
        private List<String> tags = new ArrayList<>();

        @SerializedName("di")
        private String discord;

        @SerializedName("an")
        private String allianceName;

        @SerializedName("mm")
        private int mmr;

        @SerializedName("c")
        private int claims;

        @SerializedName("po")
        private int power;

        @SerializedName("mp")
        private int maxPower;

        @SerializedName("pbf")
        private int powerboostFixed;

        @SerializedName("pbw")
        private int powerboostWarzone;

        @SerializedName("pbu")
        private int powerboostUnesco;

        @SerializedName("pbnm")
        private int powerboostNewMember;

        @SerializedName("pbma")
        private int powerboostMalusAssault;

        @SerializedName("f")
        private String flag;

        @SerializedName("le")
        private String leader;

        @SerializedName("of")
        private List<String> officers = new ArrayList<>();

        @SerializedName("me")
        private List<String> members = new ArrayList<>();

        @SerializedName("re")
        private List<String> recruits = new ArrayList<>();

        @SerializedName("nm")
        private List<NewMember> newMembers = new ArrayList<>();

        @SerializedName("rl")
        private Researches researchesLevel;

        @SerializedName("w")
        private List<String> wars = new ArrayList<>();

        @SerializedName("b")
        private Bank bank;

        @SerializedName("ac")
        private Actions actions;

        @SerializedName("s")
        private Settings settings;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FHomeCoord {
        @SerializedName("x")
        private int x;
        @SerializedName("z")
        private int z;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Bank {
        @SerializedName("b")
        private int balance;

        @SerializedName("o")
        private List<String> owners = new ArrayList<>();

        @SerializedName("m")
        private List<String> members = new ArrayList<>();

        @SerializedName("l")
        private List<BankLog> logs = new ArrayList<>();
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BankLog {
        @SerializedName("a")
        private double amount;

        @SerializedName("t")
        private long timestamp;

        @SerializedName("p")
        private String pseudo;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Actions {
        @SerializedName("td")
        private double totalDividendes;

        @SerializedName("aa")
        private int availableActions;

        @SerializedName("ap")
        private int actionPrice;

        @SerializedName("dc")
        private List<DividendeClassement> dividendesClassement = new ArrayList<>();

        @SerializedName("dh")
        private List<DividendeHistory> dividendesHistory = new ArrayList<>();

        @SerializedName("a")
        private List<Action> actions = new ArrayList<>();
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Action {
        @SerializedName("p")
        private String owner;

        @SerializedName("s")
        private ActionStatus status;
    }

    public enum ActionStatus {
        @SerializedName("locked")
        LOCKED,
        @SerializedName("unlocked")
        UNLOCKED
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DividendeClassement {
        @SerializedName("p")
        private String pays;

        @SerializedName("m")
        private double montant;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DividendeHistory {
        @SerializedName("p")
        private String pays;

        @SerializedName("m")
        private double montant;

        @SerializedName("t")
        private long timestamp;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Settings {
        @SerializedName("in")
        private boolean invitationNeeded;

        @SerializedName("ro")
        private boolean recruitmentOpen;

        @SerializedName("dkrwr")
        private boolean doKickRecruitWarReason;

        @SerializedName("dkmnp")
        private boolean doKickMemberNegativePower;

        @SerializedName("p")
        private List<Permission> permissions = new ArrayList<>();

        @SerializedName("l")
        private List<SettingLog> logs = new ArrayList<>();
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Permission {
        @SerializedName("n")
        private String name;

        @SerializedName("d")
        private String description;

        @SerializedName("l")
        private boolean leader;

        @SerializedName("o")
        private boolean officer;

        @SerializedName("m")
        private boolean member;

        @SerializedName("r")
        private boolean recruit;

        @SerializedName("al")
        private boolean ally;

        @SerializedName("ne")
        private boolean neutral;

        @SerializedName("e")
        private boolean enemy;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SettingLog {
        @SerializedName("u")
        private String user;

        @SerializedName("a")
        private String action;

        @SerializedName("t")
        private String target;

        @SerializedName("ts")
        private long timestamp;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NewMember {
        @SerializedName("p")
        private String pseudo;

        @SerializedName("pt")
        private int played_time;

        @SerializedName("rp")
        private int rewardPower;

        @SerializedName("rpm")
        private int rewardPowerMax;

        @SerializedName("rm")
        private int rewardMoney;

        @SerializedName("rmm")
        private int rewardMoneyMax;

        @SerializedName("s")
        private int step;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Researches {
        @SerializedName("g")
        private int general;

        @SerializedName("r")
        private int resource;

        @SerializedName("m")
        private int military;

        @SerializedName("i")
        private int industry;

        @SerializedName("t")
        private int technology;
    }
}