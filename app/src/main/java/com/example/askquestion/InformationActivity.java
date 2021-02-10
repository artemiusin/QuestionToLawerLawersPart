package com.example.askquestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {
    TextView textViewPhone, textViewQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        init();
    }

    private void init() {

        textViewPhone = findViewById(R.id.textViewPhone);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        Intent intent = getIntent();
        if (intent != null){
            textViewPhone.setText(intent.getStringExtra("phone"));
            textViewQuestion.setText(intent.getStringExtra("question"));
        }
    }
}