package fr.romaindu35.model;

import java.util.Map;

/**
 * Données permettant de récupérer les données du marché mondial des céréales.
 *
 * Pour pricesTexture, salesTexture et stocksTexture, il s'agit des graphiques générés par le serveur et encodés en Base64.
 */
public class CerealGlobalMarketData implements BaseData {

    private final String server;
    private final Long timestamp;

    private final String pricesTexture;
    private final String salesTexture;
    private final String stocksTexture;

    private final Map<String, Map<String, Double>> pricesHistory;
    private final Map<String, Map<String, Double>> salesHistory;
    private final Map<String, Map<String, Double>> stocksHistory;
    private final Map<String, Integer> cerealsPrice;

    public CerealGlobalMarketData(String server, Long timestamp, String pricesTexture, String salesTexture, String stocksTexture, Map<String, Map<String, Double>> pricesHistory, Map<String, Map<String, Double>> salesHistory, Map<String, Map<String, Double>> stocksHistory, Map<String, Integer> cerealsPrice) {
        this.server = server;
        this.timestamp = timestamp;
        this.pricesTexture = pricesTexture;
        this.salesTexture = salesTexture;
        this.stocksTexture = stocksTexture;
        this.pricesHistory = pricesHistory;
        this.salesHistory = salesHistory;
        this.stocksHistory = stocksHistory;
        this.cerealsPrice = cerealsPrice;
    }

    @Override
    public String getServer() {
        return server;
    }

    @Override
    public Long getTimestamp() {
        return timestamp;
    }

    public String getPricesTexture() {
        return pricesTexture;
    }

    public String getSalesTexture() {
        return salesTexture;
    }

    public String getStocksTexture() {
        return stocksTexture;
    }

    public Map<String, Map<String, Double>> getPricesHistory() {
        return pricesHistory;
    }

    public Map<String, Map<String, Double>> getSalesHistory() {
        return salesHistory;
    }

    public Map<String, Map<String, Double>> getStocksHistory() {
        return stocksHistory;
    }

    public Map<String, Integer> getCerealsPrice() {
        return cerealsPrice;
    }

}
