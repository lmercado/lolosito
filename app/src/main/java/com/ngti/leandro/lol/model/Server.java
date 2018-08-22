package com.ngti.leandro.lol.model;

public enum Server {

    BRAZIL("br", "https://br1.api.riotgames.com"),
    EUROPE_EAST ("eune", "https://eun1.api.riotgames.com"),
    EUROPE_WEST ("euw", "https://euw1.api.riotgames.com"),
    LATIN_AMERICAN_NORTH ("lan", "https://la1.api.riotgames.com"),
    LATIN_AMERICA_SOUTH ("las", "https://la2.api.riotgames.com"),
    NORTH_AMERICA ("na", "https://na1.api.riotgames.com"),
    OCEANIA ("oce", "https://oc1.api.riotgames.com"),
    RUSIA ("ru", "https://ru.api.riotgames.com"),
    TURKEY ("tr", "https://tr1.api.riotgames.com"),
    JAPAN ("jp", "https://jp1.api.riotgames.com");

    private final String mKey;
    private final String mBaseUrl;

    Server(String key, String baseUrl) {
        mKey = key;
        mBaseUrl = baseUrl;
    }

    public static Server getServerFromKey(final String key) {
        Server match = null;

        final Server[] servers = Server.values();

        for (Server server : servers) {
            if (server.mKey.equalsIgnoreCase(key)) {
                match = server;
                break;
            }
        }

        return match;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }
}
