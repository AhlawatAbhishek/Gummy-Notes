package com.mayur.gummynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private EditText editText;
    private Button incS, decS, bold, italic, underline;
    private TextView fontSize;
    GummyNote gummyNote = new GummyNote();
    float currSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        editText = (EditText) findViewById(R.id.homeET);
        incS = (Button) findViewById(R.id.homebtnInc);
        decS = (Button) findViewById(R.id.homebtReduce);
        bold = (Button) findViewById(R.id.HomeBoldBut);
        italic = (Button) findViewById(R.id.HomeItalicBut);
        underline = (Button) findViewById(R.id.HomeUnderBut);
        fontSize = (TextView) findViewById(R.id.HomeET);
        currSize = editText.getTextSize();
        fontSize.setText("" + currSize);
        incS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currSize++;
                fontSize.setText("" + currSize);
                editText.setTextSize(currSize);

            }
        });
        decS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currSize--;
                fontSize.setText("" + currSize);
                editText.setTextSize(currSize);
            }
        });
        bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               italic.setTextColor(getResources().getColor(R.color.white));
               italic.setBackgroundColor(getResources().getColor(R.color.purple_200));
//               underline.setTextColor(getResources().getColor(R.color.white));
//               underline.setBackgroundColor(getResources().getColor(R.color.purple_200));
               if(editText.getTypeface().isBold()){
                   editText.setTypeface(Typeface.DEFAULT);
                   bold.setTextColor(getResources().getColor(R.color.white));
                   bold.setBackgroundColor(getResources().getColor(R.color.purple_200));
               }else{
                   editText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                   bold.setTextColor(getResources().getColor(R.color.purple_200));
                   bold.setBackgroundColor(getResources().getColor(R.color.white));
               }
            }
        });
        italic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                underline.setTextColor(getResources().getColor(R.color.white));
//                underline.setBackgroundColor(getResources().getColor(R.color.purple_200));
                bold.setTextColor(getResources().getColor(R.color.white));
                bold.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if(editText.getTypeface().isBold()){
                    editText.setTypeface(Typeface.DEFAULT);
                    italic.setTextColor(getResources().getColor(R.color.white));
                    italic.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }else{
                    editText.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                    italic.setTextColor(getResources().getColor(R.color.purple_200));
                    italic.setBackgroundColor(getResources().getColor(R.color.white));
                }
            }
        });
        underline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                italic.setTextColor(getResources().getColor(R.color.white));
//                italic.setBackgroundColor(getResources().getColor(R.color.purple_200));
//                bold.setTextColor(getResources().getColor(R.color.white));
//                bold.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if(editText.getPaintFlags() == 8){
                    editText.setPaintFlags(editText.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
                    underline.setTextColor(getResources().getColor(R.color.white));
                    underline.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }else{
                    editText.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                    underline.setTextColor(getResources().getColor(R.color.purple_200));
                    underline.setBackgroundColor(getResources().getColor(R.color.white));
                }
            }
        });
    }

    public void saveButton(View view) {
        gummyNote.setGummy(editText.getText().toString(), this);
        updateWig();
        Toast.makeText(this, "Your note has been updated", Toast.LENGTH_SHORT).show();
    }

    private void updateWig() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.widget_lay);
        ComponentName thisWg = new ComponentName(this, AppWig.class);
        remoteViews.setTextViewText(R.id.widgetTxtV, editText.getText().toString());
        appWidgetManager.updateAppWidget(thisWg, remoteViews);

    }
}