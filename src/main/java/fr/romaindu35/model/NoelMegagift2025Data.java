package fr.romaindu35.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Modèle de données pour l'événement Noël Megagift 2025.
 * Événement interserveur du 14/12/2024 au 06/01/2025
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoelMegagift2025Data implements BaseData {

    @SerializedName("s")
    private String server;
    @SerializedName("t")
    private Long timestamp;
    @SerializedName("p")
    private List<NoelMegagift2025Player> players;

    /**
     * Représente un joueur participant à l'événement Noël Megagift 2025.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NoelMegagift2025Player {
        @SerializedName("u")
        private String username;
        @SerializedName("s")
        private String server;
        @SerializedName("t")
        private long timestamp;
        @SerializedName("l")
        private List<String> loots;
    }
}