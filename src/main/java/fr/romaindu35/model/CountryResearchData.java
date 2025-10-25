package fr.romaindu35.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountryResearchData {

    private final Map<String, Long> timestamp;
    private final Map<String, ResearchConfig> researchesConfig;
    private final Map<String, List<CountryData>> researches;

    public CountryResearchData(Map<String, Long> timestamp, Map<String, ResearchConfig> researchesConfig, Map<String, List<CountryData>> researches) {
        this.timestamp = timestamp;
        this.researchesConfig = researchesConfig;
        this.researches = researches;
    }

    public Map<String, Long> getTimestamp() {
        return timestamp;
    }

    public Map<String, ResearchConfig> getResearchesConfig() {
        return researchesConfig;
    }

    public Map<String, List<CountryData>> getResearches() {
        return researches;
    }

    @Override
    public String toString() {
        return "CountryResearchData{" +
                "timestamp=" + timestamp +
                ", researchesConfig=" + researchesConfig +
                ", researches=" + researches +
                '}';
    }

    public static class CountryData {

        private final String country;
        private final Map<String, Integer> researchesLevels;
        private final LastResearch lastResearch;
        private final Map<String, Double> researchesConditionsValues;

        public CountryData(String country, Map<String, Integer> researchesLevels, LastResearch lastResearch, Map<String, Double> researchesConditionsValues) {
            this.country = country;
            this.researchesLevels = researchesLevels;
            this.lastResearch = lastResearch;
            this.researchesConditionsValues = researchesConditionsValues;
        }

        public String getCountry() {
            return this.country;
        }

        public Map<String, Integer> getResearchesLevels() {
            return researchesLevels;
        }

        public LastResearch getLastResearch() {
            return lastResearch;
        }

        public Map<String, Double> getResearchesConditionsValues() {
            return researchesConditionsValues;
        }

        @Override
        public String toString() {
            return "CountryData{" +
                    "country='" + country + '\'' +
                    ", researchesLevels=" + researchesLevels +
                    ", lastResearch=" + lastResearch +
                    ", researchesConditionsValues=" + researchesConditionsValues +
                    '}';
        }
    }

    public static class LastResearch {
        private final String domain;
        private final Integer level;
        private final long timestamp;

        public LastResearch(String domain, Integer level, long timestamp) {
            this.domain = domain;
            this.level = level;
            this.timestamp = timestamp;
        }

        public String getDomain() {
            return domain;
        }

        public Integer getLevel() {
            return level;
        }

        public float getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return "LastResearch{" +
                    "domain='" + domain + '\'' +
                    ", level=" + level +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

    public static class ResearchConfig {
        private final String name;
        private final Integer maxLevel;
        private final String description;
        private final List<Level> levels;

        public ResearchConfig(String name, Integer maxLevel, String description, List<Level> levels) {
            this.name = name;
            this.maxLevel = maxLevel;
            this.description = description;
            this.levels = levels;
        }

        public String getName() {
            return name;
        }

        public Integer getMaxLevel() {
            return maxLevel;
        }

        public String getDescription() {
            return description;
        }

        public List<Level> getLevels() {
            return levels;
        }

        @Override
        public String toString() {
            return "ResearchConfig{" +
                    "name='" + name + '\'' +
                    ", maxLevel=" + maxLevel +
                    ", description='" + description + '\'' +
                    ", levels=" + levels +
                    '}';
        }
    }

    public static class Level {
        private final Integer level;
        private final Integer duration;
        private final List<String> conditions;
        private final List<String> rewards; // Ajouté avec la version 1.0.9

        public Level(Integer level, Integer duration, List<String> conditions, List<String> rewards) {
            this.level = level;
            this.duration = duration;
            this.conditions = conditions;
            this.rewards = rewards != null ? rewards : new ArrayList<String>(); // Permet de supporter les anciennes données sans récompenses
        }

        public Integer getLevel() {
            return level;
        }

        public Integer getDuration() {
            return duration;
        }

        public List<String> getConditions() {
            return conditions;
        }

        public List<String> getRewards() {
            return rewards;
        }

        @Override
        public String toString() {
            return "Level{" +
                    "level=" + level +
                    ", duration=" + duration +
                    ", conditions=" + conditions +
                    ", rewards=" + rewards +
                    '}';
        }
    }
}