package com.example.quizfront;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class quizActivity extends AppCompatActivity implements View.OnClickListener {

    TextView question;
    Button First, Second, Third, Fourth;
    TextView correctness, score;

    int total = qna.Question.length;
    int correct = 0;
    int index;
    String choice = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizfront_layout);

        question = findViewById(R.id.question);
        First = findViewById(R.id.First);
        Second = findViewById(R.id.Second);
        Third  = findViewById(R.id.Third);
        Fourth = findViewById(R.id.Fourth);
        correctness = findViewById(R.id.correctness);
        score = findViewById(R.id.score);


        First.setOnClickListener(this);
        Second.setOnClickListener(this);
        Third.setOnClickListener(this);
        Fourth.setOnClickListener(this);

        loadQuestion();

    }

    public void loadQuestion() {

        if (index == total){
            System.exit(0);
        }

        question.setText(qna.Question[index]);
        First.setText(qna.answers[index][0]);
        Second.setText(qna.answers[index][1]);
        Third.setText(qna.answers[index][2]);
        Fourth.setText(qna.answers[index][3]);
        correctness.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        Button clicked = (Button) v;

        choice = clicked.getText().toString();

        if (choice.equals(qna.correct[index])){
            correctness.setText("Correct!");
            correctness.setTextColor(Color.GREEN);
            correctness.setVisibility(View.VISIBLE);
            correct++;
        } else {
            correctness.setText("Incorrect!");
            correctness.setTextColor(Color.RED);
            correctness.setVisibility(View.VISIBLE);
        }

        score.setText(String.valueOf(correct));

        index++;
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                loadQuestion();
            }
        };

        // Post the Runnable with a delay
        handler.postDelayed(runnable, 1000);
    }
}
