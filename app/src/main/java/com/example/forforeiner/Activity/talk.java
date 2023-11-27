package com.example.forforeiner.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.forforeiner.BuildConfig;
import com.example.forforeiner.R;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dto.LocalQADto.ResponseDto.AnswerInfo;
import Dto.LocalQADto.ResponseDto.Example;
import Dto.TranslateDto.RequestDto.TranslateDto;
import Dto.TranslateDto.ResponseDto.ResponseTranslateDto;
import Retrofit.NaverRetrofitClient;
import Retrofit.NaverRetrofitInterface;
import Retrofit.RetrofitClient;
import Retrofit.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class talk extends AppCompatActivity {

    private EditText et;
    private LinearLayout ll;

    private RetrofitClient retrofitClient;
    private RetrofitInterface retrofitInterface;
    private NaverRetrofitClient naverRetrofitClient;
    private NaverRetrofitInterface naverRetrofitInterface;

    private String answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        et = findViewById(R.id.questionInput);
        ll = findViewById(R.id.chatLayout);
    }

    public void sendButton(View view){
        TextView newText = new TextView(this);
        newText.setText(et.getText().toString());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.RIGHT;

        ll.addView(newText, layoutParams);

        translateQuestion(et.getText().toString());

    }

    private void translateQuestion(String question){
        naverRetrofitClient = NaverRetrofitClient.getInstance();
        naverRetrofitInterface = NaverRetrofitClient.getRetrofitInterface();
        naverRetrofitInterface.getTranslateToKorean(BuildConfig.NAVER_CLIENT_ID,BuildConfig.NAVER_SECRET_KEY, "en", "ko", question).enqueue(new Callback<ResponseTranslateDto>() {
            @Override
            public void onResponse(Call<ResponseTranslateDto> call, Response<ResponseTranslateDto> response) {
                ResponseTranslateDto result = response.body();
                Log.d("retrofit", "Data fetch success");
                System.out.println(result.getMessage().getResult().getTranslatedText());
                getAnswer(result.getMessage().getResult().getTranslatedText());
            }

            @Override
            public void onFailure(Call<ResponseTranslateDto> call, Throwable t) {
                Log.d("retrofit", t.getMessage());
            }
        });
    }

    private void getAnswer(String question){
        retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = RetrofitClient.getRetrofitInterface();
        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();

        argument.put("question", question);
        request.put("argument", argument);

        retrofitInterface.getLegalQA(BuildConfig.AI_API_KEY, request).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example result = response.body();
                Log.d("retrofit", "Data fetch success");
                List<AnswerInfo> answerInfos = result.getReturnObject().getLegalInfo().getAnswerInfo();

                answer = answerInfos.get(0).getAnswer();

                TextView newText = new TextView(getApplicationContext());
                newText.setText(answer);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.LEFT;

                ll.addView(newText, layoutParams);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("retrofit", t.getMessage());
            }
        });
    }
}