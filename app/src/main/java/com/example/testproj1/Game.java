package com.example.testproj1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.testproj1.Personnages.Ana;
import com.example.testproj1.Personnages.Gorgory;
import com.example.testproj1.Personnages.Synderella;
import com.example.testproj1.Personnages.Verdrion;

public class Game extends AppCompatActivity {

    boolean[] result = new boolean[4];
    Ana PersoAna;
    Gorgory PersoGorgory;
    Synderella PersoSynderella;
    Verdrion PersoVerdrion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        result = getIntent().getBooleanArrayExtra("Personnage");
        for(int i = 0; i<result.length; i++ ){
            if(result[i] == true)
            {
                switch (i){
                    case 0 :
                        PersoAna = new Ana();
                        break;
                    case 1 :
                        PersoGorgory = new Gorgory();
                        break;
                    case 2 :
                        PersoSynderella = new Synderella();
                        break;
                    case 3 :
                        PersoVerdrion = new Verdrion();
                        break;
                }
            }
        }
    }
}
