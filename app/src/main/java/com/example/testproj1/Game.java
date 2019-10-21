package com.example.testproj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testproj1.Personnages.Ana;
import com.example.testproj1.Personnages.Dice;
import com.example.testproj1.Personnages.Gorgory;
import com.example.testproj1.Personnages.Personnage;
import com.example.testproj1.Personnages.Synderella;
import com.example.testproj1.Personnages.Verdrion;


public class Game extends AppCompatActivity {

    private View GameVue;
    boolean[] result = new boolean[4];
    Ana PersoAna;
    Gorgory PersoGorgory;
    Synderella PersoSynderella;
    Verdrion PersoVerdrion;
    Button BoutonPerso0;
    Button BoutonPerso1;
    Button BoutonPerso2;
    Button BoutonPerso3;
    Button BoutonMonstre1;
    Button BoutonMonstre2;
    Button BoutonMonstre3;
    Button BoutonMonstre4;
    Button BoutonMonstre5;
    Button BoutonMonstre6;
    Button[] BoutonPersos;
    Button[] BoutonMonstres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        BoutonPerso1 = (Button) findViewById(R.id.ButPerso1);
        BoutonPerso2 = (Button) findViewById(R.id.ButPerso2);
        BoutonPerso3 = (Button) findViewById(R.id.ButPerso3);
        BoutonPerso0 = (Button) findViewById(R.id.ButPerso0);
        BoutonMonstre1 = (Button) findViewById(R.id.Monstre1);
        BoutonMonstre2 = (Button) findViewById(R.id.Monstre2);
        BoutonMonstre3 = (Button) findViewById(R.id.Monstre3);
        BoutonMonstre4 = (Button) findViewById(R.id.Monstre4);
        BoutonMonstre5 = (Button) findViewById(R.id.Monstre5);
        BoutonMonstre6 = (Button) findViewById(R.id.Monstre6);
        BoutonMonstre1.setVisibility(View.INVISIBLE);
        BoutonMonstre2.setVisibility(View.INVISIBLE);
        BoutonMonstre3.setVisibility(View.INVISIBLE);
        BoutonMonstre4.setVisibility(View.INVISIBLE);
        BoutonMonstre5.setVisibility(View.INVISIBLE);
        BoutonMonstre6.setVisibility(View.INVISIBLE);
        GameVue = (View) findViewById(R.id.GameVue);
        BoutonPersos = new Button[4];
        BoutonMonstres = new Button[6];
        BoutonPersos[0] = BoutonPerso1;
        BoutonPersos[1] = BoutonPerso0;
        BoutonPersos[2] = BoutonPerso2;
        BoutonPersos[3] = BoutonPerso3;
        String theme = getIntent().getStringExtra("theme");

        switch (theme){
            case "soir" :
                GameVue.setBackgroundColor(getResources().getColor(R.color.couleurSoir));
                break;
            case "matin" :
                GameVue.setBackgroundColor(getResources().getColor(R.color.couleurMatin));
                break;
            case "midi":
                GameVue.setBackgroundColor(getResources().getColor(R.color.couleurMidi));
                break;
        }



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
        Invisible();
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

    public void Invisible()
    {
        for(int j = 0; j<4; j++)
        {
            if(BoutonPersos[j].getText().equals("Perso"))
            {
                BoutonPersos[j].setVisibility(View.INVISIBLE);
            }
        }
    }

    public Personnage getPersonnageWithName(String name)
    {
        switch (name)
        {
            case "Ana":
                return PersoAna;

            case "Gorgory":
                return PersoGorgory;

            case "Synderella":
                return PersoSynderella;

            case "Verdrion":
                return PersoVerdrion;

        }

        // Si jamais le switch/case ne passe pas, on retourne un personnage inexistant possédant des statistiques par défaut.
        return new Personnage("NOT FOUND",0,0,0,new Dice(0,0), new Dice(0,0));
    }

    public void GoFiche(View v)
    {
        Button localButton = (Button)v;

        Intent intent = new Intent(getApplicationContext(), FicheActivity.class);
        intent.putExtra("infos",getPersonnageWithName((String)localButton.getText()));


        startActivity(intent);
    }

    public void AddMonstre(View v){
        for(int j = 0; j<6; j++)
        {
            if(BoutonMonstres[j].getText().equals("Monstre"))
            {
                BoutonMonstres[j].setText("Monstre1");
            }
        }
    }
}
