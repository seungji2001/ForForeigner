package Retrofit;

import Dto.TranslateDto.ResponseDto.ResponseTranslateDto;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NaverRetrofitInterface {
    @Headers({
            "Content-Type: application/x-www-form-urlencoded; charset=UTF-8"
    })
    @POST("papago/n2mt")
    Call<ResponseTranslateDto> getTranslateToKorean(@Header("X-Naver-Client-Id") String client_id, @Header("X-Naver-Client-Secret")String secret_key,
                                                    @Query("source")String source,
                                                    @Query("target")String target,
                                                    @Query("text") String text);
}
