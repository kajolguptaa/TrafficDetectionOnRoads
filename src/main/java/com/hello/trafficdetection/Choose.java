package com.hello.trafficdetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Locale;

public class Choose extends AppCompatActivity implements TextToSpeech.OnInitListener {
private ImageView nav_view, searchview;
    TextToSpeech tts;
    int counter =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        nav_view=findViewById(R.id.nav_view);
        searchview=findViewById(R.id.searchview);

        try{
            this.getSupportActionBar().hide();
        }catch(NullPointerException e){

        }
        tts = new TextToSpeech(this, this);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (counter == 1) {
                    speakOut(" this this window has two options.search anything .Road Walking help.search anything is on the upper half portion and road walking help is in bottom of window. click any one of them.");
                    //speak after 1000ms
                    counter++;

                }
                else{

                }

            }

            private void speakOut(String text) {

                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        }, 500);
        nav_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calling =new Intent(Choose.this,CallingActivity.class);
                startActivity(calling);
            }
        });
        searchview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calling =new Intent(Choose.this,SearchMain.class);
                startActivity(calling);
            }
        });
    }
    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            // tts.setPitch(5); // set pitch level

            // tts.setSpeechRate(2); // set speech speed rate

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language is not supported");
            }
            else {

            }

        } else {
            Log.e("TTS", "Initilization Failed");
        }

    }
    @Override
    protected void onStop()
    {
        super.onStop();

        if(tts != null){
            tts.shutdown();
        }
    }

}
