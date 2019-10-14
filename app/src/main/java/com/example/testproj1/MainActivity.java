package com.example.testproj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Commence(View v){
        Intent intent = new Intent(getApplicationContext(), SelectionPersonnage.class);
        startActivity(intent);
    }

    public void Quit(View V){
        finish();
    }
}
