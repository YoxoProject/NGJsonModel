package fr.romaindu35.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * Données permettant de récupérer les données du marché mondial des céréales.
 * <p>
 * Pour pricesTexture, salesTexture et stocksTexture, il s'agit des graphiques générés par le serveur et encodés en Base64.
 * <p>
 * On ne viens pas optimiser le stockage car le poids des données n'est pas très important comparé à celui des images.
 */
@AllArgsConstructor
@Getter
@ToString
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
}
