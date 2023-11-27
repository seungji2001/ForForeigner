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
import java.util.Map;

import Dto.LocalQADto.ResponseDto.Example;
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

        //해당 질문 보내야
        retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = RetrofitClient.getRetrofitInterface();

        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();

        String question = "대법원장 임기는 몇년인가요?";

        argument.put("question", question);
        request.put("argument", argument);

        retrofitInterface.getLegalQA(BuildConfig.AI_API_KEY, request).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example result = response.body();
                Log.d("retrofit", "Data fetch success");
                System.out.println(result);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("retrofit", t.getMessage());
            }
        });

    }
}