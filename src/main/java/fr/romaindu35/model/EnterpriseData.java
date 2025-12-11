package fr.romaindu35.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseData implements BaseData {

    @SerializedName("t")
    private Long timestamp;

    @SerializedName("s")
    private String server;

    @SerializedName("e")
    private List<Enterprise> enterprises = new ArrayList<>();

    // --- ADAPTERS ---

    /**
     * Adaptateur conservé pour la compression d'historique (Complex logic).
     */
    public static class CompactHistoryAdapter implements JsonSerializer<Map<String, Double>>, JsonDeserializer<Map<String, Double>> {
        @Override
        public JsonElement serialize(Map<String, Double> src, Type typeOfSrc, JsonSerializationContext context) {
            if (src == null || src.isEmpty()) return new JsonObject();
            List<String> dates = new ArrayList<>(src.keySet());
            Collections.sort(dates);
            JsonObject obj = new JsonObject();
            obj.addProperty("l", dates.size());
            obj.addProperty("f", dates.get(0));
            JsonArray arr = new JsonArray();
            for (String d : dates) {
                if ((double) src.get(d).intValue() == src.get(d)) {
                    arr.add(new JsonPrimitive(src.get(d).intValue()));
                } else {
                    arr.add(new JsonPrimitive(src.get(d)));
                }
            }
            int newSize = arr.size();
            while (newSize > 0 && arr.get(newSize - 1).getAsDouble() == 0.0) {
                newSize--;
            }
            if (newSize > 0) {
                JsonArray trimmed = new JsonArray();
                for (int i = 0; i < newSize; i++) {
                    trimmed.add(arr.get(i));
                }
                obj.add("v", trimmed);
            }
            return obj;
        }

        @Override
        public Map<String, Double> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (!json.isJsonObject()) return new HashMap<>();
            JsonObject obj = json.getAsJsonObject();
            if (!obj.has("l") || !obj.has("f")) return new HashMap<>();
            int l = obj.get("l").getAsInt();
            String first = obj.get("f").getAsString();
            JsonArray arr = obj.has("v") ? obj.getAsJsonArray("v") : new JsonArray();
            Map<String, Double> map = new TreeMap<>();
            Calendar date = Calendar.getInstance();
            try {
                Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(first);
                date.setTime(parsedDate);
            } catch (ParseException e) {
                return new HashMap<>();
            }
            for (int i = 0; i < l; i++) {
                String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
                map.put(dateStr, i < arr.size() ? arr.get(i).getAsDouble() : 0.0);
                date.add(Calendar.DATE, 1);
            }
            return map;
        }
    }

    /**
     * Adaptateur spécifique pour EnterpriseFarm qui contient une Map de Map avec historique.
     */
    public static class FarmHistoryAdapter implements JsonSerializer<Map<String, Map<String, Double>>>, JsonDeserializer<Map<String, Map<String, Double>>> {
        private final CompactHistoryAdapter delegate = new CompactHistoryAdapter();

        @Override
        public JsonElement serialize(Map<String, Map<String, Double>> src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            for (Map.Entry<String, Map<String, Double>> entry : src.entrySet()) {
                obj.add(entry.getKey(), delegate.serialize(entry.getValue(), null, context));
            }
            return obj;
        }

        @Override
        public Map<String, Map<String, Double>> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Map<String, Map<String, Double>> result = new TreeMap<>();
            JsonObject obj = json.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                result.put(entry.getKey(), delegate.deserialize(entry.getValue(), null, context));
            }
            return result;
        }
    }

    // --- CLASSES INTERNES ---

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Enterprise {

        @SerializedName("n")
        private String name;

        @SerializedName("ty")
        private EnterpriseType type;

        @SerializedName("a")
        private int age;

        @SerializedName("d")
        private String description;

        @SerializedName("s")
        private String services;

        @SerializedName("o")
        private String owner;

        @SerializedName("c")
        private List<String> cadres = new ArrayList<>();

        @SerializedName("e")
        private List<String> employees = new ArrayList<>();

        @SerializedName("cd")
        private int contractsDone;

        @SerializedName("di")
        private int disputes;

        @SerializedName("cs")
        private Double contractsSuccess;

        @SerializedName("to")
        private int turnover;

        @SerializedName("p")
        private List<Permission> permissions = new ArrayList<>();

        @SerializedName("f")
        private String flag;

        @SerializedName("b")
        private EnterpriseBank bank;

        /**
         * Permet de cloner une Enterprise de base. Cela est utilisé afin de créer les sous-classes sans perdre les données communes.
         *
         * @param enterprise: L'Enterprise de base à cloner.
         */
        public Enterprise(Enterprise enterprise) {
            this.name = enterprise.name;
            this.type = enterprise.type;
            this.age = enterprise.age;
            this.description = enterprise.description;
            this.services = enterprise.services;
            this.owner = enterprise.owner;
            this.cadres = enterprise.cadres;
            this.employees = enterprise.employees;
            this.contractsDone = enterprise.contractsDone;
            this.disputes = enterprise.disputes;
            this.contractsSuccess = enterprise.contractsSuccess;
            this.turnover = enterprise.turnover;
            this.permissions = enterprise.permissions;
            this.flag = enterprise.flag;
            this.bank = enterprise.bank;
        }

        /**
         * Classe technique pour briser la récursion infinie de Gson.
         * Utilisée quand on veut désérialiser un "Enterprise" de base sans ré-invoquer le Deserializer.
         */
        private static class EnterpriseRaw extends Enterprise {
        }

        /**
         * Ce Deserializer est OBLIGATOIRE pour gérer le polymorphisme (instancier la bonne sous-classe selon "ty").
         * Il doit être enregistré manuellement dans le GsonBuilder si @JsonAdapter n'est pas utilisé sur la liste parente.
         */
        public static class Deserializer implements JsonDeserializer<Enterprise> {
            @Override
            public Enterprise deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                // Utilisation de EnterpriseRaw.class pour éviter la boucle infinie (StackOverflow)
                if (!obj.has("ty")) return context.deserialize(json, EnterpriseRaw.class);

                try {
                    EnterpriseType type = EnterpriseType.valueOf(obj.get("ty").getAsString());
                    switch (type) {
                        case BET:
                            return context.deserialize(json, EnterpriseBet.class);
                        case CASINO:
                            return context.deserialize(json, EnterpriseCasino.class);
                        case ELECTRIC:
                            return context.deserialize(json, EnterpriseElectric.class);
                        case FARM:
                            return context.deserialize(json, EnterpriseFarm.class);
                        case REALESTATE:
                            return context.deserialize(json, EnterpriseParcelle.class);
                        case PETROL:
                            return context.deserialize(json, EnterprisePetrol.class);
                        case TRADER:
                            return context.deserialize(json, EnterpriseTrader.class);
                        default:
                            return context.deserialize(json, EnterpriseRaw.class);
                    }
                } catch (IllegalArgumentException e) {
                    return context.deserialize(json, EnterpriseRaw.class);
                }
            }
        }
    }

    @Getter
    @Setter
    @ToString(callSuper = true)
    @NoArgsConstructor
    public static class EnterpriseBet extends Enterprise {
        @SerializedName("be")
        private List<Bet> bets = new ArrayList<>();
        @SerializedName("brr")
        private int betsRewardRedistributed;
        @SerializedName("bt")
        private int betTotal;
        @SerializedName("ba")
        private int betActive;

        @Getter
        @Setter
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Bet {
            @SerializedName("dt")
            private Long deadlineTime;
            @SerializedName("mb")
            private int minBet;
            @SerializedName("np")
            private int numberOfPlayers;
            @SerializedName("t")
            private String title;
            @SerializedName("o1")
            private String option1;
            @SerializedName("o2")
            private String option2;
            @SerializedName("s")
            private String status;
        }

        public EnterpriseBet(Enterprise enterprise, List<Bet> bets, int betsRewardRedistributed, int betTotal, int betActive) {
            super(enterprise);
            this.bets = bets;
            this.betsRewardRedistributed = betsRewardRedistributed;
            this.betTotal = betTotal;
            this.betActive = betActive;
        }
    }

    @Getter
    @Setter
    @ToString(callSuper = true)
    @NoArgsConstructor
    public static class EnterpriseCasino extends Enterprise {
        @SerializedName("h")
        @JsonAdapter(CompactHistoryAdapter.class)
        private Map<String, Double> history = new HashMap<>();

        @SerializedName("ba")
        private Double benefAverage;
        @SerializedName("tp")
        private Double totalPlay;
        @SerializedName("wp")
        private Double winPercent;
        @SerializedName("tw")
        private Double totalWin;

        public EnterpriseCasino(Enterprise enterprise, Map<String, Double> history, Double benefAverage, Double totalPlay, Double winPercent, Double totalWin) {
            super(enterprise);
            this.history = history;
            this.benefAverage = benefAverage;
            this.totalPlay = totalPlay;
            this.winPercent = winPercent;
            this.totalWin = totalWin;
        }
    }

    @Getter
    @Setter
    @ToString(callSuper = true)
    @NoArgsConstructor
    public static class EnterpriseElectric extends Enterprise {
        @SerializedName("t")
        private Double total;
        @SerializedName("av")
        private Double available;
        @SerializedName("ac")
        private boolean allowCountry;
        @SerializedName("aa")
        private boolean allowAlly;
        @SerializedName("al")
        private boolean allowAll;
        @SerializedName("ad")
        private String associatedCountry;
        @SerializedName("pr")
        private Double price;
        @SerializedName("pa")
        private Double priceAverage;

        @SerializedName("ct")
        private Map<String, Double> countriesSell = new HashMap<>();

        @SerializedName("hg")
        @JsonAdapter(CompactHistoryAdapter.class)
        private Map<String, Double> historyGenerated = new HashMap<>();

        @SerializedName("hc")
        @JsonAdapter(CompactHistoryAdapter.class)
        private Map<String, Double> historyCollected = new HashMap<>();

        public EnterpriseElectric(Enterprise other, Double total, Double available, boolean allowCountry, boolean allowAlly, boolean allowAll, String associatedCountry, Double price, Double priceAverage, Map<String, Double> countriesSell, Map<String, Double> historyGenerated, Map<String, Double> historyCollected) {
            super(other);
            this.total = total;
            this.available = available;
            this.allowCountry = allowCountry;
            this.allowAlly = allowAlly;
            this.allowAll = allowAll;
            this.associatedCountry = associatedCountry;
            this.price = price;
            this.priceAverage = priceAverage;
            this.countriesSell = countriesSell;
            this.historyGenerated = historyGenerated;
            this.historyCollected = historyCollected;
        }
    }

    @Getter
    @Setter
    @ToString(callSuper = true)
    @NoArgsConstructor
    public static class EnterpriseFarm extends Enterprise {
        @SerializedName("h")
        @JsonAdapter(FarmHistoryAdapter.class)
        private Map<String, Map<String, Double>> histories = new TreeMap<>();

        @SerializedName("tc")
        private int totalCollected;
        @SerializedName("td")
        private int todayCollected;
        @SerializedName("cc")
        private Map<String, Integer> collectedCereal = new HashMap<>();

        public EnterpriseFarm(Enterprise enterprise, Map<String, Map<String, Double>> histories, int totalCollected, int todayCollected, Map<String, Integer> collectedCereal) {
            super(enterprise);
            this.histories = histories;
            this.totalCollected = totalCollected;
            this.todayCollected = todayCollected;
            this.collectedCereal = collectedCereal;
        }
    }

    @Getter
    @Setter
    @ToString(callSuper = true)
    @NoArgsConstructor
    public static class EnterpriseParcelle extends Enterprise {
        @SerializedName("pa")
        private List<Parcelle> parcelles = new ArrayList<>();

        @Getter
        @Setter
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Parcelle {
            @SerializedName("n")
            private String name;
            @SerializedName("c")
            private String clientName;
            @SerializedName("s")
            private boolean isStaff;
            @SerializedName("p")
            private int price;
            @SerializedName("sc")
            private Coords signCoords;
            @SerializedName("st")
            private String status;
            @SerializedName("dr")
            private Integer daysRent; // Kept as Integer because nullable in original logic
        }

        @Getter
        @Setter
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Coords {
            @SerializedName("x")
            private int x;
            @SerializedName("y")
            private int y;
            @SerializedName("z")
            private int z;
        }

        public EnterpriseParcelle(Enterprise enterprise, List<Parcelle> parcelles) {
            super(enterprise);
            this.parcelles = parcelles;
        }
    }

    @Getter
    @Setter
    @ToString(callSuper = true)
    @NoArgsConstructor
    public static class EnterprisePetrol extends Enterprise {
        @SerializedName("hg")
        @JsonAdapter(CompactHistoryAdapter.class)
        private Map<String, Double> historyGenerated = new HashMap<>();

        @SerializedName("hc")
        @JsonAdapter(CompactHistoryAdapter.class)
        private Map<String, Double> historyCollected = new HashMap<>();

        @SerializedName("t")
        private int total;
        @SerializedName("av")
        private int available;
        @SerializedName("ac")
        private boolean allowCountry;
        @SerializedName("aa")
        private boolean allowAlly;
        @SerializedName("al")
        private boolean allowAll;
        @SerializedName("ad")
        private String associatedCountry;
        @SerializedName("pr")
        private int price;
        @SerializedName("pa")
        private int priceAverage;

        public EnterprisePetrol(Enterprise enterprise, Map<String, Double> historyGenerated, Map<String, Double> historyCollected, int total, int available, boolean allowCountry, boolean allowAlly, boolean allowAll, String associatedCountry, int price, int priceAverage) {
            super(enterprise);
            this.historyGenerated = historyGenerated;
            this.historyCollected = historyCollected;
            this.total = total;
            this.available = available;
            this.allowCountry = allowCountry;
            this.allowAlly = allowAlly;
            this.allowAll = allowAll;
            this.associatedCountry = associatedCountry;
            this.price = price;
            this.priceAverage = priceAverage;
        }
    }

    @Getter
    @Setter
    @ToString(callSuper = true)
    @NoArgsConstructor
    public static class EnterpriseTrader extends Enterprise {
        @SerializedName("h")
        @JsonAdapter(CompactHistoryAdapter.class)
        private Map<String, Double> history = new HashMap<>();

        @SerializedName("tg")
        private int totalGenerated;
        @SerializedName("tp")
        private int todayPercent;
        @SerializedName("si")
        private Float sumInvestment;
        @SerializedName("ti")
        private int totalInvestors;

        public EnterpriseTrader(Enterprise enterprise, Map<String, Double> history, int totalGenerated, int todayPercent, Float sumInvestment, int totalInvestors) {
            super(enterprise);
            this.history = history;
            this.totalGenerated = totalGenerated;
            this.todayPercent = todayPercent;
            this.sumInvestment = sumInvestment;
            this.totalInvestors = totalInvestors;
        }
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Permission {
        @SerializedName("n")
        private String name;
        @SerializedName("o")
        private boolean owner;
        @SerializedName("c")
        private boolean cadre;
        @SerializedName("e")
        private boolean employee;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EnterpriseBank {
        @SerializedName("b")
        private Double bank;
        @SerializedName("f")
        private Double flux14Days;
        @SerializedName("i")
        private Double inProgressContracts;
        @SerializedName("s")
        private Double salaries14Days;
        @SerializedName("t")
        private Double taxes14Days;
        @SerializedName("l")
        private List<Log> logs = new ArrayList<>();

        @Getter
        @Setter
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Log {
            @SerializedName("n")
            private String name;
            @SerializedName("a")
            private Double amount;
            @SerializedName("t")
            private Long timestamp;
        }
    }

    public enum EnterpriseType {
        BUILD, ENGINEER, TERRAFORM, JOURNALIST, CASINO, PVP, LOAN, REALESTATE, TRADER, BET, REPAIR, LAWYER, ELECTRIC, PETROL, FARM
    }
}
