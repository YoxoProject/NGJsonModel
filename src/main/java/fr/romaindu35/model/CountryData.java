package fr.romaindu35.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryData implements BaseData {

    private Long timestamp;
    private String server;
    private List<Country> countries;

    public CountryData(Long timestamp, String server, List<Country> countries) {
        this.timestamp = timestamp;
        this.server = server;
        this.countries = countries;
    }

    @Override
    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public String getServer() {
        return server;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public static class Serializer implements JsonSerializer<CountryData> {

        @Override
        public JsonElement serialize(CountryData src, Type type, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            obj.addProperty("t", src.timestamp);
            obj.addProperty("s", src.server);
            if (!src.countries.isEmpty()) {
                obj.add("c", context.serialize(src.countries, new TypeToken<List<Country>>() {
                }.getType()));
            }
            return obj;
        }
    }

    public static class Deserializer implements JsonDeserializer<CountryData> {

        @Override
        public CountryData deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject obj = json.getAsJsonObject();
            Long timestamp = obj.get("t").getAsLong();
            String server = obj.get("s").getAsString();
            List<Country> countries = obj.has("c") ? context.<List<Country>>deserialize(obj.get("c"), new TypeToken<List<Country>>() {
            }.getType()) : new ArrayList<Country>();
            return new CountryData(timestamp, server, countries);
        }
    }

    public static class Country {
        private String name;
        private String description;
        private String motd;
        private String entryMsg;
        private Integer age;
        private Integer countMembers;
        private Integer countNewMembers;
        private FHomeCoord fhomeCoord;
        private boolean isEmpire;
        private boolean isReferent;
        private boolean isTopWarzone;
        private boolean isRestrictAssault;
        private boolean isRestrictMissile;
        private Integer level;
        private Integer progress;
        private Integer notationsPosition;
        private List<String> tags;
        private String discord;
        private String allianceName;
        private Integer mmr;
        private Integer claims;
        private Integer power;
        private Integer maxPower;
        ;
        private Integer powerboostFixed;
        private Integer powerboostWarzone;
        private Integer powerboostUnesco;
        private Integer powerboostNewMember;
        private Integer powerboostMalusAssault;
        private String flag;
        private String leader;
        private List<String> officers;
        private List<String> members;
        private List<String> recruits;
        private Map<String, Integer> researchesLevel;
        private Bank bank;
        private Actions actions;
        private Settings settings;

        public Country(String name, String description, String motd, String entryMsg, Integer age, Integer countMembers, Integer countNewMembers, FHomeCoord fhomeCoord, boolean isEmpire, boolean isReferent, boolean isTopWarzone, boolean isRestrictAssault, boolean isRestrictMissile, Integer level, Integer progress, Integer notationsPosition, List<String> tags, String discord, String allianceName, Integer mmr, Integer claims, Integer power, Integer maxPower, Integer powerboostFixed, Integer powerboostWarzone, Integer powerboostUnesco, Integer powerboostNewMember, Integer powerboostMalusAssault, String flag, String leader, List<String> officers, List<String> members, List<String> recruits, Map<String, Integer> researchesLevel, Bank bank, Actions actions, Settings settings) {
            this.name = name;
            this.description = description;
            this.motd = motd;
            this.entryMsg = entryMsg;
            this.age = age;
            this.countMembers = countMembers;
            this.countNewMembers = countNewMembers;
            this.fhomeCoord = fhomeCoord;
            this.isEmpire = isEmpire;
            this.isReferent = isReferent;
            this.isTopWarzone = isTopWarzone;
            this.isRestrictAssault = isRestrictAssault;
            this.isRestrictMissile = isRestrictMissile;
            this.level = level;
            this.progress = progress;
            this.notationsPosition = notationsPosition;
            this.tags = tags;
            this.discord = discord;
            this.allianceName = allianceName;
            this.mmr = mmr;
            this.claims = claims;
            this.power = power;
            this.maxPower = maxPower;
            this.powerboostFixed = powerboostFixed;
            this.powerboostWarzone = powerboostWarzone;
            this.powerboostUnesco = powerboostUnesco;
            this.powerboostNewMember = powerboostNewMember;
            this.powerboostMalusAssault = powerboostMalusAssault;
            this.flag = flag;
            this.leader = leader;
            this.officers = officers;
            this.members = members;
            this.recruits = recruits;
            this.researchesLevel = researchesLevel;
            this.bank = bank;
            this.actions = actions;
            this.settings = settings;
        }

        // Getters and Setters for Country


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMotd() {
            return motd;
        }

        public void setMotd(String motd) {
            this.motd = motd;
        }

        public String getEntryMsg() {
            return entryMsg;
        }

        public void setEntryMsg(String entryMsg) {
            this.entryMsg = entryMsg;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getCountMembers() {
            return countMembers;
        }

        public void setCountMembers(Integer countMembers) {
            this.countMembers = countMembers;
        }

        public Integer getCountNewMembers() {
            return countNewMembers;
        }

        public void setCountNewMembers(Integer countNewMembers) {
            this.countNewMembers = countNewMembers;
        }

        public FHomeCoord getFhomeCoord() {
            return fhomeCoord;
        }

        public void setFhomeCoord(FHomeCoord fhomeCoord) {
            this.fhomeCoord = fhomeCoord;
        }

        public boolean isEmpire() {
            return isEmpire;
        }

        public void setEmpire(boolean empire) {
            isEmpire = empire;
        }

        public boolean isReferent() {
            return isReferent;
        }

        public void setReferent(boolean referent) {
            isReferent = referent;
        }

        public boolean isTopWarzone() {
            return isTopWarzone;
        }

        public void setTopWarzone(boolean topWarzone) {
            isTopWarzone = topWarzone;
        }

        public boolean isRestrictAssault() {
            return isRestrictAssault;
        }

        public void setRestrictAssault(boolean restrictAssault) {
            isRestrictAssault = restrictAssault;
        }

        public boolean isRestrictMissile() {
            return isRestrictMissile;
        }

        public void setRestrictMissile(boolean restrictMissile) {
            isRestrictMissile = restrictMissile;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public Integer getProgress() {
            return progress;
        }

        public void setProgress(Integer progress) {
            this.progress = progress;
        }

        public Integer getNotationsPosition() {
            return notationsPosition;
        }

        public void setNotationsPosition(Integer notationsPosition) {
            this.notationsPosition = notationsPosition;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public String getDiscord() {
            return discord;
        }

        public void setDiscord(String discord) {
            this.discord = discord;
        }

        public String getAllianceName() {
            return allianceName;
        }

        public void setAllianceName(String allianceName) {
            this.allianceName = allianceName;
        }

        public Integer getMmr() {
            return mmr;
        }

        public void setMmr(Integer mmr) {
            this.mmr = mmr;
        }

        public Integer getClaims() {
            return claims;
        }

        public void setClaims(Integer claims) {
            this.claims = claims;
        }

        public Integer getPower() {
            return power;
        }

        public void setPower(Integer power) {
            this.power = power;
        }

        public Integer getMaxPower() {
            return maxPower;
        }

        public void setMaxPower(Integer maxPower) {
            this.maxPower = maxPower;
        }

        public Integer getPowerboostFixed() {
            return powerboostFixed;
        }

        public void setPowerboostFixed(Integer powerboostFixed) {
            this.powerboostFixed = powerboostFixed;
        }

        public Integer getPowerboostWarzone() {
            return powerboostWarzone;
        }

        public void setPowerboostWarzone(Integer powerboostWarzone) {
            this.powerboostWarzone = powerboostWarzone;
        }

        public Integer getPowerboostUnesco() {
            return powerboostUnesco;
        }

        public void setPowerboostUnesco(Integer powerboostUnesco) {
            this.powerboostUnesco = powerboostUnesco;
        }

        public Integer getPowerboostNewMember() {
            return powerboostNewMember;
        }

        public void setPowerboostNewMember(Integer powerboostNewMember) {
            this.powerboostNewMember = powerboostNewMember;
        }

        public Integer getPowerboostMalusAssault() {
            return powerboostMalusAssault;
        }

        public void setPowerboostMalusAssault(Integer powerboostMalusAssault) {
            this.powerboostMalusAssault = powerboostMalusAssault;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getLeader() {
            return leader;
        }

        public void setLeader(String leader) {
            this.leader = leader;
        }

        public List<String> getOfficers() {
            return officers;
        }

        public void setOfficers(List<String> officers) {
            this.officers = officers;
        }

        public List<String> getMembers() {
            return members;
        }

        public void setMembers(List<String> members) {
            this.members = members;
        }

        public List<String> getRecruits() {
            return recruits;
        }

        public void setRecruits(List<String> recruits) {
            this.recruits = recruits;
        }

        public Map<String, Integer> getResearchesLevel() {
            return researchesLevel;
        }

        public void setResearchesLevel(Map<String, Integer> researchesLevel) {
            this.researchesLevel = researchesLevel;
        }

        public Bank getBank() {
            return bank;
        }

        public void setBank(Bank bank) {
            this.bank = bank;
        }

        public Actions getActions() {
            return actions;
        }

        public void setActions(Actions actions) {
            this.actions = actions;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public static class Serializer implements JsonSerializer<Country> {
            @Override
            public JsonElement serialize(Country src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                obj.addProperty("n", src.name);
                if (src.description != null && !src.description.isEmpty()) obj.addProperty("d", src.description);
                if (src.motd != null && !src.motd.isEmpty()) obj.addProperty("m", src.motd);
                if (src.entryMsg != null && !src.entryMsg.isEmpty()) obj.addProperty("em", src.entryMsg);
                obj.addProperty("a", src.age);
                obj.addProperty("cm", src.countMembers);
                obj.addProperty("cnm", src.countNewMembers);
                if (src.fhomeCoord != null) obj.add("fh", context.serialize(src.fhomeCoord));
                if (src.isEmpire) obj.addProperty("ie", true);
                if (src.isReferent) obj.addProperty("ir", true);
                if (src.isTopWarzone) obj.addProperty("itw", true);
                if (src.isRestrictAssault) obj.addProperty("ira", true);
                if (src.isRestrictMissile) obj.addProperty("irm", true);
                obj.addProperty("l", src.level);
                obj.addProperty("p", src.progress);
                obj.addProperty("np", src.notationsPosition);
                if (src.tags != null && !src.tags.isEmpty()) obj.add("t", context.serialize(src.tags));
                if (src.discord != null && !src.discord.isEmpty()) obj.addProperty("di", src.discord);
                if (src.allianceName != null && !src.allianceName.isEmpty()) obj.addProperty("an", src.allianceName);
                obj.addProperty("mm", src.mmr);
                obj.addProperty("c", src.claims);
                obj.addProperty("po", src.power);
                obj.addProperty("mp", src.maxPower);
                obj.addProperty("pbf", src.powerboostFixed);
                obj.addProperty("pbw", src.powerboostWarzone);
                obj.addProperty("pbu", src.powerboostUnesco);
                obj.addProperty("pbnm", src.powerboostNewMember);
                obj.addProperty("pbma", src.powerboostMalusAssault);
                if (src.flag != null && !src.flag.isEmpty()) obj.addProperty("f", src.flag);
                if (src.leader != null) obj.addProperty("le", src.leader);
                if (src.officers != null && !src.officers.isEmpty()) obj.add("of", context.serialize(src.officers));
                if (src.members != null && !src.members.isEmpty()) obj.add("me", context.serialize(src.members));
                if (src.recruits != null && !src.recruits.isEmpty()) obj.add("re", context.serialize(src.recruits));
                if (src.researchesLevel != null && !src.researchesLevel.isEmpty())
                    obj.add("rl", context.serialize(src.researchesLevel));
                if (src.bank != null) obj.add("b", context.serialize(src.bank));
                if (src.actions != null) obj.add("ac", context.serialize(src.actions));
                if (src.settings != null) obj.add("s", context.serialize(src.settings));
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<Country> {
            @Override
            public Country deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                String name = obj.has("n") ? obj.get("n").getAsString() : "";
                String description = obj.has("d") ? obj.get("d").getAsString() : "";
                String motd = obj.has("m") ? obj.get("m").getAsString() : "";
                String entryMsg = obj.has("em") ? obj.get("em").getAsString() : "";
                Integer age = obj.has("a") ? obj.get("a").getAsInt() : 0;
                Integer countMembers = obj.has("cm") ? obj.get("cm").getAsInt() : 0;
                Integer countNewMembers = obj.has("cnm") ? obj.get("cnm").getAsInt() : 0;
                FHomeCoord fhomeCoord = obj.has("fh") ? context.<FHomeCoord>deserialize(obj.get("fh"), FHomeCoord.class) : null;
                boolean isEmpire = obj.has("ie") && obj.get("ie").getAsBoolean();
                boolean isReferent = obj.has("ir") && obj.get("ir").getAsBoolean();
                boolean isTopWarzone = obj.has("itw") && obj.get("itw").getAsBoolean();
                boolean isRestrictAssault = obj.has("ira") && obj.get("ira").getAsBoolean();
                boolean isRestrictMissile = obj.has("irm") && obj.get("irm").getAsBoolean();
                Integer level = obj.has("l") ? obj.get("l").getAsInt() : 0;
                Integer progress = obj.has("p") ? obj.get("p").getAsInt() : 0;
                Integer notationsPosition = obj.has("np") ? obj.get("np").getAsInt() : 0;
                List<String> tags = obj.has("t") ? context.<List<String>>deserialize(obj.get("t"), new TypeToken<List<String>>() {
                }.getType()) : new ArrayList<String>();
                String discord = obj.has("di") ? obj.get("di").getAsString() : "";
                String allianceName = obj.has("an") ? obj.get("an").getAsString() : "";
                Integer mmr = obj.has("mm") ? obj.get("mm").getAsInt() : 0;
                Integer claims = obj.has("c") ? obj.get("c").getAsInt() : 0;
                Integer power = obj.has("po") ? obj.get("po").getAsInt() : 0;
                Integer maxPower = obj.has("mp") ? obj.get("mp").getAsInt() : 0;
                Integer powerboostFixed = obj.has("pbf") ? obj.get("pbf").getAsInt() : 0;
                Integer powerboostWarzone = obj.has("pbw") ? obj.get("pbw").getAsInt() : 0;
                Integer powerboostUnesco = obj.has("pbu") ? obj.get("pbu").getAsInt() : 0;
                Integer powerboostNewMember = obj.has("pbnm") ? obj.get("pbnm").getAsInt() : 0;
                Integer powerboostMalusAssault = obj.has("pbma") ? obj.get("pbma").getAsInt() : 0;
                String flag = obj.has("f") ? obj.get("f").getAsString() : "";
                String leader = obj.has("le") ? obj.get("le").getAsString() : "";
                List<String> officers = obj.has("of") ? context.<List<String>>deserialize(obj.get("of"), new TypeToken<List<String>>() {
                }.getType()) : new ArrayList<String>();
                List<String> members = obj.has("me") ? context.<List<String>>deserialize(obj.get("me"), new TypeToken<List<String>>() {
                }.getType()) : new ArrayList<String>();
                List<String> recruits = obj.has("re") ? context.<List<String>>deserialize(obj.get("re"), new TypeToken<List<String>>() {
                }.getType()) : new ArrayList<String>();
                Map<String, Integer> researchesLevel = obj.has("rl") ? context.<Map<String, Integer>>deserialize(obj.get("rl"), new TypeToken<Map<String, Integer>>() {
                }.getType()) : new HashMap<String, Integer>();
                Bank bank = obj.has("b") ? context.<Bank>deserialize(obj.get("b"), Bank.class) : null;
                Actions actions = obj.has("ac") ? context.<Actions>deserialize(obj.get("ac"), Actions.class) : null;
                Settings settings = obj.has("s") ? context.<Settings>deserialize(obj.get("s"), Settings.class) : null;

                return new Country(name, description, motd, entryMsg, age, countMembers, countNewMembers, fhomeCoord, isEmpire, isReferent, isTopWarzone, isRestrictAssault, isRestrictMissile, level, progress, notationsPosition, tags, discord, allianceName, mmr, claims, power, maxPower, powerboostFixed, powerboostWarzone, powerboostUnesco, powerboostNewMember, powerboostMalusAssault, flag, leader, officers, members, recruits, researchesLevel, bank, actions, settings);
            }
        }
    }

    public static class FHomeCoord {
        private int x;
        private int z;

        public FHomeCoord(int x, int z) {
            this.x = x;
            this.z = z;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getZ() {
            return z;
        }

        public void setZ(int z) {
            this.z = z;
        }

        public static class Serializer implements JsonSerializer<FHomeCoord> {
            @Override
            public JsonElement serialize(FHomeCoord src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                obj.addProperty("x", src.x);
                obj.addProperty("z", src.z);
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<FHomeCoord> {
            @Override
            public FHomeCoord deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                return new FHomeCoord(obj.get("x").getAsInt(), obj.get("z").getAsInt());
            }
        }
    }

    public static class Bank {
        private int balance;
        private List<String> owners;
        private List<String> members;
        private List<BankLog> logs;

        public Bank(int balance, List<String> owners, List<String> members, List<BankLog> logs) {
            this.balance = balance;
            this.owners = owners;
            this.members = members;
            this.logs = logs;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public List<String> getOwners() {
            return owners;
        }

        public void setOwners(List<String> owners) {
            this.owners = owners;
        }

        public List<String> getMembers() {
            return members;
        }

        public void setMembers(List<String> members) {
            this.members = members;
        }

        public List<BankLog> getLogs() {
            return logs;
        }

        public void setLogs(List<BankLog> logs) {
            this.logs = logs;
        }

        public static class Serializer implements JsonSerializer<Bank> {
            @Override
            public JsonElement serialize(Bank src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                obj.addProperty("b", src.balance);
                if (src.owners != null && !src.owners.isEmpty()) obj.add("o", context.serialize(src.owners));
                if (src.members != null && !src.members.isEmpty()) obj.add("m", context.serialize(src.members));
                if (src.logs != null && !src.logs.isEmpty()) obj.add("l", context.serialize(src.logs));
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<Bank> {
            @Override
            public Bank deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                int balance = obj.has("b") ? obj.get("b").getAsInt() : 0;
                List<String> owners = obj.has("o") ? context.<List<String>>deserialize(obj.get("o"), new TypeToken<List<String>>() {
                }.getType()) : new ArrayList<String>();
                List<String> members = obj.has("m") ? context.<List<String>>deserialize(obj.get("m"), new TypeToken<List<String>>() {
                }.getType()) : new ArrayList<String>();
                List<BankLog> logs = obj.has("l") ? context.<List<BankLog>>deserialize(obj.get("l"), new TypeToken<List<BankLog>>() {
                }.getType()) : new ArrayList<BankLog>();
                return new Bank(balance, owners, members, logs);
            }
        }
    }

    public static class BankLog {
        private double amount;
        private long timestamp;
        private String pseudo;

        public BankLog(double amount, long timestamp, String pseudo) {
            this.amount = amount;
            this.timestamp = timestamp;
            this.pseudo = pseudo;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getPseudo() {
            return pseudo;
        }

        public void setPseudo(String pseudo) {
            this.pseudo = pseudo;
        }

        public static class Serializer implements JsonSerializer<BankLog> {
            @Override
            public JsonElement serialize(BankLog src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                obj.addProperty("a", src.amount);
                obj.addProperty("t", src.timestamp);
                obj.addProperty("p", src.pseudo);
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<BankLog> {
            @Override
            public BankLog deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                return new BankLog(obj.get("a").getAsDouble(), obj.get("t").getAsLong(), obj.get("p").getAsString());
            }
        }
    }

    public static class Actions {
        private double totalDividendes;
        private int availableActions;
        private int actionPrice;
        private List<DividendeClassement> dividendesClassement;
        private List<DividendeHistory> dividendesHistory;

        public Actions(double totalDividendes, int availableActions, int actionPrice, List<DividendeClassement> dividendesClassement, List<DividendeHistory> dividendesHistory) {
            this.totalDividendes = totalDividendes;
            this.availableActions = availableActions;
            this.actionPrice = actionPrice;
            this.dividendesClassement = dividendesClassement;
            this.dividendesHistory = dividendesHistory;
        }

        public double getTotalDividendes() {
            return totalDividendes;
        }

        public void setTotalDividendes(double totalDividendes) {
            this.totalDividendes = totalDividendes;
        }

        public int getAvailableActions() {
            return availableActions;
        }

        public void setAvailableActions(int availableActions) {
            this.availableActions = availableActions;
        }

        public int getActionPrice() {
            return actionPrice;
        }

        public void setActionPrice(int actionPrice) {
            this.actionPrice = actionPrice;
        }

        public List<DividendeClassement> getDividendesClassement() {
            return dividendesClassement;
        }

        public void setDividendesClassement(List<DividendeClassement> dividendesClassement) {
            this.dividendesClassement = dividendesClassement;
        }

        public List<DividendeHistory> getDividendesHistory() {
            return dividendesHistory;
        }

        public void setDividendesHistory(List<DividendeHistory> dividendesHistory) {
            this.dividendesHistory = dividendesHistory;
        }


        public static class Serializer implements JsonSerializer<Actions> {
            @Override
            public JsonElement serialize(Actions src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                obj.addProperty("td", src.totalDividendes);
                obj.addProperty("aa", src.availableActions);
                obj.addProperty("ap", src.actionPrice);
                if (src.dividendesClassement != null && !src.dividendesClassement.isEmpty())
                    obj.add("dc", context.serialize(src.dividendesClassement));
                if (src.dividendesHistory != null && !src.dividendesHistory.isEmpty())
                    obj.add("dh", context.serialize(src.dividendesHistory));
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<Actions> {
            @Override
            public Actions deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                double totalDividendes = obj.has("td") ? obj.get("td").getAsDouble() : 0;
                int availableActions = obj.has("aa") ? obj.get("aa").getAsInt() : 0;
                int actionPrice = obj.has("ap") ? obj.get("ap").getAsInt() : 0;
                List<DividendeClassement> dividendesClassement = obj.has("dc") ? context.<List<DividendeClassement>>deserialize(obj.get("dc"), new TypeToken<List<DividendeClassement>>() {
                }.getType()) : new ArrayList<DividendeClassement>();
                List<DividendeHistory> dividendesHistory = obj.has("dh") ? context.<List<DividendeHistory>>deserialize(obj.get("dh"), new TypeToken<List<DividendeHistory>>() {
                }.getType()) : new ArrayList<DividendeHistory>();
                return new Actions(totalDividendes, availableActions, actionPrice, dividendesClassement, dividendesHistory);
            }
        }
    }

    public static class DividendeClassement {
        private String pays;
        private double montant;

        public DividendeClassement(String pays, double montant) {
            this.pays = pays;
            this.montant = montant;
        }

        public String getPays() {
            return pays;
        }

        public void setPays(String pays) {
            this.pays = pays;
        }

        public double getMontant() {
            return montant;
        }

        public void setMontant(double montant) {
            this.montant = montant;
        }

        public static class Serializer implements JsonSerializer<DividendeClassement> {
            @Override
            public JsonElement serialize(DividendeClassement src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                obj.addProperty("p", src.pays);
                obj.addProperty("m", src.montant);
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<DividendeClassement> {
            @Override
            public DividendeClassement deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                return new DividendeClassement(obj.get("p").getAsString(), obj.get("m").getAsDouble());
            }
        }
    }

    public static class DividendeHistory {
        private String pays;
        private double montant;
        private long timestamp;

        public DividendeHistory(String pays, double montant, long timestamp) {
            this.pays = pays;
            this.montant = montant;
            this.timestamp = timestamp;
        }

        public String getPays() {
            return pays;
        }

        public void setPays(String pays) {
            this.pays = pays;
        }

        public double getMontant() {
            return montant;
        }

        public void setMontant(double montant) {
            this.montant = montant;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public static class Serializer implements JsonSerializer<DividendeHistory> {
            @Override
            public JsonElement serialize(DividendeHistory src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                obj.addProperty("p", src.pays);
                obj.addProperty("m", src.montant);
                obj.addProperty("t", src.timestamp);
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<DividendeHistory> {
            @Override
            public DividendeHistory deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                return new DividendeHistory(obj.get("p").getAsString(), obj.get("m").getAsDouble(), obj.get("t").getAsLong());
            }
        }
    }

    public static class Settings {
        private boolean invitationNeeded;
        private boolean recruitmentOpen;
        private boolean doKickRecruitWarReason;
        private boolean doKickMemberNegativePower;
        private List<Permission> permissions;
        private List<SettingLog> logs;

        public Settings(boolean invitationNeeded, boolean recruitmentOpen, boolean doKickRecruitWarReason, boolean doKickMemberNegativePower, List<Permission> permissions, List<SettingLog> logs) {
            this.invitationNeeded = invitationNeeded;
            this.recruitmentOpen = recruitmentOpen;
            this.doKickRecruitWarReason = doKickRecruitWarReason;
            this.doKickMemberNegativePower = doKickMemberNegativePower;
            this.permissions = permissions;
            this.logs = logs;
        }

        public boolean isInvitationNeeded() {
            return invitationNeeded;
        }

        public void setInvitationNeeded(boolean invitationNeeded) {
            this.invitationNeeded = invitationNeeded;
        }

        public boolean isRecruitmentOpen() {
            return recruitmentOpen;
        }

        public void setRecruitmentOpen(boolean recruitmentOpen) {
            this.recruitmentOpen = recruitmentOpen;
        }

        public boolean isDoKickRecruitWarReason() {
            return doKickRecruitWarReason;
        }

        public void setDoKickRecruitWarReason(boolean doKickRecruitWarReason) {
            this.doKickRecruitWarReason = doKickRecruitWarReason;
        }

        public boolean isDoKickMemberNegativePower() {
            return doKickMemberNegativePower;
        }

        public void setDoKickMemberNegativePower(boolean doKickMemberNegativePower) {
            this.doKickMemberNegativePower = doKickMemberNegativePower;
        }

        public List<Permission> getPermissions() {
            return permissions;
        }

        public void setPermissions(List<Permission> permissions) {
            this.permissions = permissions;
        }

        public List<SettingLog> getLogs() {
            return logs;
        }

        public void setLogs(List<SettingLog> logs) {
            this.logs = logs;
        }

        public static class Serializer implements JsonSerializer<Settings> {
            @Override
            public JsonElement serialize(Settings src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                if (src.invitationNeeded) obj.addProperty("in", true);
                if (src.recruitmentOpen) obj.addProperty("ro", true);
                if (src.doKickRecruitWarReason) obj.addProperty("dkrwr", true);
                if (src.doKickMemberNegativePower) obj.addProperty("dkmnp", true);
                if (src.permissions != null && !src.permissions.isEmpty())
                    obj.add("p", context.serialize(src.permissions));
                if (src.logs != null && !src.logs.isEmpty()) obj.add("l", context.serialize(src.logs));
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<Settings> {
            @Override
            public Settings deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                boolean invitationNeeded = obj.has("in") && obj.get("in").getAsBoolean();
                boolean recruitmentOpen = obj.has("ro") && obj.get("ro").getAsBoolean();
                boolean doKickRecruitWarReason = obj.has("dkrwr") && obj.get("dkrwr").getAsBoolean();
                boolean doKickMemberNegativePower = obj.has("dkmnp") && obj.get("dkmnp").getAsBoolean();
                List<Permission> permissions = obj.has("p") ? context.<List<Permission>>deserialize(obj.get("p"), new TypeToken<List<Permission>>() {
                }.getType()) : new ArrayList<Permission>();
                List<SettingLog> logs = obj.has("l") ? context.<List<SettingLog>>deserialize(obj.get("l"), new TypeToken<List<SettingLog>>() {
                }.getType()) : new ArrayList<SettingLog>();
                return new Settings(invitationNeeded, recruitmentOpen, doKickRecruitWarReason, doKickMemberNegativePower, permissions, logs);
            }
        }
    }

    public static class Permission {
        private String name;
        private String description;
        private boolean leader;
        private boolean officer;
        private boolean member;
        private boolean recruit;
        private boolean ally;
        private boolean neutral;
        private boolean enemy;

        public Permission(String name, String description, boolean leader, boolean officer, boolean member, boolean recruit, boolean ally, boolean neutral, boolean enemy) {
            this.name = name;
            this.description = description;
            this.leader = leader;
            this.officer = officer;
            this.member = member;
            this.recruit = recruit;
            this.ally = ally;
            this.neutral = neutral;
            this.enemy = enemy;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isLeader() {
            return leader;
        }

        public void setLeader(boolean leader) {
            this.leader = leader;
        }

        public boolean isOfficer() {
            return officer;
        }

        public void setOfficer(boolean officer) {
            this.officer = officer;
        }

        public boolean isMember() {
            return member;
        }

        public void setMember(boolean member) {
            this.member = member;
        }

        public boolean isRecruit() {
            return recruit;
        }

        public void setRecruit(boolean recruit) {
            this.recruit = recruit;
        }

        public boolean isAlly() {
            return ally;
        }

        public void setAlly(boolean ally) {
            this.ally = ally;
        }

        public boolean isNeutral() {
            return neutral;
        }

        public void setNeutral(boolean neutral) {
            this.neutral = neutral;
        }

        public boolean isEnemy() {
            return enemy;
        }

        public void setEnemy(boolean enemy) {
            this.enemy = enemy;
        }

        public static class Serializer implements JsonSerializer<Permission> {
            @Override
            public JsonElement serialize(Permission src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                obj.addProperty("n", src.name);
                if (src.description != null) obj.addProperty("d", src.description);
                if (src.leader) obj.addProperty("l", true);
                if (src.officer) obj.addProperty("o", true);
                if (src.member) obj.addProperty("m", true);
                if (src.recruit) obj.addProperty("r", true);
                if (src.ally) obj.addProperty("al", true);
                if (src.neutral) obj.addProperty("ne", true);
                if (src.enemy) obj.addProperty("e", true);
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<Permission> {
            @Override
            public Permission deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                String name = obj.get("n").getAsString();
                String description = obj.has("d") ? obj.get("d").getAsString() : "";
                boolean leader = obj.has("l") && obj.get("l").getAsBoolean();
                boolean officer = obj.has("o") && obj.get("o").getAsBoolean();
                boolean member = obj.has("m") && obj.get("m").getAsBoolean();
                boolean recruit = obj.has("r") && obj.get("r").getAsBoolean();
                boolean ally = obj.has("al") && obj.get("al").getAsBoolean();
                boolean neutral = obj.has("ne") && obj.get("ne").getAsBoolean();
                boolean enemy = obj.has("e") && obj.get("e").getAsBoolean();
                return new Permission(name, description, leader, officer, member, recruit, ally, neutral, enemy);
            }
        }
    }

    public static class SettingLog {
        private String user;
        private String action;
        private String target;
        private long timestamp;

        public SettingLog(String user, String action, String target, long timestamp) {
            this.user = user;
            this.action = action;
            this.target = target;
            this.timestamp = timestamp;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public static class Serializer implements JsonSerializer<SettingLog> {
            @Override
            public JsonElement serialize(SettingLog src, Type type, JsonSerializationContext context) {
                JsonObject obj = new JsonObject();
                obj.addProperty("u", src.user);
                obj.addProperty("a", src.action);
                if (src.target != null) obj.addProperty("t", src.target);
                obj.addProperty("ts", src.timestamp);
                return obj;
            }
        }

        public static class Deserializer implements JsonDeserializer<SettingLog> {
            @Override
            public SettingLog deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                String user = obj.get("u").getAsString();
                String action = obj.get("a").getAsString();
                String target = obj.has("t") ? obj.get("t").getAsString() : null;
                long timestamp = obj.get("ts").getAsLong();
                return new SettingLog(user, action, target, timestamp);
            }
        }
    }
}