package fr.romaindu35.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WarData implements BaseData {

    @SerializedName("t")
    private Long timestamp;

    @SerializedName("s")
    private String server;

    @SerializedName("w")
    private List<War> wars = new ArrayList<>();

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class War {

        @SerializedName("i")
        private String warId; // Le hash SHA-1 de l'id de guerre
        @SerializedName("wd")
        private long warDeclaredAt; // Timestamp de la déclaration de guerre
        @SerializedName("ws")
        private long warStartAt; // Timestamp du début de la guerre (mise en ennemi effective)
        @SerializedName("ib")
        private String warsInitiatedBy;
        @SerializedName("r")
        private WarReason reason;
        @SerializedName("t")
        private String target;
        @SerializedName("tg")
        private Grade targetGrade;
        @SerializedName("st")
        private WarStatus status;
        @SerializedName("w")
        private String winner; // Le pays gagnant de la guerre (si terminée)
        @SerializedName("db")
        private int daysBeforeMissilePointReset;

        @SerializedName("a")
        private Participant attacker;
        @SerializedName("d")
        private Participant defender;
        @SerializedName("c")
        private Condition conditions;
        @SerializedName("ct")
        private ConditionType conditionType; // OR/AND
        @SerializedName("re")
        private Rewards rewards;

        @SerializedName("as")
        private List<Assault> assaults;
        @SerializedName("m")
        private List<Missile> missiles;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Participant {
        @SerializedName("c")
        private String country;
        @SerializedName("rm")
        private int remainingMissilesPoints;
        @SerializedName("i")
        private int inactivity;
        @SerializedName("p")
        private double progress; // Pourcentage de progression dans la guerre
        @SerializedName("co")
        private Condition condition;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Condition {
        @SerializedName("k")
        private int kill;
        @SerializedName("v")
        private int victory;
        @SerializedName("mp")
        private int missilePoints;
        @SerializedName("ap")
        private int assaultPoints;
        @SerializedName("am")
        private int antimatter;
        @SerializedName("rm")
        private int redmatter;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Rewards {
        @SerializedName("d")
        private int dollars;
        @SerializedName("p")
        private int power;
        @SerializedName("c")
        private int claims;
        @SerializedName("pe")
        private int peace;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Assault {
        @SerializedName("s")
        private long startAt;
        @SerializedName("w")
        private String winner;
        @SerializedName("l")
        private String looser;
        @SerializedName("mw")
        private int mmrWinner; // Représente le MMR gagné par le gagnant
        @SerializedName("ml")
        private int mmrLooser; // Représente le MMR perdu par le perdant
        @SerializedName("wh")
        private List<String> winnerHelpers;
        @SerializedName("lh")
        private List<String> looserHelpers;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Missile {
        @SerializedName("l")
        private long launchedAt;
        @SerializedName("m")
        private String missile;
        @SerializedName("mp")
        private int missilePoints;
        @SerializedName("la")
        private String launcher;
        @SerializedName("lc")
        private String launcherCountry;
    }

    public enum ConditionType {
        @SerializedName("o")
        OR,
        @SerializedName("a")
        AND
    }

    public enum WarStatus {
        @SerializedName("ip")
        IN_PROGRESS, // Guerre en cours
        @SerializedName("wv")
        WAITING_VALIDATION, // En attente de validation par le staff
        @SerializedName("wca")
        WAITING_CONDITIONS_ATT, // En attente des conditions de l'attaquant
        @SerializedName("wcd")
        WAITING_CONDITIONS_DEF, // En attente de validation des conditions de l'attaquant par le défenseur ou en attente des conditions du défenseur
        @SerializedName("wcav")
        WAITING_CONDITIONS_ATT_VALIDATION, // En attente de validation des conditions du défenseur par l'attaquant
        @SerializedName("f")
        FINISHED, // Guerre terminée
        @SerializedName("r")
        REFUSED // Guerre refusée par le staff
    }

    public enum Grade {
        @SerializedName("r")
        RECRUIT,
        @SerializedName("m")
        MEMBER,
        @SerializedName("o")
        OFFICER,
        @SerializedName("l")
        LEADER
    }

    public enum WarReason {
        @SerializedName("kw")
        KILL_WILDERNESS, // Kill wilderness
        @SerializedName("p")
        PROVOCATION, // Provocation
        @SerializedName("te")
        TERRITORIAL_EXPANSION, // Expansion territoriale
        @SerializedName("t")
        TREASON, // Trahison
        @SerializedName("i")
        INTRUSION, // Intrusion territoire
        @SerializedName("s")
        SCAM, // Arnaque
        @SerializedName("tk")
        TPKILL, // TP-kill
        @SerializedName("ci")
        COLONY_IMDEPENDENCE, // Indépendance de colonie
        @SerializedName("cr")
        COLONY_REFUSAL, // Refus de colonie
        @SerializedName("cp")
        COLONY_PROTECTION, // Protection de colonie
        @SerializedName("cs")
        COLONY_STEAL, // Vol de colonie
        @SerializedName("bc")
        BUFFER_COUNTRY, // Pays tampon
        @SerializedName("up")
        UNDER_POWER, // Pays en sous-power
        @SerializedName("r")
        REPRISAL, // Représaille assaut
        @SerializedName("fw")
        FOLLOW_WAR, // Suivi de guerre
        @SerializedName("m5")
        MMR500, // Pays >500 MMR
        @SerializedName("er")
        EMPIRE_RIVALITY, // Rivalité d'empire
        @SerializedName("wr")
        WAR_REVENGE, // Revanche de guerre
        @SerializedName("ba")
        BOMBING_ASSISTANCE, // Assistance au bombardement (prêt de T4 antimmater)
        @SerializedName("wd")
        WARZONE_DOMINATION, // Domination Warzone
        @SerializedName("pr")
        PROTECTORAT // Protectorat de pays
    }
}