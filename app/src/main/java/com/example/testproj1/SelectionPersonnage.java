package com.example.testproj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;


public class SelectionPersonnage extends AppCompatActivity {
    private final int Ana = 0;
    private final int Gorgory = 1;
    private final int Synderella = 2;
    private final int Verdrion = 3;
    private boolean[] result = new boolean[4];

    private Switch SwitchAna;
    private Switch SwitchGorgory;
    private Switch SwitchSynderella;
    private Switch SwitchVerdrion;
    private View SelecVue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_personnage);
        SwitchAna = (Switch) findViewById(R.id.switchAna);
        SwitchGorgory =(Switch) findViewById(R.id.switchGorgory);
        SwitchVerdrion = (Switch) findViewById(R.id.switchVerdrion);
        SwitchSynderella = (Switch) findViewById(R.id.switchSynderella);
        SelecVue = (View) findViewById(R.id.SelecVue);
        String theme = getIntent().getStringExtra("theme");

        switch (theme){
            case "soir" :
                SelecVue.setBackgroundColor(getResources().getColor(R.color.couleurSoir));
                break;
            case "matin" :
                SelecVue.setBackgroundColor(getResources().getColor(R.color.couleurMatin));
                break;
            case "midi":
                SelecVue.setBackgroundColor(getResources().getColor(R.color.couleurMidi));
                break;
        }

        for(int i=0; i<4; i++){
            result[i] = false;
        }

        SwitchAna.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
            result[Ana] = isChecked;
            }
        });

        SwitchGorgory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                result[Gorgory] = isChecked;
            }
        });

        SwitchVerdrion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                result[Verdrion] = isChecked;
            }
        });

        SwitchSynderella.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                result[Synderella] = isChecked;
            }
        });
    }

    public void Launch(View view){
        Intent intent = new Intent(getApplicationContext(), Game.class);
        intent.putExtra("Personnage", result);
        intent.putExtra("theme", "soir");
        startActivity(intent);
    }




}
