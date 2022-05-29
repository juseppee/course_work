package com.example.logicgame;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OnlyTheoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory);
        Button btnNew = (Button) findViewById(R.id.buttonNextlvl);
        btnNew.setText(R.string.moreTheory);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    TextView tv = (TextView) findViewById(R.id.textView2);
                    tv.setText(R.string.theoryTwo);
                } catch (Exception e){
                }
            }
        });
    }


    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(OnlyTheoryActivity.this, LevelsActivity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e){
        }
    }
}