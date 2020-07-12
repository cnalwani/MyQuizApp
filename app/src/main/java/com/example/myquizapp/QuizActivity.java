package com.example.myquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView txtViwQuestion, txtViwScore, txtViwQUSCount, txtViwCountDown;
    RadioGroup rbGroup;
    RadioButton rb1, rb2, rb3;
    Button buttonConfmNext;
    List<Question> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        txtViwCountDown = (TextView) findViewById(R.id.countdown);
        txtViwQuestion = (TextView) findViewById(R.id.QuTextViw);
        txtViwQUSCount = (TextView) findViewById(R.id.Que_count);
        txtViwScore = (TextView) findViewById(R.id.View_Score);
    }
}
