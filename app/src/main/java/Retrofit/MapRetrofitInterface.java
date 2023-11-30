package Retrofit;

import Dto.MapGeocoderDto.ResponseDto.GeocoderDto;
import Dto.TranslateDto.ResponseDto.ResponseTranslateDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MapRetrofitInterface {
    @Headers({
            "Content-Type: application/x-www-form-urlencoded; charset=UTF-8"
    })
    @GET("geocode/json")
    Call<GeocoderDto> geocoder(@Query("key") String google_api_key,
                               @Query("address")String address);

}
