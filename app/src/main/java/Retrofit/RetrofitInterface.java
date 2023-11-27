package Retrofit;

import java.util.Map;

import Dto.LocalQADto.ResponseDto.Example;
import Dto.TranslateDto.RequestDto.TranslateDto;
import Dto.TranslateDto.ResponseDto.ResponseTranslateDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json; charset=UTF-8"
    })
    @POST("LegalQA")
    Call<Example> getLegalQA(@Header("Authorization") String key, @Body Map<String, Object> request);


}
