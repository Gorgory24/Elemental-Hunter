package com.example.testproj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    View MainVue;
    private MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainVue = (View)findViewById(R.id.MainVue);
        MainVue.setBackgroundColor(getResources().getColor(R.color.couleurSoir)); //On change la couleur du background ici en couleur du soir (bleu)
    }

    public void Commence(View v){
        Intent intent = new Intent(getApplicationContext(), SelectionPersonnage.class);
        intent.putExtra("theme","soir");
        startActivity(intent);
    }

    public void play(View v) {
        stop();
        mPlayer = MediaPlayer.create(this, R.raw.connecte);
        mPlayer.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        stop();
                    }
                }
        );
        mPlayer.start();
    }

    public void stop() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void Quit(View V){
        finish();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stop();
    }
}

// Sound made by Starrysky - Connecte toi