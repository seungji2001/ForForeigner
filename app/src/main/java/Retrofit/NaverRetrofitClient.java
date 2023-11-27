package Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NaverRetrofitClient {
    private static NaverRetrofitClient instance = null;
    private static NaverRetrofitInterface naverRetrofitInterface;
    private static String baseUrl = "https://openapi.naver.com/v1/";

    private NaverRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())//이 부분 추가
                .build();

        naverRetrofitInterface = retrofit.create(NaverRetrofitInterface.class);
    }

    public static NaverRetrofitClient getInstance() {
        if (instance == null) {
            instance = new NaverRetrofitClient();
        }
        return instance;
    }

    public static NaverRetrofitInterface getRetrofitInterface() {
        return naverRetrofitInterface;
    }

}
