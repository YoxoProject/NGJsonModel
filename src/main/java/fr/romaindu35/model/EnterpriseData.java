package fr.romaindu35.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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