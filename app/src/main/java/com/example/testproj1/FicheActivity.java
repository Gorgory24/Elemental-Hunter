package com.example.testproj1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FicheActivity extends AppCompatActivity {

    TextView nameText;
    TextView pvText, manaText;
    TextView atkpText, atkmText;
    TextView defpText, defmText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche);
    }
}
