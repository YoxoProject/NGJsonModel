package fr.romaindu35.model;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

public class EnterpriseData {

    private Long timestamp;
    private String server;
    private List<Enterprise> enterprises;

    public EnterpriseData(Long timestamp, String server, List<Enterprise> enterprises) {
        this.timestamp = timestamp;
        this.server = server;
        this.enterprises = enterprises;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getServer() {
        return server;
    }

    public List<Enterprise> getEnterprises() {
        return enterprises;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setEnterprises(List<Enterprise> enterprises) {
        this.enterprises = enterprises;
    }

    public static class Serializer implements JsonSerializer<EnterpriseData> {

        @Override
        public JsonElement serialize(EnterpriseData src, Type type, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            obj.addProperty("t", src.timestamp);
            obj.addProperty("s", src.server);
            if (!src.enterprises.isEmpty()) {
                obj.add("e", context.serialize(src.enterprises, new TypeToken<List<Enterprise>>() {}.getType()));
            }
            return obj;
        }
    }

    public static class Deserializer implements JsonDeserializer<EnterpriseData> {

        @Override
        public EnterpriseData deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject obj = json.getAsJsonObject();
            Long timestamp = obj.get("t").getAsLong();
            String server = obj.get("s").getAsString();
            List<Enterprise> enterprises = obj.has("e") ? context.<List<Enterprise>>deserialize(obj.get("e"), new TypeToken<List<Enterprise>>() {}.getType()) : new ArrayList<Enterprise>();
            return new EnterpriseData(timestamp, server, enterprises);
        }
    }

    /**
     * Permet de compacter les historique pour alléger le JSON.
     * Il utilise un format compact où :
     * - "l" est la longueur de l'historique (nombre de dates)
     * - "f" est la première date de l'historique (au format "yyyy-MM-dd")
     * - "v" est un tableau des valeurs associées à chaque date, dans l'ordre chronologique.
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
            for (String d : dates) arr.add(src.get(d));
            obj.add("v", arr);
            return obj;
        }

        @Override
        public Map<String, Double> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (!json.isJsonObject()) return new HashMap<>();
            JsonObject obj = json.getAsJsonObject();
            if (!obj.has("l") || !obj.has("f") || !obj.has("v")) return new HashMap<>();
            int l = obj.get("l").getAsInt();
            String first = obj.get("f").getAsString();
            JsonArray arr = obj.getAsJsonArray("v");
            Map<String, Double> map = new TreeMap<>();
            Calendar date = Calendar.getInstance();
            try {
                Date parsedDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(first);
                date.setTime(parsedDate);
            } catch (java.text.ParseException e) {
                return new HashMap<>();
            }
            for (int i = 0; i < l && i < arr.size(); i++) {
                String dateStr = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
                map.put(dateStr, arr.get(i).getAsDouble());
                date.add(Calendar.DATE, 1);
            }
            return map;
        }
    }

    public static class Enterprise {

        private String name;
        private EnterpriseType type;
        private Integer age;
        private String description;
        private String services;
        private String owner;
        private List<String> cadres;
        private List<String> employees;

        private Integer contractsDone;
        private Integer disputes;
        private Double contractsSuccess;
        private Integer turnover;

        private List<Permission> permissions;
        private String flag;

        private EnterpriseBank bank;

        public Enterprise(String name, EnterpriseType type, Integer age, String description, String services, String owner, List<String> cadres, List<String> employees, Integer contractsDone, Integer disputes, Double contractsSuccess, Integer turnover, List<Permission> permissions, String flag, EnterpriseBank bank) {
            this.name = name;
            this.type = type;
            this.age = age;
            this.description = description;
            this.services = services;
            this.owner = owner;
            this.cadres = cadres;
            this.employees = employees;
            this.contractsDone = contractsDone;
            this.disputes = disputes;
            this.contractsSuccess = contractsSuccess;
            this.turnover = turnover;
            this.permissions = permissions;
            this.flag = flag;
            this.bank = bank;
        }

        public Enterprise(Enterprise enterprise) {
            this(enterprise.getName(),
                    enterprise.getType(),
                    enterprise.getAge(),
                    enterprise.getDescription(),
                    enterprise.getServices(),
                    enterprise.getOwner(),
                    new ArrayList<>(enterprise.getCadres()),
                    new ArrayList<>(enterprise.getEmployees()),
                    enterprise.getContractsDone(),
                    enterprise.getDisputes(),
                    enterprise.getContractsSuccess(),
                    enterprise.getTurnover(),
                    new ArrayList<>(enterprise.getPermissions()),
                    enterprise.getFlag(),
                    enterprise.getBank());
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public EnterpriseType getType() {
            return type;
        }

        public void setType(EnterpriseType type) {
            this.type = type;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getServices() {
            return services;
        }

        public void setServices(String services) {
            this.services = services;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public List<String> getCadres() {
            return cadres;
        }

        public void setCadres(List<String> cadres) {
            this.cadres = cadres;
        }

        public List<String> getEmployees() {
            return employees;
        }

        public void setEmployees(List<String> employees) {
            this.employees = employees;
        }

        public Integer getContractsDone() {
            return contractsDone;
        }

        public void setContractsDone(Integer contractsDone) {
            this.contractsDone = contractsDone;
        }

        public Integer getDisputes() {
            return disputes;
        }

        public void setDisputes(Integer disputes) {
            this.disputes = disputes;
        }

        public Double getContractsSuccess() {
            return contractsSuccess;
        }

        public void setContractsSuccess(Double contractsSuccess) {
            this.contractsSuccess = contractsSuccess;
        }

        public Integer getTurnover() {
            return turnover;
        }

        public void setTurnover(Integer turnover) {
            this.turnover = turnover;
        }

        public List<Permission> getPermissions() {
            return permissions;
        }

        public void setPermissions(List<Permission> permissions) {
            this.permissions = permissions;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public EnterpriseBank getBank() {
            return bank;
        }

        public void setBank(EnterpriseBank bank) {
            this.bank = bank;
        }

        public static class Serializer implements JsonSerializer<Enterprise> {

            @Override
            public JsonElement serialize(Enterprise src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                obj.addProperty("n", src.name);
                obj.addProperty("ty", src.type.name());
                obj.addProperty("a", src.age);
                if (!src.description.isEmpty()) obj.addProperty("d", src.description);
                if (!src.services.isEmpty()) obj.addProperty("s", src.services);
                obj.addProperty("o", src.owner);
                if (!src.cadres.isEmpty()) obj.add("c", context.serialize(src.cadres));
                if (!src.employees.isEmpty()) obj.add("e", context.serialize(src.employees));

                if (src.contractsDone != 0) obj.addProperty("cd", src.contractsDone);
                if (src.disputes != 0) obj.addProperty("di", src.disputes);
                if (src.contractsSuccess != 0) obj.addProperty("cs", src.contractsSuccess);
                if (src.turnover != 0) obj.addProperty("to", src.turnover);

                if (!src.permissions.isEmpty()) obj.add("p", context.serialize(src.permissions, new TypeToken<List<Permission>>() {}.getType()));
                if (!src.flag.isEmpty()) obj.addProperty("f", src.flag);
                obj.add("b", context.serialize(src.bank, EnterpriseBank.class));


                if (src instanceof EnterpriseBet) {
                    EnterpriseBet src_bet = (EnterpriseBet) src;
                    if (!src_bet.bets.isEmpty()) obj.add("be", context.serialize(src_bet.bets, new TypeToken<List<EnterpriseBet.Bet>>() {}.getType()));
                    if (src_bet.betsRewardRedistributed != 0) obj.addProperty("brr", src_bet.betsRewardRedistributed);
                    if (src_bet.betTotal != 0) obj.addProperty("bt", src_bet.betTotal);
                    if (src_bet.betActive != 0) obj.addProperty("ba", src_bet.betActive);
                }
                if (src instanceof EnterpriseCasino) {
                    EnterpriseCasino src_casino = (EnterpriseCasino) src;
                    obj.add("h", new CompactHistoryAdapter().serialize(src_casino.history, null, context));
                    if (src_casino.benefAverage != 0) obj.addProperty("ba", src_casino.benefAverage);
                    if (src_casino.totalPlay != 0) obj.addProperty("tp", src_casino.totalPlay);
                    if (src_casino.winPercent != 0) obj.addProperty("wp", src_casino.winPercent);
                    if (src_casino.totalWin != 0) obj.addProperty("tw", src_casino.totalWin);
                }
                if (src instanceof EnterpriseElectric) {
                    EnterpriseElectric src_electric = (EnterpriseElectric) src;
                    if (src_electric.total != 0) obj.addProperty("t", src_electric.total);
                    if (src_electric.available != 0) obj.addProperty("av", src_electric.available);
                    if (src_electric.allowCountry) obj.addProperty("ac", true); // Le false, on ne le met pas pour alléger le JSON
                    if (src_electric.allowAlly) obj.addProperty("aa", true);
                    if (src_electric.allowAll) obj.addProperty("al", true);
                    if (!src_electric.associatedCountry.isEmpty()) obj.addProperty("ad", src_electric.associatedCountry);
                    if (src_electric.price != 0) obj.addProperty("pr", src_electric.price);
                    if (src_electric.priceAverage != 0) obj.addProperty("pa", src_electric.priceAverage);
                    if (!src_electric.countriesSell.isEmpty()) obj.add("ct", context.serialize(src_electric.countriesSell));
                    obj.add("hg", new CompactHistoryAdapter().serialize(src_electric.historyGenerated, null, context));
                    obj.add("hc", new CompactHistoryAdapter().serialize(src_electric.historyCollected, null, context));
                }
                if (src instanceof EnterpriseFarm) {
                    EnterpriseFarm src_farm = (EnterpriseFarm) src;
                    JsonObject historiesObj = new JsonObject();
                    for (Map.Entry<String, Map<String, Double>> entry : src_farm.histories.entrySet()) {
                        historiesObj.add(entry.getKey(), new CompactHistoryAdapter().serialize(entry.getValue(), null, context));
                    }
                    obj.add("h", historiesObj);
                    if (src_farm.totalCollected != 0) obj.addProperty("tc", src_farm.totalCollected);
                    if (src_farm.todayCollected != 0) obj.addProperty("td", src_farm.todayCollected);
                    if (!src_farm.collectedCereal.isEmpty()) obj.add("cc", context.serialize(src_farm.collectedCereal));
                }
                if (src instanceof EnterpriseParcelle) {
                    EnterpriseParcelle src_parcelle = (EnterpriseParcelle) src;
                    if (!src_parcelle.parcelles.isEmpty()) obj.add("pa", context.serialize(src_parcelle.parcelles, new TypeToken<List<EnterpriseParcelle.Parcelle>>() {}.getType()));
                }
                if (src instanceof EnterprisePetrol) {
                    EnterprisePetrol src_petrol = (EnterprisePetrol) src;
                    obj.add("hg", new CompactHistoryAdapter().serialize(src_petrol.historyGenerated, null, context));
                    obj.add("hc", new CompactHistoryAdapter().serialize(src_petrol.historyCollected, null, context));
                    if (src_petrol.total != 0) obj.addProperty("t", src_petrol.total);
                    if (src_petrol.available != 0) obj.addProperty("av", src_petrol.available);
                    if (src_petrol.allowCountry) obj.addProperty("ac", true); // Le false, on ne le met pas pour alléger le JSON
                    if (src_petrol.allowAlly) obj.addProperty("aa", true);
                    if (src_petrol.allowAll) obj.addProperty("al", true);
                    if (!src_petrol.associatedCountry.isEmpty()) obj.addProperty("ad", src_petrol.associatedCountry);
                    if (src_petrol.price != 0) obj.addProperty("pr", src_petrol.price);
                    if (src_petrol.priceAverage != 0) obj.addProperty("pa", src_petrol.priceAverage);
                }
                if (src instanceof EnterpriseTrader) {
                    EnterpriseTrader src_trader = (EnterpriseTrader) src;
                    obj.add("h", new CompactHistoryAdapter().serialize(src_trader.history, null, context));
                    if (src_trader.totalGenerated != 0) obj.addProperty("tg", src_trader.totalGenerated);
                    if (src_trader.todayPercent != 0) obj.addProperty("tp", src_trader.todayPercent);
                    if (src_trader.sumInvestment != 0) obj.addProperty("si", src_trader.sumInvestment);
                    if (src_trader.totalInvestors != 0) obj.addProperty("ti", src_trader.totalInvestors);
                }
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<Enterprise> {

            @Override
            public Enterprise deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = jsonElement.getAsJsonObject();

                String name = obj.get("n").getAsString();
                EnterpriseType entType = EnterpriseType.valueOf(obj.get("ty").getAsString());
                Integer age = obj.get("a").getAsInt();
                String description = obj.has("d") ? obj.get("d").getAsString() : "";
                String services = obj.has("s") ? obj.get("s").getAsString() : "";
                String owner = obj.get("o").getAsString();
                List<String> cadres = (List<String>) (obj.has("c") ? context.deserialize(obj.get("c"), new TypeToken<List<String>>(){}.getType()) : new ArrayList<>());
                List<String> employees = (List<String>) (obj.has("e") ? context.deserialize(obj.get("e"), new TypeToken<List<String>>(){}.getType()) : new ArrayList<>());

                Integer contractsDone = obj.has("cd") ? obj.get("cd").getAsInt() : 0;
                Integer disputes = obj.has("di") ? obj.get("di").getAsInt() : 0;
                Double contractsSuccess = obj.has("cs") ? obj.get("cs").getAsDouble() : 0.0;
                Integer turnover = obj.has("to") ? obj.get("to").getAsInt() : 0;

                List<Permission> permissions = (List<Permission>) (obj.has("p") ? context.deserialize(obj.get("p"), new TypeToken<List<Permission>>(){}.getType()) : new ArrayList<>());
                String flag = obj.has("f") ? obj.get("f").getAsString() : "";
                EnterpriseBank bank = context.deserialize(obj.get("b"), EnterpriseBank.class);

                Enterprise base = new Enterprise(name, entType, age, description, services, owner, cadres, employees, contractsDone, disputes, contractsSuccess, turnover, permissions, flag, bank);

                // Sous-types
                switch (entType) {
                    case BET: {
                        List<EnterpriseBet.Bet> bets = (List<EnterpriseBet.Bet>) (obj.has("be") ? context.deserialize(obj.get("be"), new TypeToken<List<EnterpriseBet.Bet>>(){}.getType()) : new ArrayList<>());
                        Integer brr = obj.has("brr") ? obj.get("brr").getAsInt() : 0;
                        Integer bt = obj.has("bt") ? obj.get("bt").getAsInt() : 0;
                        Integer ba = obj.has("ba") ? obj.get("ba").getAsInt() : 0;
                        return new EnterpriseBet(base, bets, brr, bt, ba);
                    }
                    case CASINO: {
                        Map<String, Double> history = new CompactHistoryAdapter().deserialize(obj.get("h"), null, context);
                        Double benefAverage = obj.has("ba") ? obj.get("ba").getAsDouble() : 0.0;
                        Double totalPlay = obj.has("tp") ? obj.get("tp").getAsDouble() : 0.0;
                        Double winPercent = obj.has("wp") ? obj.get("wp").getAsDouble() : 0.0;
                        Double totalWin = obj.has("tw") ? obj.get("tw").getAsDouble() : 0.0;
                        return new EnterpriseCasino(base, history, benefAverage, totalPlay, winPercent, totalWin);
                    }
                    case ELECTRIC: {
                        Double total = obj.has("t") ? obj.get("t").getAsDouble() : 0.0;
                        Double available = obj.has("av") ? obj.get("av").getAsDouble() : 0.0;
                        boolean allowCountry = obj.has("ac") && obj.get("ac").getAsBoolean();
                        boolean allowAlly = obj.has("aa") && obj.get("aa").getAsBoolean();
                        boolean allowAll = obj.has("al") && obj.get("al").getAsBoolean();
                        String associatedCountry = obj.has("ad") ? obj.get("ad").getAsString() : "";
                        Double price = obj.has("pr") ? obj.get("pr").getAsDouble() : 0.0;
                        Double priceAverage = obj.has("pa") ? obj.get("pa").getAsDouble() : 0.0;
                        Map<String, Double> countriesSell = obj.has("ct") ? (Map<String, Double>) context.deserialize(obj.get("ct"), new TypeToken<Map<String, Double>>() {}.getType()) : new HashMap<String, Double>();
                        Map<String, Double> historyGenerated = new CompactHistoryAdapter().deserialize(obj.get("hg"), null, context);
                        Map<String, Double> historyCollected = new CompactHistoryAdapter().deserialize(obj.get("hc"), null, context);
                        return new EnterpriseElectric(base, total, available, allowCountry, allowAlly, allowAll, associatedCountry, price, priceAverage, countriesSell, historyGenerated, historyCollected);
                    }
                    case FARM: {
                        Map<String, Map<String, Double>> histories = new TreeMap<>();
                        JsonObject historiesObj = obj.getAsJsonObject("h");
                        for (Map.Entry<String, JsonElement> entry : historiesObj.entrySet()) {
                            String cerealType = entry.getKey();
                            Map<String, Double> history = new CompactHistoryAdapter().deserialize(entry.getValue(), null, context);
                            histories.put(cerealType, history);
                        }
                        Integer totalCollected = obj.has("tc") ? obj.get("tc").getAsInt() : 0;
                        Integer todayCollected = obj.has("td") ? obj.get("td").getAsInt() : 0;
                        Map<String, Integer> collectedCereal = obj.has("cc") ? (Map<String, Integer>) context.deserialize(obj.get("cc"), new TypeToken<Map<String, Integer>>() {}.getType()) : new HashMap<String, Integer>();
                        return new EnterpriseFarm(base, histories, totalCollected, todayCollected, collectedCereal);
                    }
                    case REALESTATE: {
                        List<EnterpriseParcelle.Parcelle> parcelles = (List<EnterpriseParcelle.Parcelle>) (obj.has("pa") ? context.deserialize(obj.get("pa"), new TypeToken<List<EnterpriseParcelle.Parcelle>>(){}.getType()) : new ArrayList<>());
                        return new EnterpriseParcelle(base, parcelles);
                    }
                    case PETROL: {
                        Map<String, Double> historyGenerated = new CompactHistoryAdapter().deserialize(obj.get("hg"), null, context);
                        Map<String, Double> historyCollected = new CompactHistoryAdapter().deserialize(obj.get("hc"), null, context);
                        Integer total = obj.has("t") ? obj.get("t").getAsInt() : 0;
                        Integer available = obj.has("av") ? obj.get("av").getAsInt() : 0;
                        boolean allowCountry = obj.has("ac") && obj.get("ac").getAsBoolean();
                        boolean allowAlly = obj.has("aa") && obj.get("aa").getAsBoolean();
                        boolean allowAll = obj.has("al") && obj.get("al").getAsBoolean();
                        String associatedCountry = obj.has("ad") ? obj.get("ad").getAsString() : "";
                        Integer price = obj.has("pr") ? obj.get("pr").getAsInt() : 0;
                        Integer priceAverage = obj.has("pa") ? obj.get("pa").getAsInt() : 0;
                        return new EnterprisePetrol(base, historyGenerated, historyCollected, total, available, allowCountry, allowAlly, allowAll, associatedCountry, price, priceAverage);
                    }
                    case TRADER: {
                        Map<String, Double> history = new CompactHistoryAdapter().deserialize(obj.get("h"), null, context);
                        Integer totalGenerated = obj.has("tg") ? obj.get("tg").getAsInt() : 0;
                        Integer todayPercent = obj.has("tp") ? obj.get("tp").getAsInt() : 0;
                        Float sumInvestment = obj.has("si") ? obj.get("si").getAsFloat() : 0f;
                        Integer totalInvestors = obj.has("ti") ? obj.get("ti").getAsInt() : 0;
                        return new EnterpriseTrader(base, history, totalGenerated, todayPercent, sumInvestment, totalInvestors);
                    }
                    default:
                        return base;
                }
            }
        }
    }

    public static class Permission {

        private String name;
        private boolean owner;
        private boolean cadre;
        private boolean employee;

        public Permission(String name, boolean owner, boolean cadre, boolean employee) {
            this.name = name;
            this.owner = owner;
            this.cadre = cadre;
            this.employee = employee;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isOwner() {
            return owner;
        }

        public void setOwner(boolean owner) {
            this.owner = owner;
        }

        public boolean isCadre() {
            return cadre;
        }

        public void setCadre(boolean cadre) {
            this.cadre = cadre;
        }

        public boolean isEmployee() {
            return employee;
        }

        public void setEmployee(boolean employee) {
            this.employee = employee;
        }

        public static class Serializer implements JsonSerializer<Permission> {
            @Override
            public JsonElement serialize(Permission src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                obj.addProperty("n", src.name);
                if (src.owner) obj.addProperty("o", true);
                if (src.cadre) obj.addProperty("c", true);
                if (src.employee) obj.addProperty("e", true);
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<Permission> {
            @Override
            public Permission deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                String name = obj.get("n").getAsString();
                boolean owner = obj.has("o") && obj.get("o").getAsBoolean(); // Normalement toujours true, mais je préfère le vérifier
                boolean cadre = obj.has("c") && obj.get("c").getAsBoolean();
                boolean employee = obj.has("e") && obj.get("e").getAsBoolean();
                return new Permission(name, owner, cadre, employee);
            }
        }
    }

    public static class EnterpriseBet extends Enterprise {

        private List<Bet> bets;
        private Integer betsRewardRedistributed;
        private Integer betTotal;
        private Integer betActive;

        public EnterpriseBet(Enterprise enterprise, List<Bet> bets, Integer betsRewardRedistributed, Integer betTotal, Integer betActive) {
            super(enterprise);
            this.bets = bets;
            this.betsRewardRedistributed = betsRewardRedistributed;
            this.betTotal = betTotal;
            this.betActive = betActive;
        }

        public List<Bet> getBets() {
            return bets;
        }

        public void setBets(List<Bet> bets) {
            this.bets = bets;
        }

        public Integer getBetsRewardRedistributed() {
            return betsRewardRedistributed;
        }

        public void setBetsRewardRedistributed(Integer betsRewardRedistributed) {
            this.betsRewardRedistributed = betsRewardRedistributed;
        }

        public Integer getBetTotal() {
            return betTotal;
        }

        public void setBetTotal(Integer betTotal) {
            this.betTotal = betTotal;
        }

        public Integer getBetActive() {
            return betActive;
        }

        public void setBetActive(Integer betActive) {
            this.betActive = betActive;
        }

        public static class Bet {
            private Long deadlineTime;
            private Integer minBet;
            private Integer numberOfPlayers;
            private String title;
            private String option1;
            private String option2;
            private String status;

            public Bet(Long deadlineTime, Integer minBet, Integer numberOfPlayers, String title, String option1, String option2, String status) {
                this.deadlineTime = deadlineTime;
                this.minBet = minBet;
                this.numberOfPlayers = numberOfPlayers;
                this.title = title;
                this.option1 = option1;
                this.option2 = option2;
                this.status = status;
            }

            public Long getDeadlineTime() {
                return deadlineTime;
            }

            public void setDeadlineTime(Long deadlineTime) {
                this.deadlineTime = deadlineTime;
            }

            public Integer getMinBet() {
                return minBet;
            }

            public void setMinBet(Integer minBet) {
                this.minBet = minBet;
            }

            public Integer getNumberOfPlayers() {
                return numberOfPlayers;
            }

            public void setNumberOfPlayers(Integer numberOfPlayers) {
                this.numberOfPlayers = numberOfPlayers;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getOption1() {
                return option1;
            }

            public void setOption1(String option1) {
                this.option1 = option1;
            }

            public String getOption2() {
                return option2;
            }

            public void setOption2(String option2) {
                this.option2 = option2;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public static class Serializer implements JsonSerializer<Bet> {
                @Override
                public JsonElement serialize(Bet src, Type type, JsonSerializationContext context) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("dt", src.deadlineTime);
                    obj.addProperty("mb", src.minBet);
                    obj.addProperty("np", src.numberOfPlayers);
                    obj.addProperty("t", src.title);
                    obj.addProperty("o1", src.option1);
                    obj.addProperty("o2", src.option2);
                    obj.addProperty("s", src.status);
                    return obj;
                }
            }

            public static class Deserializer implements JsonDeserializer<Bet> {
                @Override
                public Bet deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                    JsonObject obj = json.getAsJsonObject();
                    Long deadlineTime = obj.get("dt").getAsLong();
                    Integer minBet = obj.get("mb").getAsInt();
                    Integer numberOfPlayers = obj.get("np").getAsInt();
                    String title = obj.get("t").getAsString();
                    String option1 = obj.get("o1").getAsString();
                    String option2 = obj.get("o2").getAsString();
                    String status = obj.get("s").getAsString();
                    return new Bet(deadlineTime, minBet, numberOfPlayers, title, option1, option2, status);
                }
            }
        }
    }

    public static class EnterpriseCasino extends Enterprise {

        private Map<String, Double> history;
        private Double benefAverage;
        private Double totalPlay;
        private Double winPercent;
        private Double totalWin;

        public EnterpriseCasino(Enterprise enterprise, Map<String, Double> history, Double benefAverage, Double totalPlay, Double winPercent, Double totalWin) {
            super(enterprise);
            this.history = history;
            this.benefAverage = benefAverage;
            this.totalPlay = totalPlay;
            this.winPercent = winPercent;
            this.totalWin = totalWin;
        }

        public Map<String, Double> getHistory() {
            return history;
        }

        public void setHistory(Map<String, Double> history) {
            this.history = history;
        }

        public Double getBenefAverage() {
            return benefAverage;
        }

        public void setBenefAverage(Double benefAverage) {
            this.benefAverage = benefAverage;
        }

        public Double getTotalPlay() {
            return totalPlay;
        }

        public void setTotalPlay(Double totalPlay) {
            this.totalPlay = totalPlay;
        }

        public Double getWinPercent() {
            return winPercent;
        }

        public void setWinPercent(Double winPercent) {
            this.winPercent = winPercent;
        }

        public Double getTotalWin() {
            return totalWin;
        }

        public void setTotalWin(Double totalWin) {
            this.totalWin = totalWin;
        }
    }

    public static class EnterpriseElectric extends Enterprise {

        private Double total;
        private Double available;
        private boolean allowCountry;
        private boolean allowAlly;
        private boolean allowAll;
        private String associatedCountry;
        private Double price;
        private Double priceAverage;
        private Map<String, Double> countriesSell;
        private Map<String, Double> historyGenerated;
        private Map<String, Double> historyCollected;

        public EnterpriseElectric(Enterprise enterprise, Double total, Double available, boolean allowCountry, boolean allowAlly, boolean allowAll, String associatedCountry, Double price, Double priceAverage, Map<String, Double> countriesSell, Map<String, Double> historyGenerated, Map<String, Double> historyCollected) {
            super(enterprise);
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

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public Double getAvailable() {
            return available;
        }

        public void setAvailable(Double available) {
            this.available = available;
        }

        public boolean isAllowCountry() {
            return allowCountry;
        }

        public void setAllowCountry(boolean allowCountry) {
            this.allowCountry = allowCountry;
        }

        public boolean isAllowAlly() {
            return allowAlly;
        }

        public void setAllowAlly(boolean allowAlly) {
            this.allowAlly = allowAlly;
        }

        public boolean isAllowAll() {
            return allowAll;
        }

        public void setAllowAll(boolean allowAll) {
            this.allowAll = allowAll;
        }

        public String getAssociatedCountry() {
            return associatedCountry;
        }

        public void setAssociatedCountry(String associatedCountry) {
            this.associatedCountry = associatedCountry;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getPriceAverage() {
            return priceAverage;
        }

        public void setPriceAverage(Double priceAverage) {
            this.priceAverage = priceAverage;
        }

        public Map<String, Double> getCountriesSell() {
            return countriesSell;
        }

        public void setCountriesSell(Map<String, Double> countriesSell) {
            this.countriesSell = countriesSell;
        }

        public Map<String, Double> getHistoryGenerated() {
            return historyGenerated;
        }

        public void setHistoryGenerated(Map<String, Double> historyGenerated) {
            this.historyGenerated = historyGenerated;
        }

        public Map<String, Double> getHistoryCollected() {
            return historyCollected;
        }

        public void setHistoryCollected(Map<String, Double> historyCollected) {
            this.historyCollected = historyCollected;
        }
    }

    public static class EnterpriseFarm extends Enterprise {

        private Map<String, Map<String, Double>> histories;
        private Integer totalCollected;
        private Integer todayCollected;
        private Map<String, Integer> collectedCereal;

        public EnterpriseFarm(Enterprise enterprise, Map<String, Map<String, Double>> histories, Integer totalCollected, Integer todayCollected, Map<String, Integer> collectedCereal) {
            super(enterprise);
            this.histories = histories;
            this.totalCollected = totalCollected;
            this.todayCollected = todayCollected;
            this.collectedCereal = collectedCereal;
        }

        public Map<String, Map<String, Double>> getHistories() {
            return histories;
        }

        public void setHistories(Map<String, Map<String, Double>> histories) {
            this.histories = histories;
        }

        public Integer getTotalCollected() {
            return totalCollected;
        }

        public void setTotalCollected(Integer totalCollected) {
            this.totalCollected = totalCollected;
        }

        public Integer getTodayCollected() {
            return todayCollected;
        }

        public void setTodayCollected(Integer todayCollected) {
            this.todayCollected = todayCollected;
        }

        public Map<String, Integer> getCollectedCereal() {
            return collectedCereal;
        }

        public void setCollectedCereal(Map<String, Integer> collectedCereal) {
            this.collectedCereal = collectedCereal;
        }
    }

    public static class EnterpriseParcelle extends Enterprise {

        private List<Parcelle> parcelles;

        public EnterpriseParcelle(Enterprise enterprise, List<Parcelle> parcelles) {
            super(enterprise);
            this.parcelles = parcelles;
        }

        public List<Parcelle> getParcelles() {
            return parcelles;
        }

        public void setParcelles(List<Parcelle> parcelles) {
            this.parcelles = parcelles;
        }

        public static class Parcelle {
            private String name;
            private String clientName;
            private boolean isStaff;
            private Integer price;
            private Coords signCoords;
            private String status;
            private Integer daysRent;

            public Parcelle(String name, String clientName, boolean isStaff, Integer price, Coords signCoords, String status, Integer daysRent) {
                this.name = name;
                this.clientName = clientName;
                this.isStaff = isStaff;
                this.price = price;
                this.signCoords = signCoords;
                this.status = status;
                this.daysRent = daysRent;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getClientName() {
                return clientName;
            }

            public void setClientName(String clientName) {
                this.clientName = clientName;
            }

            public boolean isStaff() {
                return isStaff;
            }

            public void setStaff(boolean staff) {
                isStaff = staff;
            }

            public Integer getPrice() {
                return price;
            }

            public void setPrice(Integer price) {
                this.price = price;
            }

            public Coords getSignCoords() {
                return signCoords;
            }

            public void setSignCoords(Coords signCoords) {
                this.signCoords = signCoords;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Integer getDaysRent() {
                return daysRent;
            }

            public void setDaysRent(Integer daysRent) {
                this.daysRent = daysRent;
            }

            public static class Serializer implements JsonSerializer<Parcelle> {
                @Override
                public JsonElement serialize(Parcelle src, Type type, JsonSerializationContext context) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("n", src.name);
                    if (src.clientName != null) obj.addProperty("c", src.clientName);
                    if (src.isStaff) obj.addProperty("s", true);
                    if (src.price != 0) obj.addProperty("p", src.price);
                    if (src.signCoords != null) obj.add("sc", context.serialize(src.signCoords, Coords.class));
                    obj.addProperty("st", src.status);
                    if (src.daysRent != null) obj.addProperty("dr", src.daysRent);
                    return obj;
                }
            }

            public static class Deserializer implements JsonDeserializer<Parcelle> {
                @Override
                public Parcelle deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                    JsonObject obj = json.getAsJsonObject();
                    String name = obj.get("n").getAsString();
                    String clientName = obj.has("c") ? obj.get("c").getAsString() : null;
                    boolean isStaff = obj.has("s") && obj.get("s").getAsBoolean();
                    Integer price = obj.has("p") ? obj.get("p").getAsInt() : 0;
                    Coords signCoords = obj.has("sc") ? (Coords) context.deserialize(obj.get("sc"), Coords.class) : null;
                    String status = obj.get("st").getAsString();
                    Integer daysRent = obj.has("dr") ? obj.get("dr").getAsInt() : null;
                    return new Parcelle(name, clientName, isStaff, price, signCoords, status, daysRent);
                }
            }
        }

        public static class Coords {
            private Integer x;
            private Integer y;
            private Integer z;

            public Coords(Integer x, Integer y, Integer z) {
                this.x = x;
                this.y = y;
                this.z = z;
            }

            public Integer getX() {
                return x;
            }

            public void setX(Integer x) {
                this.x = x;
            }

            public Integer getY() {
                return y;
            }

            public void setY(Integer y) {
                this.y = y;
            }

            public Integer getZ() {
                return z;
            }

            public void setZ(Integer z) {
                this.z = z;
            }

            public static class Serializer implements JsonSerializer<Coords> {
                @Override
                public JsonElement serialize(Coords src, Type type, JsonSerializationContext context) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("x", src.x);
                    obj.addProperty("y", src.y);
                    obj.addProperty("z", src.z);
                    return obj;
                }
            }

            public static class Deserializer implements JsonDeserializer<Coords> {
                @Override
                public Coords deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                    JsonObject obj = json.getAsJsonObject();
                    Integer x = obj.get("x").getAsInt();
                    Integer y = obj.get("y").getAsInt();
                    Integer z = obj.get("z").getAsInt();
                    return new Coords(x, y, z);
                }
            }
        }
    }

    public static class EnterprisePetrol extends Enterprise {

        private Map<String, Double> historyGenerated;
        private Map<String, Double> historyCollected;
        private Integer total;
        private Integer available;
        private boolean allowCountry;
        private boolean allowAlly;
        private boolean allowAll;
        private String associatedCountry;
        private Integer price;
        private Integer priceAverage;

        public EnterprisePetrol(Enterprise enterprise, Map<String, Double> historyGenerated, Map<String, Double> historyCollected, Integer total, Integer available, boolean allowCountry, boolean allowAlly, boolean allowAll, String associatedCountry, Integer price, Integer priceAverage) {
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

        public Map<String, Double> getHistoryGenerated() {
            return historyGenerated;
        }

        public void setHistoryGenerated(Map<String, Double> historyGenerated) {
            this.historyGenerated = historyGenerated;
        }

        public Map<String, Double> getHistoryCollected() {
            return historyCollected;
        }

        public void setHistoryCollected(Map<String, Double> historyCollected) {
            this.historyCollected = historyCollected;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getAvailable() {
            return available;
        }

        public void setAvailable(Integer available) {
            this.available = available;
        }

        public boolean isAllowCountry() {
            return allowCountry;
        }

        public void setAllowCountry(boolean allowCountry) {
            this.allowCountry = allowCountry;
        }

        public boolean isAllowAlly() {
            return allowAlly;
        }

        public void setAllowAlly(boolean allowAlly) {
            this.allowAlly = allowAlly;
        }

        public boolean isAllowAll() {
            return allowAll;
        }

        public void setAllowAll(boolean allowAll) {
            this.allowAll = allowAll;
        }

        public String getAssociatedCountry() {
            return associatedCountry;
        }

        public void setAssociatedCountry(String associatedCountry) {
            this.associatedCountry = associatedCountry;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public Integer getPriceAverage() {
            return priceAverage;
        }

        public void setPriceAverage(Integer priceAverage) {
            this.priceAverage = priceAverage;
        }
    }

    public static class EnterpriseTrader extends Enterprise {

        private Map<String, Double> history;
        private Integer totalGenerated;
        private Integer todayPercent;
        private Float sumInvestment;
        private Integer totalInvestors;

        public EnterpriseTrader(Enterprise enterprise, Map<String, Double> history, Integer totalGenerated, Integer todayPercent, Float sumInvestment, Integer totalInvestors) {
            super(enterprise);
            this.history = history;
            this.totalGenerated = totalGenerated;
            this.todayPercent = todayPercent;
            this.sumInvestment = sumInvestment;
            this.totalInvestors = totalInvestors;
        }

        public Map<String, Double> getHistory() {
            return history;
        }

        public void setHistory(Map<String, Double> history) {
            this.history = history;
        }

        public Integer getTotalGenerated() {
            return totalGenerated;
        }

        public void setTotalGenerated(Integer totalGenerated) {
            this.totalGenerated = totalGenerated;
        }

        public Integer getTodayPercent() {
            return todayPercent;
        }

        public void setTodayPercent(Integer todayPercent) {
            this.todayPercent = todayPercent;
        }

        public Float getSumInvestment() {
            return sumInvestment;
        }

        public void setSumInvestment(Float sumInvestment) {
            this.sumInvestment = sumInvestment;
        }

        public Integer getTotalInvestors() {
            return totalInvestors;
        }

        public void setTotalInvestors(Integer totalInvestors) {
            this.totalInvestors = totalInvestors;
        }
    }

    public static class EnterpriseBank {

        private Double bank;
        private Double flux14Days;
        private Double inProgressContracts;
        private Double salaries14Days;
        private Double taxes14Days;
        private List<Log> logs;

        public EnterpriseBank(Double bank, Double flux14Days, Double inProgressContracts, Double salaries14Days, Double taxes14Days, List<Log> logs) {
            this.bank = bank;
            this.flux14Days = flux14Days;
            this.inProgressContracts = inProgressContracts;
            this.salaries14Days = salaries14Days;
            this.taxes14Days = taxes14Days;
            this.logs = logs;
        }

        public Double getBank() {
            return bank;
        }

        public void setBank(Double bank) {
            this.bank = bank;
        }

        public Double getFlux14Days() {
            return flux14Days;
        }

        public void setFlux14Days(Double flux14Days) {
            this.flux14Days = flux14Days;
        }

        public Double getInProgressContracts() {
            return inProgressContracts;
        }

        public void setInProgressContracts(Double inProgressContracts) {
            this.inProgressContracts = inProgressContracts;
        }

        public Double getSalaries14Days() {
            return salaries14Days;
        }

        public void setSalaries14Days(Double salaries14Days) {
            this.salaries14Days = salaries14Days;
        }

        public Double getTaxes14Days() {
            return taxes14Days;
        }

        public void setTaxes14Days(Double taxes14Days) {
            this.taxes14Days = taxes14Days;
        }

        public List<Log> getLogs() {
            return logs;
        }

        public void setLogs(List<Log> logs) {
            this.logs = logs;
        }

        public static class Serializer implements JsonSerializer<EnterpriseBank> {
            @Override
            public JsonElement serialize(EnterpriseBank src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                if (src.bank != 0) obj.addProperty("b", src.bank);
                if (src.flux14Days != 0) obj.addProperty("f", src.flux14Days);
                if (src.inProgressContracts != 0) obj.addProperty("i", src.inProgressContracts);
                if (src.salaries14Days != 0) obj.addProperty("s", src.salaries14Days);
                if (src.taxes14Days != 0) obj.addProperty("t", src.taxes14Days);

                if (!src.logs.isEmpty()) obj.add("l", context.serialize(src.getLogs(), new TypeToken<List<Log>>() {}.getType()));
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<EnterpriseBank> {

            @Override
            public EnterpriseBank deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                Double bank = obj.has("b") ? obj.get("b").getAsDouble() : 0.0;
                Double flux14Days = obj.has("f") ? obj.get("f").getAsDouble() : 0.0;
                Double inProgressContracts = obj.has("i") ? obj.get("i").getAsDouble() : 0.0;
                Double salaries14Days = obj.has("s") ? obj.get("s").getAsDouble() : 0.0;
                Double taxes14Days = obj.has("t") ? obj.get("t").getAsDouble() : 0.0;
                List<Log> logs = obj.has("l") ? (List<Log>) context.deserialize(obj.get("l"), new TypeToken<List<Log>>() {}.getType()) : new ArrayList<Log>();
                return new EnterpriseBank(bank, flux14Days, inProgressContracts, salaries14Days, taxes14Days, logs);
            }
        }

        public static class Log {
            private String name;
            private Double amount;
            private Long timestamp;

            public Log(String name, Double amount, Long timestamp) {
                this.name = name;
                this.amount = amount;
                this.timestamp = timestamp;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Double getAmount() {
                return amount;
            }

            public void setAmount(Double amount) {
                this.amount = amount;
            }

            public Long getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(Long timestamp) {
                this.timestamp = timestamp;
            }

            public static class Serializer implements JsonSerializer<Log> {
                @Override
                public JsonElement serialize(Log src, Type type, JsonSerializationContext context) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("n", src.name);
                    obj.addProperty("a", src.amount);
                    obj.addProperty("t", src.timestamp);
                    return obj;
                }
            }

            public static class Deserializer implements JsonDeserializer<Log> {
                @Override
                public Log deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                    JsonObject obj = json.getAsJsonObject();
                    String name = obj.get("n").getAsString();
                    Double amount = obj.get("a").getAsDouble();
                    Long timestamp = obj.get("t").getAsLong();
                    return new Log(name, amount, timestamp);
                }
            }
        }
    }

    public enum EnterpriseType {
        BUILD,
        ENGINEER,
        TERRAFORM,
        JOURNALIST,
        CASINO,
        PVP,
        LOAN,
        REALESTATE,
        TRADER,
        BET,
        REPAIR,
        LAWYER,
        ELECTRIC,
        PETROL,
        FARM
    }
}