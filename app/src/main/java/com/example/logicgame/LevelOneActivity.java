package com.example.logicgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class LevelOneActivity extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    public int numLeft;
    public int numRight;
    Array array = new Array();
    public int count = 0;
    int rightAnswerDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal);

        TextView textLevels = findViewById(R.id.textLevels);
        textLevels.setText(R.string.level2);

        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.preview_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        TextView btnClose = (TextView) dialog.findViewById(R.id.btnclose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent (LevelOneActivity.this, LevelsActivity.class);
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

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();

        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialog_end);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.setCancelable(false);

        TextView btnCloseEnd = (TextView) dialogEnd.findViewById(R.id.btnclose);
        btnCloseEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent (LevelOneActivity.this, LevelsActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
                dialogEnd.dismiss();
            }
        });
        TextView btnSkipEnd = (Button) dialogEnd.findViewById(R.id.btnskip);
        btnSkipEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent (LevelOneActivity.this, LevelTwoActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
                dialogEnd.dismiss();
            }
        });

        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4,
                R.id.point5, R.id.point6, R.id.point7, R.id.point8
        };

        final Animation a = AnimationUtils.loadAnimation(LevelOneActivity.this, R.anim.alpha);

        numLeft = 0;
        text_left.setText(array.text[0]);

        numRight = 1;
        text_right.setText(array.text[1]);

        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);
                    if (numLeft == rightAnswerDB) {
//                    if (numLeft > numRight) {
                        img_left.setImageResource(R.drawable.right_answer);
                    }else{
                        img_left.setImageResource(R.drawable.wrong_answer);
                    }
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if (numLeft == rightAnswerDB) {
//                    if (numLeft > numRight) {
                        if (count < 8){
                            count = count + 1;
                        }

                        for (int i = 0; i < 8; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_done);
                        }
                    }else{
                        if (count > 0){
                            count = count - 1;
                        }

                        for (int i = 0; i < 7; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_done);
                        }
                    }
                    if (count == 8){
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if (level > 2){

                        }else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 3);
                            editor.commit();
                        }
                        dialogEnd.show();
                    }else{

                        img_left.setImageResource(array.images[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.text[0]);

                        img_right.setImageResource(array.images[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.text[1]);
                        img_right.setEnabled(true);


                        onResume();
                    }
                }
                return true;
            }
        });

        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    img_left.setEnabled(false);
                    if (numRight == rightAnswerDB) {
//                    if (numLeft < numRight) {
                        img_right.setImageResource(R.drawable.right_answer);
                    }else{
                        img_right.setImageResource(R.drawable.wrong_answer);
                    }
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    if (numLeft < numRight) {
                    if (numRight == rightAnswerDB) {
                        if (count < 8){
                            count = count + 1;
                        }
                        for (int i = 0; i < 8; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_done);
                        }
                    }else{
                        if (count > 0){
                            count = count - 1;
                        }

                        for (int i = 0; i < 7; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_done);
                        }
                    }

                    if (count == 8){
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if (level > 2){

                        }else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 3);
                            editor.commit();
                        }
                        dialogEnd.show();
                    }else{
                        img_left.setImageResource(array.images[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.text[0]);

                        img_right.setImageResource(array.images[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.text[1]);
                        img_left.setEnabled(true);

                        onResume();
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        db = databaseHelper.open();
        Cursor query = db.rawQuery("SELECT * FROM tasks WHERE theme = 1;", null);
        TextView textTask = findViewById(R.id.textTask);
        Random random = new Random();
        System.out.println("sdfdsfdsffd " + query.getCount());
        int i = random.nextInt(query.getCount() + 1);
        System.out.println(i);
        while(query.moveToPosition(i)){
            String age = query.getString(2);
            rightAnswerDB = query.getInt(3);
            textTask.setText(age);
            break;
        }
    }

    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(LevelOneActivity.this, LevelsActivity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception e){
        }
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        // Закрываем подключение и курсор
//        db.close();
//        userCursor.close();
//    }
}