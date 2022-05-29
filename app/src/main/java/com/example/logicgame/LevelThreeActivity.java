package com.example.logicgame;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LevelThreeActivity extends AppCompatActivity {

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory);

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        final int level = save.getInt("Level", 1);
        if (level > 1){

        }else{
            SharedPreferences.Editor editor = save.edit();
            editor.putInt("Level", 2);
            editor.commit();
        }

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.preview_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        TextView textDescription = (TextView) dialog.findViewById(R.id.textDescription);
        textDescription.setText(R.string.levelthree);

        TextView btnClose = (TextView) dialog.findViewById(R.id.btnclose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent (LevelThreeActivity.this, LevelsActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });
        Button btnSkip = (Button) dialog.findViewById(R.id.btnskip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

        Button buttonStartTasks = (Button) findViewById(R.id.buttonNextlvl);
        buttonStartTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent (LevelThreeActivity.this, LevelOneActivity.class);
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
            Intent intent = new Intent(LevelThreeActivity.this, LevelsActivity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e){
        }
    }
}
