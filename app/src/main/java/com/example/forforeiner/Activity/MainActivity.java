package com.example.forforeiner.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.forforeiner.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void legalButton(View view){
        Intent intent = new Intent(getApplicationContext(), legalCounseling.class);
        startActivity(intent);
    }

    public void translateButton(View view){

    }
}