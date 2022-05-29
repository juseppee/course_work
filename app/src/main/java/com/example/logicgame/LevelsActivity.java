package com.example.logicgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LevelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelevels);

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        final int level = save.getInt("Level", 1);

        TextView textViewOne = (TextView)findViewById(R.id.textViewOne);
        textViewOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (level >= 1){
                        Intent intent = new Intent(LevelsActivity.this, LevelThreeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        TextView textViewTwo = (TextView)findViewById(R.id.textViewTwo);
        textViewTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (level >= 2){
                        Intent intent = new Intent(LevelsActivity.this, LevelOneActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        TextView textViewThree = (TextView)findViewById(R.id.textViewThree);
        textViewThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (level >= 3){
                        Intent intent = new Intent(LevelsActivity.this, LevelTwoActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        final int[] x = {
                R.id.textViewOne,
                R.id.textViewTwo,
                R.id.textViewThree,
                R.id.textViewFour,
                R.id.textViewFive,
                R.id.textViewSix,
                R.id.textViewSeven,
                R.id.textViewEight,
                R.id.textViewNine,
                R.id.textViewTen,
                R.id.textViewEleven,
                R.id.textViewTwelve,
                R.id.textViewThirteen,
                R.id.textViewFourteen,
                R.id.textViewFifteen,
                R.id.textViewSixteen,
                R.id.textViewSeventeen,
                R.id.textViewEighteen,
                R.id.textViewNineteen,
                R.id.textViewTwenty,
                R.id.textViewTwentyOne,
                R.id.textViewTwentyTwo,
                R.id.textViewTwentyThree,
                R.id.textViewTwentyFour,
                R.id.textViewTwentyFive,
                R.id.textViewTwentySix,
                R.id.textViewTwentySeven,
                R.id.textViewTwentyEight,
                R.id.textViewTwentyNine,
                R.id.textViewThirty,
                R.id.textViewThirtyOne,
                R.id.textViewThirtyTwo,
                R.id.textViewThirtyThree,
                R.id.textViewThirtyFour,
                R.id.textViewThirtyFive,
        };

        for (int i = 1; i < level; i++){
            TextView tv = findViewById(x[i]);
            tv.setText("" + (i + 1));
        }

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(LevelsActivity.this, ChooseGameActivity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e){
        }
    }
}
