package com.example.logicgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        Button buttonStartClassic = (Button) findViewById(R.id.classicGame);
        buttonStartClassic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent (ChooseGameActivity.this, LevelsActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e){
                }
            }
        });

        Button buttonTheory = (Button) findViewById(R.id.onlyTheory);
        buttonTheory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent (ChooseGameActivity.this, OnlyTheoryActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e){
                }
            }
        });

        Button buttonGameOnly = (Button) findViewById(R.id.onlyTasks);
        buttonTheory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent (ChooseGameActivity.this, LevelOneActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e){
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(ChooseGameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e){
        }
    }
}
