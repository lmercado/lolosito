package com.ngti.leandro.lol.model;

import java.util.EnumMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static EnumMap<Server, Retrofit> sInstancesPerServer = new EnumMap<>(Server.class);

    public static synchronized Retrofit getInstance(String serverKey) {
        final Server server = Server.getServerFromKey(serverKey);

        if (server!=null) {
            Retrofit retrofit = sInstancesPerServer.get(server);

            if (retrofit == null) {
                retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl(server.getBaseUrl())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                sInstancesPerServer.put(server, retrofit);
            }

            return retrofit;
        }
        else {
            throw new IllegalStateException("Unsupported server detected.");
        }
    }
}
