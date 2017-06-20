package com.mah.braining;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button startButton;
    TextView resultTextView;
    TextView pointTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameConstraintLayout;
    GridLayout gridLayout;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;



    public void playAgain(View view){


        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(GridLayout.VISIBLE);

        generateQuestion();
        createCountDownTimer();

//////////////

//        new CountDownTimer(30100, 1000){
//
//
//            @Override
//            public void onTick(long l ) {
//
//
//                timerTextView.setText(String.valueOf(l / 1000) + "s");
//
//            }
//
//            @Override
//            public void onFinish() {
//                gridLayout.setVisibility(GridLayout.INVISIBLE);
//                playAgainButton.setVisibility(View.VISIBLE);
//                timerTextView.setText("0s");
//                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
//
//            }
//        }.start();
///////////////

    }

public void createCountDownTimer() {

            new CountDownTimer(30100, 1000){


            @Override
            public void onTick(long l ) {


                timerTextView.setText(String.valueOf(l / 1000) + "s");

            }

            @Override
            public void onFinish() {
                gridLayout.setVisibility(GridLayout.INVISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

            }
        }.start();


}



    public void generateQuestion(){

        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i=0; i<4; i++){

            if ( i == locationOfCorrectAnswer){

                answers.add(a + b);

            } else {

                incorrectAnswer = random.nextInt(41);

                while (incorrectAnswer == a + b){

                    incorrectAnswer = random.nextInt(41);

                }

                answers.add(incorrectAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view){

        Log.i("Tag", (String) view.getTag());

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            Log.i("correct", "correct");
            score++;

            resultTextView.setText("Correct!");

        } else {

            Log.i("wrong", "wrong");

            resultTextView.setText("Wrong!");
        }

        numberOfQuestions++;
        pointTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();


    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameConstraintLayout.setVisibility(ConstraintLayout.VISIBLE);

        createCountDownTimer();
        playAgain(findViewById(R.id.playAgainButton));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameConstraintLayout = (ConstraintLayout)findViewById(R.id.gameConstraintLayout);
        gridLayout = (GridLayout)findViewById(R.id.gridLayout);





    }
}
