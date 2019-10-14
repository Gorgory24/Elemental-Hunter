package com.example.testproj1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

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
    Button BoutonPerso0;
    Button BoutonPerso1;
    Button BoutonPerso2;
    Button BoutonPerso3;
    Button[] BoutonPersos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        BoutonPerso1 = (Button) findViewById(R.id.ButPerso1);
        BoutonPerso2 = (Button) findViewById(R.id.ButPerso2);
        BoutonPerso3 = (Button) findViewById(R.id.ButPerso3);
        BoutonPerso0 = (Button) findViewById(R.id.ButPerso0);
        BoutonPersos = new Button[4];
        BoutonPersos[0] = BoutonPerso1;
        BoutonPersos[1] = BoutonPerso0;
        BoutonPersos[2] = BoutonPerso2;
        BoutonPersos[3] = BoutonPerso3;



        result = getIntent().getBooleanArrayExtra("Personnage");
        for(int i = 0; i<result.length; i++ ){
            if(result[i] == true)
            {
                switch (i){
                    case 0 :
                        PersoAna = new Ana();
                        PlaceBouton("Ana");
                        break;
                    case 1 :
                        PersoGorgory = new Gorgory();
                        PlaceBouton("Gorgory");
                        break;
                    case 2 :
                        PersoSynderella = new Synderella();
                        PlaceBouton("Synderella");
                        break;
                    case 3 :
                        PersoVerdrion = new Verdrion();
                        PlaceBouton("Verdrion");
                        break;
                }
            }
        }
    }

    public void PlaceBouton(String name){
        for(int i = 0; i<4; i++)
        {
            if(BoutonPersos[i].getText().equals("Perso"))
            {
                BoutonPersos[i].setText(name);
                break;
            }
        }
    }
}
