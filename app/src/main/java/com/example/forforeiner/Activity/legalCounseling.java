package com.example.forforeiner.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.forforeiner.R;

public class legalCounseling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal_counseling);
    }

    public void talkButton(View view){
        Intent intent = new Intent(getApplicationContext(), talk.class);
        startActivity(intent);
    }
    public void findButton(View view){
        Intent intent = new Intent(getApplicationContext(), nearLawCenter.class);
        startActivity(intent);
    }
}