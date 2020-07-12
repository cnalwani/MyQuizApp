package com.example.myquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

 public class QuizActivity extends AppCompatActivity {

    TextView txtViwQuestion, txtViwScore, txtViwQUSCount, txtViwCountDown;
    RadioGroup rbGroup;
    RadioButton rb1, rb2, rb3;
    Button buttonConfmNext;

    private ColorStateList textColorDefaultRb;
    List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        txtViwCountDown = (TextView) findViewById(R.id.countdown);
        txtViwQuestion = (TextView) findViewById(R.id.QuTextViw);
        txtViwQUSCount = (TextView) findViewById(R.id.Que_count);
        txtViwScore = (TextView) findViewById(R.id.View_Score);
        rbGroup = (RadioGroup) findViewById(R.id.radio_group);
        rb1 = (RadioButton) findViewById(R.id.radBut1);
        rb2 = (RadioButton) findViewById(R.id.radBut2);
        rb3 = (RadioButton) findViewById(R.id.radBut3);
        buttonConfmNext = (Button) findViewById(R.id.button_confirm_text);

        textColorDefaultRb = rb1.getTextColors();

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        buttonConfmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answered) {
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    }
                    else
                    {
                        Toast.makeText(QuizActivity.this, "please select the answer", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void showNextQuestion(){
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if(questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            txtViwQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            questionCounter++;
            txtViwQUSCount.setText("Question :" + questionCounter + "/"  + questionCountTotal);
            answered = false;
            buttonConfmNext.setText("confirm");
        }else{
            finishQuiz();
        }
    }
    private void checkAnswer(){
        answered = true;
        RadioButton rbsSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbsSelected) + 1;

        if(answerNr == currentQuestion.getAnswerNr()){
            score++;
            txtViwScore.setText("Score " + score);
        }
    }
    private void finishQuiz() {
        finish();
    }
}
