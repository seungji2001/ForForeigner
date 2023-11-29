package Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MapRetrofitClient {
    private static MapRetrofitClient instance = null;
    private static MapRetrofitInterface mapRetrofitInterface;
    private static String baseUrl = "https://maps.googleapis.com/maps/api/";

    private MapRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())//이 부분 추가
                .build();

        mapRetrofitInterface = retrofit.create(MapRetrofitInterface.class);
    }

    public static MapRetrofitClient getInstance() {
        if (instance == null) {
            instance = new MapRetrofitClient();
        }
        return instance;
    }

    public static MapRetrofitInterface getMapRetrofitInterface() {
        return mapRetrofitInterface;
    }
}
