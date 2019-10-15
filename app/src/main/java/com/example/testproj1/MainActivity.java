package com.example.testproj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    View MainVue;
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

    public void Quit(View V){
        finish();
    }
}
